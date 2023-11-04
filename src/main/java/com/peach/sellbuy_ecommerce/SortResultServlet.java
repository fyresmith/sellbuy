package com.peach.sellbuy_ecommerce;

import java.io.*;
import java.util.LinkedList;
import java.util.Objects;

import com.peach.sellbuy_ecommerce.business.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "sortResultServlet", value = "/sort-result-servlet")
public class SortResultServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String option = request.getParameter("option");

        LinkedList<Product> results = (LinkedList<Product>) session.getAttribute("searchResults");

        if (Objects.equals(option, "best")) {
            Product.sortProductsRandomly(results);
        } else if (Objects.equals(option, "rated")) {
            Product.sortProductsDescending(results);
        } else {
            Product.sortProductsRandomly(results);
        }

        session.setAttribute("searchResults", results);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("search.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}