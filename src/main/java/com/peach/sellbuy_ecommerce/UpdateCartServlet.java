package com.peach.sellbuy_ecommerce;

import java.io.*;
import java.util.LinkedList;
import java.util.Objects;

import com.peach.sellbuy_ecommerce.business.Cart;
import com.peach.sellbuy_ecommerce.business.CartItem;
import com.peach.sellbuy_ecommerce.business.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "updateCartServlet", value = "/update-cart")
public class UpdateCartServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int cartID = Integer.parseInt(request.getParameter("cartID"));

        if (quantity < 1) quantity = 1;
        else if (quantity > 20) quantity = 20;

        Cart cart = (Cart) session.getAttribute("cart");

        cart.getByID(cartID).setQuantity(quantity);

        session.setAttribute("cart", cart);

        response.sendRedirect("my-cart.jsp");
    }

    public void destroy() {
    }
}