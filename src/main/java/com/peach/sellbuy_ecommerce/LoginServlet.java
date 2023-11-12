package com.peach.sellbuy_ecommerce;

import java.io.*;
import java.util.Objects;

import com.peach.sellbuy_ecommerce.business.Access;
import com.peach.sellbuy_ecommerce.business.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");

        Access<User> access = new Access<>("user", "userID", User.class);

        if (!access.existsInColumn("email", email)) {
            session.setAttribute("failMessage", "Email or Password Incorrect!");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }

        User user = access.altSelect("email", email);

        if (!(Objects.equals(user.getPassword(), password))) {
            session.setAttribute("failMessage", "Email or Password Incorrect!");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }

        session.setAttribute("user", user);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-account.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}