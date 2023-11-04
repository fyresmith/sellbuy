package com.peach.sellbuy_ecommerce;

import java.io.*;

import com.peach.sellbuy_ecommerce.business.User;
import com.peach.sellbuy_ecommerce.util.Validator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "createAccountServlet", value = "/create-account-servlet")
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

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(request, response);
        }

        if (!Validator.isValidPassword(password)) {
            session.setAttribute("failMessage", "Password is Invalid!");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(request, response);
        }

        if (!Validator.isValidEmail(email)) {
            session.setAttribute("failMessage", "Email Address is Invalid!");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(request, response);
        }

        if (!Validator.isValidUsername(username)) {
            session.setAttribute("failMessage", "Username is Invalid!");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(request, response);
        }

        User user = new User(firstName, lastName, username, password, email, address, payment);

        user.save();

        session.setAttribute("user", user);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-account.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}