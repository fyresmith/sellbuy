package com.peach.sellbuy;

import java.io.*;

import com.peach.sellbuy.business.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "removeCartItemServlet", value = "/remove-cartitem")
public class RemoveCartItemServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        int productID = Integer.parseInt(request.getParameter("productID"));

        cart.delItem(productID);
        session.setAttribute("cart", cart);

        response.sendRedirect("my-cart.jsp");
    }

    public void destroy() {}
}