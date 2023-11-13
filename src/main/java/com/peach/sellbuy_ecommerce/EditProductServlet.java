package com.peach.sellbuy_ecommerce;

import com.peach.sellbuy_ecommerce.business.Image;
import com.peach.sellbuy_ecommerce.business.Product;
import com.peach.sellbuy_ecommerce.business.User;
import com.peach.sellbuy_ecommerce.util.Data;
import com.peach.sellbuy_ecommerce.util.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;

@WebServlet(name = "editProductServlet", value = "/edit-product")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class EditProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        int productID = Integer.parseInt(request.getParameter("editProductID"));

        Product product = Product.fromID(productID);

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

        int productID = Integer.parseInt(request.getParameter("editProductID"));
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String category = request.getParameter("productCategory");
        String keywords = request.getParameter("productKeywords");
        String stringPrice = request.getParameter("productPrice");
        String stringQuantity = request.getParameter("productQuantity");
        double price;
        int quantity;

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

        Product product = Product.fromID(productID);

        User user = (User) session.getAttribute("user");

        product.setProductName(name);
        product.setDescription(description);
        product.setProductCategory(category);
        product.setKeywords(keywords);
        product.setPrice(price);
        product.setStockQuantity(quantity);
        product.setSellerID(user.getUserID());

        product.update();

        session.setAttribute("alertTitle", "Success!");
        session.setAttribute("alertMessage", "Your product was updated on SellBuy!");
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
