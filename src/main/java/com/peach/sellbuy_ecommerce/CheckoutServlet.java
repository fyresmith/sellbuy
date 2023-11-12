package com.peach.sellbuy_ecommerce;

import com.peach.sellbuy_ecommerce.business.Access;
import com.peach.sellbuy_ecommerce.business.Cart;
import com.peach.sellbuy_ecommerce.business.CartItem;
import com.peach.sellbuy_ecommerce.business.Order;
import com.peach.sellbuy_ecommerce.business.OrderItem;
import com.peach.sellbuy_ecommerce.business.User;
import com.peach.sellbuy_ecommerce.util.PaymentFormat;
import jakarta.servlet.RequestDispatcher;
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
        name = "checkoutServlet",
        value = {"/checkout-servlet"}
)
public class CheckoutServlet extends HttpServlet {
    public CheckoutServlet() {
    }

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String cardType = request.getParameter("cardType");
        String nameOnCard = request.getParameter("nameOnCard");
        String cardNumber = request.getParameter("cardNumber");
        String expiration = request.getParameter("expiration");
        String cvv = request.getParameter("cvv");
        Cart cart = (Cart)session.getAttribute("cart");
        User user = (User)session.getAttribute("user");
        PaymentFormat payment = new PaymentFormat(nameOnCard, cardNumber, expiration, cvv, cardType);
        Order order = new Order();
        order.setUserID(user.getUserID());
        order.setOrderDate(new Date());
        order.setShippingAddress(user.getShippingAddress());
        order.setPaymentMethod(payment.toString());
        int orderID = order.getOrderID();
        order.save();

        OrderItem orderItem;
        for(Iterator var14 = cart.getCartItems().iterator(); var14.hasNext(); orderItem = null) {
            CartItem item = (CartItem)var14.next();
            orderItem = new OrderItem();
            orderItem.setOrderID(orderID);
            orderItem.setPrice(item.getProduct().getPrice() * (double)item.getQuantity());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setProductID(item.getProduct().getProductID());
            Access<OrderItem> access = new Access(OrderItem.class);
            access.save(orderItem);
        }

        cart.clear();
        session.setAttribute("cart", cart);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("success.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}