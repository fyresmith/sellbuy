package com.peach.sellbuy;

import com.peach.sellbuy.business.Image;
import com.peach.sellbuy.business.Product;
import com.peach.sellbuy.business.User;
import com.peach.sellbuy.util.Data;
import com.peach.sellbuy.util.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "addProductServlet", value = "/add-product")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        int productID = Integer.parseInt(request.getParameter("editProductID"));

        Product product = new Product();
        product.select(productID);

        for (Image image : product.getImages()) {
            image.delete();
        }

        product.delete();

        session.setAttribute("alertTitle", "Success!");
        session.setAttribute("alertMessage", "Your product was deleted from SellBuy!");
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String category = request.getParameter("productCategory");
        String keywords = request.getParameter("productKeywords");
        String stringPrice = request.getParameter("productPrice");
        String stringQuantity = request.getParameter("productQuantity");
        double price;
        int quantity;

        User user = (User) session.getAttribute("user");

        // Specify the desired field name
        String desiredFieldName = "productImage";

        // Get all parts from the request
        List<Part> allParts = new ArrayList<>(request.getParts());

        // Filter parts based on the desired field name
        List<Part> imageParts = new ArrayList<>();
        for (Part part : allParts) {
            String fieldName = part.getName(); // Assuming that the field name is the name attribute in your HTML form
            if (desiredFieldName.equals(fieldName)) {
                imageParts.add(part);
            }
        }

        if (stringPrice == null || !Util.isNumeric(stringPrice)) {
            price = 1.00;
        } else {
            price = Double.parseDouble(stringPrice);
        }

        if (stringQuantity == null || !Util.isNumeric(stringQuantity)) {
            quantity = 1;
        } else {
            quantity = Integer.parseInt(stringQuantity);
        }

        Product product = new Product();
        product.setProductName(name);
        product.setDescription(description);
        product.setProductCategory(category);
        product.setKeywords(keywords);
        product.setPrice(price);
        product.setStockQuantity(quantity);
        product.setSellerID(user.getUserID());

        for (Part imagePart : imageParts) {
            String fileName = getFileName(imagePart);
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
            String finalName = Util.generateRandomString(40) + "." + extension;


            try (InputStream fileContent = imagePart.getInputStream()) {
                // Define the destination path to save the file
                Path destinationPath = Paths.get(Data.image(), finalName);

                // Save the file to the destination path
                Files.copy(fileContent, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }

            Image productImage = new Image();
            productImage.setProductID(product.getProductID());
            productImage.setImageURL(finalName);
            productImage.save();
        }

        product.save();

        session.setAttribute("alertTitle", "Success!");
        session.setAttribute("alertMessage", "Your product was published to SellBuy!");
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    private String getFileName(Part part) {
        String submittedFileName = part.getSubmittedFileName();

        if (submittedFileName != null && !submittedFileName.isEmpty()) {
            return submittedFileName;
        }

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.isEmpty() ? null : fileName;
            }
        }
        return null;
    }
}
