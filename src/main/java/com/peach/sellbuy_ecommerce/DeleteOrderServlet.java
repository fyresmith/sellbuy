package com.peach.sellbuy_ecommerce;

import java.io.*;

import com.peach.sellbuy_ecommerce.business.Access;
import com.peach.sellbuy_ecommerce.business.Order;
import com.peach.sellbuy_ecommerce.business.User;
import com.peach.sellbuy_ecommerce.util.Validator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "deleteOrderServlet", value = "/delete-order-servlet")
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

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("success.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}