package com.peach.sellbuy_ecommerce;

import java.io.*;
import com.peach.sellbuy_ecommerce.business.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "addToCartServlet", value = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {

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

        if (product.getStockQuantity() <= 0) {
            session.setAttribute("alertTitle", "Item not Added!");
            session.setAttribute("alertMessage", "Your item was not added to the cart because it is out of stock!");
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer);
        }

        cart.addItem(new CartItem(product, quantity));

        session.setAttribute("cart", cart);

        session.setAttribute("alertTitle", "Added to Cart!");

        if (user == null) {
            session.setAttribute("alertMessage", "Your item was added to the cart, but you must first sign in to access it!");
        } else {
            session.setAttribute("alertMessage", "Your item was added to the cart!");
        }

        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    public void destroy() {}
}

