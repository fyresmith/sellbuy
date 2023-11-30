package com.peach.sellbuy;

import java.io.*;
import java.util.Objects;

import com.peach.sellbuy.business.Access;
import com.peach.sellbuy.business.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login")
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

            response.sendRedirect("login.jsp");
        } else {
            User user = access.altSelect("email", email);

            if (!(Objects.equals(user.getPassword(), password))) {
                session.setAttribute("failMessage", "Email or Password Incorrect!");

                response.sendRedirect("login.jsp");
            } else {
                session.setAttribute("user", user);

                response.sendRedirect("user-account.jsp");
            }
        }
    }

    public void destroy() {
    }
}