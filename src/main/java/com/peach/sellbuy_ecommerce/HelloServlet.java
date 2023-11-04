package com.peach.sellbuy_ecommerce;

import java.io.*;
import java.util.LinkedList;

import com.peach.sellbuy_ecommerce.business.Product;
import com.peach.sellbuy_ecommerce.business.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        LinkedList<Product> productList = Product.topProductList(30);

        session.setAttribute("products", productList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}