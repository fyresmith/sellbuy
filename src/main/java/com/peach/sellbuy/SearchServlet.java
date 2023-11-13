package com.peach.sellbuy;

import java.io.*;
import java.util.LinkedList;
import java.util.Objects;

import com.peach.sellbuy.business.Access;
import com.peach.sellbuy.business.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "searchServlet", value = "/search")
public class SearchServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String query = request.getParameter("searchBar");

        LinkedList<Product> results;


        Access<Product> access = new Access<>("product", "productID", Product.class);

        if (Objects.equals(query, "")) {
            results = access.search("a");
        } else {
            results = access.search(query);
        }

        session.setAttribute("pageNum", 1);
        session.setAttribute("searchResults", results);
        session.setAttribute("query", query);

        response.sendRedirect("search.jsp");
    }

    public void destroy() {
    }
}