package com.peach.sellbuy_ecommerce;

import java.io.*;
import java.net.URI;
import java.util.Date;
import java.util.Objects;

import com.peach.sellbuy_ecommerce.business.*;
import com.peach.sellbuy_ecommerce.util.Data;
import com.peach.sellbuy_ecommerce.util.Util;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "addProductServlet", value = "/add-product-servlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddProductServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String category = request.getParameter("productCategory");
        String keywords = request.getParameter("productKeywords");
        String stringPrice = request.getParameter("productPrice");
        String stringQuantity = request.getParameter("productQuantity");
        double price;
        int quantity;

        Part image = request.getPart("productImage");
        String fileName = image.getSubmittedFileName();

        for (Part part : request.getParts()) {
            part.write(Data.image() + fileName);
        }

        String imageURL = Data.image() + fileName;

        response.getWriter().print("The file uploaded sucessfully.");

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

        Image productImage = new Image();
        productImage.setProductID(product.getProductID());
        productImage.setImageURL(imageURL);
        productImage.save();

        product.save();

        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    public void destroy() {
    }
}