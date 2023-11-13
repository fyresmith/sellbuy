package com.peach.sellbuy;

import java.io.*;
import com.peach.sellbuy.business.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "changeEmailServlet", value = "/change-email")
public class ChangeEmailServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String email = request.getParameter("newEmail");

        User user = (User) session.getAttribute("user");

        user.setEmail(email);

        user.update();

        session.setAttribute("alertTitle", "Success!");
        session.setAttribute("alertMessage", "Your email address was updated!");
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    public void destroy() {}
}

