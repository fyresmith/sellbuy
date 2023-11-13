package com.peach.sellbuy;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "contactUsServlet", value = "/contact-us")
public class ContactUsServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        session.setAttribute("alertTitle", "Form Submitted!");
        session.setAttribute("alertMessage", "Thank you for contacting us! We will get back to you shortly.");
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    public void destroy() {
    }
}