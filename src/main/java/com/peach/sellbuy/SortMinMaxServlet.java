package com.peach.sellbuy;

import java.io.*;
import java.util.LinkedList;

import com.peach.sellbuy.business.Access;
import com.peach.sellbuy.business.Product;
import com.peach.sellbuy.util.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "sortMinMaxServlet", value = "/sort-min-max")
public class SortMinMaxServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        int min, max;

        String minString = request.getParameter("min");
        String maxString = request.getParameter("max");

        if (Util.isNumeric(minString)) {
            min = Integer.parseInt(minString);

            if (min < 0) {
                min = 0;
            }
        } else {
            min = 0;
        }

        if (Util.isNumeric(maxString)) {
            max = Integer.parseInt(maxString);

            if (max > 100000) {
                max = 100000;
            }
        } else {
            max = 10000;
        }

        String query = (String) session.getAttribute("query");

        Access<Product> access = new Access<>("product", "productID", Product.class);
        LinkedList<Product> results = access.search(Util.rawCategory(query), min, max);

        session.setAttribute("searchResults", results);
        session.setAttribute("minString", minString);
        session.setAttribute("maxString", maxString);

        response.sendRedirect("search.jsp");
    }

    public void destroy() {
    }
}