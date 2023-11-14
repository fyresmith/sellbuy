package com.peach.sellbuy;

import com.peach.sellbuy.business.Cart;
import com.peach.sellbuy.business.CartItem;
import com.peach.sellbuy.business.Order;
import com.peach.sellbuy.business.OrderItem;
import com.peach.sellbuy.business.User;
import com.peach.sellbuy.util.PaymentFormat;
import com.peach.sellbuy.util.Util;
import com.peach.sellbuy.util.WebSeeder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

@WebServlet(
        name = "clearDatabase",
        value = {"/clear-database"}
)
public class ClearDB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebSeeder.clearDB();

        response.sendRedirect(Util.webPage("generator.jsp"));
    }
}
