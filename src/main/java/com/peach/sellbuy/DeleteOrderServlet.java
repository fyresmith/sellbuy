package com.peach.sellbuy;

import java.io.*;

import com.peach.sellbuy.business.Access;
import com.peach.sellbuy.business.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "deleteOrderServlet", value = "/delete-order")
public class DeleteOrderServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        int orderID = Integer.parseInt(request.getParameter("orderID"));

        Access<Order> access = new Access<>(Order.class);
        Order order = access.select(orderID);
        Order.cancelOrder(order);

        session.setAttribute("alertTitle", "Order Canceled!");

        session.setAttribute("alertMessage", "Your order was canceled!");

        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    public void destroy() {
    }
}