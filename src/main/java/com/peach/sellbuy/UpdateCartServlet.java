package com.peach.sellbuy;

import java.io.*;

import com.peach.sellbuy.business.Cart;
import com.peach.sellbuy.business.CartItem;
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

        Cart cart = (Cart) session.getAttribute("cart");

        CartItem item = cart.getByID(cartID);

        if (quantity < 1) quantity = 1;
        else if (quantity > 500) quantity = 500;
        else if (quantity > item.getProduct().getStockQuantity()) quantity = item.getProduct().getStockQuantity();
        item.setQuantity(quantity);

        session.setAttribute("cart", cart);

        response.sendRedirect("my-cart.jsp");
    }

    public void destroy() {
    }
}