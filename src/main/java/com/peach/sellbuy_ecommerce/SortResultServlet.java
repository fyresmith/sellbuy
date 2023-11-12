package com.peach.sellbuy_ecommerce;

import java.io.*;
import java.util.LinkedList;
import java.util.Objects;

import com.peach.sellbuy_ecommerce.business.Access;
import com.peach.sellbuy_ecommerce.business.Product;
import com.peach.sellbuy_ecommerce.util.Util;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "sortResultServlet", value = "/sort-result")
public class SortResultServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String option = request.getParameter("option");

        LinkedList<Product> results = (LinkedList<Product>) session.getAttribute("searchResults");
        Access<Product> access = new Access<>("product", "productID", Product.class);

        String query = (String) session.getAttribute("query");

        if (Objects.equals(option, "best")) {
            Product.sortProductsRandomly(results);
        } else if (Objects.equals(option, "rated")) {
            Product.sortProductsDescending(results);
        } else if (Objects.equals(option, "highest")) {
            results = access.searchAndSortByPrice(Util.rawCategory(query));
        } else if (Objects.equals(option, "lowest")) {
            results = access.searchAndSortByReversedPrice(Util.rawCategory(query));
        } else {
            Product.sortProductsRandomly(results);
        }

        session.setAttribute("searchResults", results);

        response.sendRedirect("search.jsp");
    }

    public void destroy() {
    }
}