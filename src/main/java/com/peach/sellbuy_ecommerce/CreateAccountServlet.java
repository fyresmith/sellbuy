package com.peach.sellbuy_ecommerce;

import java.io.*;

import com.peach.sellbuy_ecommerce.business.User;
import com.peach.sellbuy_ecommerce.util.Validator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "createAccountServlet", value = "/create-account")
public class CreateAccountServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String payment = "None";


        if (!Validator.isValidName(firstName) || !Validator.isValidName(lastName)) {
            session.setAttribute("failMessage", "First or Last Name is Invalid!");

            response.sendRedirect("register.jsp");
        }

        if (!Validator.isValidPassword(password)) {
            session.setAttribute("failMessage", "Password is Invalid!");

            response.sendRedirect("register.jsp");
        }

        if (!Validator.isValidEmail(email)) {
            session.setAttribute("failMessage", "Email Address is Invalid!");

            response.sendRedirect("register.jsp");
        }

        if (!Validator.isValidUsername(username)) {
            session.setAttribute("failMessage", "Username is Invalid!");

            response.sendRedirect("register.jsp");
        }

        User user = new User(firstName, lastName, username, password, email, address, payment);

        user.save();

        session.setAttribute("user", user);

        response.sendRedirect("user-account.jsp");
    }

    public void destroy() {
    }
}