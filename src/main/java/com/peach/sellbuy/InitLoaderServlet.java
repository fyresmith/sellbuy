package com.peach.sellbuy;

import com.peach.sellbuy.util.Util;
import com.peach.sellbuy.util.WebSeeder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet("/init-loader")
public class InitLoaderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        int users = Integer.parseInt(request.getParameter("Users"));
        int products = Integer.parseInt(request.getParameter("Products"));

        WebSeeder.populateDatabase(users, products, request);

        response.sendRedirect(Util.webPage("database.jsp"));
    }
}
