package com.peach.sellbuy;

import com.peach.sellbuy.business.Access;
import com.peach.sellbuy.business.Product;
import com.peach.sellbuy.business.User;
import com.peach.sellbuy.util.AddressFormat;
import com.peach.sellbuy.util.Validator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register-servlet")
public class RegisterServlet extends HttpServlet {
    public void init() {
        String message = "Hello World!";
    }

    public void failResponse(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.getSession().setAttribute("failMessage", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String streetAddress = request.getParameter("streetAddress");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipCode = request.getParameter("zipCode");
        String password = request.getParameter("password");
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        session.setAttribute("username", username);
        session.setAttribute("email", email);
        session.setAttribute("streetAddress", streetAddress);
        session.setAttribute("city", city);
        session.setAttribute("state", state);
        session.setAttribute("zipCode", zipCode);
        session.setAttribute("password", password);
        Access<Product> access = new Access(Product.class);
        if (access.existsInColumn("email", email)) {
            this.failResponse(request, response, "There is already an account with that email address!");
        }

        if (!Validator.isValidName(firstName)) {
            this.failResponse(request, response, "First name is Invalid!");
        }

        if (!Validator.isValidName(lastName)) {
            this.failResponse(request, response, "Last name is Invalid!");
        }

        if (!Validator.isValidPassword(password)) {
            this.failResponse(request, response, "Password is Invalid!");
        }

        if (!Validator.isValidEmail(email)) {
            this.failResponse(request, response, "Email Address is Invalid!");
        }

        if (!Validator.isValidUsername(username)) {
            this.failResponse(request, response, "Username is Invalid!");
        }

        if (!Validator.isValidStreetAddress(streetAddress)) {
            this.failResponse(request, response, "Street Address is invalid!");
        }

        if (!Validator.isValidCity(city)) {
            this.failResponse(request, response, "City field is invalid!");
        }

        if (!Validator.isValidState(state)) {
            this.failResponse(request, response, "State is invalid! Must be a state code (i.e. GA, TX, MN, PA, etc)");
        }

        if (!Validator.isValidZipCode(zipCode)) {
            this.failResponse(request, response, "Zip Code is invalid!");
        }

        AddressFormat address = new AddressFormat(streetAddress, city, state, zipCode);
        User user = new User(firstName, lastName, username, password, email, address.toString(), "None");
        user.save();
        session.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-account.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
