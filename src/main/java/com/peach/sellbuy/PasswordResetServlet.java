package com.peach.sellbuy;

import java.io.*;

import com.peach.sellbuy.business.*;
import com.peach.sellbuy.util.NewSendEmail;
import com.peach.sellbuy.util.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "passwordResetServlet", value = "/password-reset")
public class PasswordResetServlet extends HttpServlet {

    public void init() {
        System.setProperty("javax.net.ssl.trustStore", "clientTrustStore.key");
        System.setProperty("javax.net.ssl.trustStorePassword", "qwerty");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String email = request.getParameter("userEmail");

        Access<User> access = new Access<>("user", "userID", User.class);

        if (!access.existsInColumn("email", email)) {
            session.setAttribute("failMessage", "Email does not exist!");

            response.sendRedirect("password-reset.jsp");
        } else {
            session.setAttribute("failMessage", "");

            User tempUser = access.altSelect("email", email);

            String code = Integer.toString(Util.generateSixDigitNumber());
            session.setAttribute("resetCode", code);

            NewSendEmail.resetPassword(email, tempUser.getName(), code);
            session.setAttribute("failMessage", "");

            session.setAttribute("successMessage", "Email sent!");
            session.setAttribute("tempUser", tempUser);

            response.sendRedirect("password-reset.jsp");
        }
    }

    public void destroy() {
    }
}