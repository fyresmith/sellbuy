package com.peach.sellbuy_ecommerce;

import java.io.*;

import com.peach.sellbuy_ecommerce.business.*;
import com.peach.sellbuy_ecommerce.util.NewSendEmail;
import com.peach.sellbuy_ecommerce.util.Util;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "passwordResetServlet", value = "/password-reset-servlet")
public class PasswordResetServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String email = request.getParameter("userEmail");

        Access<User> access = new Access<>("user", "userID", User.class);

        if (!access.valueExists("email", email)) {
            session.setAttribute("failMessage", "Email does not exist!");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("password-reset.jsp");
            requestDispatcher.forward(request, response);
        } else {
            session.setAttribute("failMessage", "");
        }

        User tempUser = access.alternateSelect("email", email);

        String code = Integer.toString(Util.generateSixDigitNumber());
        session.setAttribute("resetCode", code);

        NewSendEmail.resetPassword(email, tempUser.getName(), code);
        session.setAttribute("failMessage", "");

        session.setAttribute("successMessage", "Email sent!");
        session.setAttribute("tempUser", tempUser);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("password-reset.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}