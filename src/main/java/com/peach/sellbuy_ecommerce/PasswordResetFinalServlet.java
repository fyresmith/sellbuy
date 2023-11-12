package com.peach.sellbuy_ecommerce;

import java.io.*;
import java.util.Objects;

import com.peach.sellbuy_ecommerce.business.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "passwordResetFinalServlet", value = "/password-reset-final-servlet")
public class PasswordResetFinalServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String password = request.getParameter("newPassword");
        String cPassword = request.getParameter("confirmedPassword");

        if (!(Objects.equals(password, cPassword))) {
            session.setAttribute("failMessage", "Passwords do not match!");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("password-reset.jsp");
            requestDispatcher.forward(request, response);
        } else {
            session.setAttribute("failMessage", "");
        }

        User user = (User) session.getAttribute("tempUser");
        user.setPassword(password);

        user.update();

        session.setAttribute("successMessage", "Password reset!");

        response.sendRedirect("login.jsp");
    }

    public void destroy() {
    }
}