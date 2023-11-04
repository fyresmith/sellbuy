package com.peach.sellbuy_ecommerce;

import java.io.*;
import java.util.Objects;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "resetCodeServlet", value = "/reset-code-servlet")
public class ResetCodeServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String code = request.getParameter("userCode");

        String realCode = (String) session.getAttribute("resetCode");

        if (!Objects.equals(code, realCode)) {
            session.setAttribute("failMessage", "That code is incorrect!");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("password-reset.jsp");
            requestDispatcher.forward(request, response);
        }

        session.setAttribute("failMessage", "");
        session.setAttribute("successMessage", "PWRESET");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("password-reset.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}