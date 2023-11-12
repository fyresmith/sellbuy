package com.peach.sellbuy_ecommerce;

import java.io.*;

import com.peach.sellbuy_ecommerce.business.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "addToCartServlet", value = "/add-to-cart-servlet")
public class AddToCartServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        int productID = Integer.parseInt(request.getParameter("productID"));
        int quantity = 0;

        if (request.getParameter("quantity") != null) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }

        Product product = new Product();
        product.select(productID);

        if (quantity <= 0) {
            quantity = 1;
        }

        if (cart == null) {
            cart = new Cart();
        }

        cart.addItem(new CartItem(product, quantity));

        session.setAttribute("cart", cart);

        if (user == null) {
            session.setAttribute("failMessage", "Your item was added, but you must sign in to access your cart!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("my-cart.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {}
}