package com.peach.sellbuy;

import java.io.*;
import java.util.Objects;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "resetCodeServlet", value = "/reset-code")
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

            response.sendRedirect("password-reset.jsp");
        } else {
            session.setAttribute("failMessage", "");
            session.setAttribute("successMessage", "PWRESET");

            response.sendRedirect("password-reset.jsp");
        }
    }

    public void destroy() {
    }
}