package com.peach.sellbuy_ecommerce;

import java.io.*;
import java.util.Objects;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "changeTableServlet", value = "/change-table")
public class ChangeTableServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String option = request.getParameter("table");

        session.setAttribute("table", Objects.requireNonNullElse(option, "product"));

        response.sendRedirect("database.jsp");
    }

    public void destroy() {
    }
}