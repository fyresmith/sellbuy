package com.peach.sellbuy_ecommerce.business;

import com.peach.sellbuy_ecommerce.util.PaymentFormat;
import com.peach.sellbuy_ecommerce.util.Util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * This class represents an order within an e-commerce system. An order contains order details such as
 * order ID, user ID, order items, order date, shipping address, and payment method.
 */
public class Order {
    // Fields representing order attributes
    private int orderID;
    private int userID;
    private LinkedList<OrderItem> orderItems;
    private Date orderDate;
    private String shippingAddress;
    private String paymentMethod;

    /**
     * Default constructor for Order.
     */
    public Order() {
        Access<Order> access = new Access<>("order", "orderID", Order.class);
        this.orderID = access.generateUniquePK();
    }

    /**
     * Constructor for Order with all attributes provided.
     * @param userID The user ID associated with this order.
     * @param orderItems A list of order items in the order.
     * @param orderDate The date when the order was placed.
     * @param shippingAddress The address to which the order is shipped.
     * @param paymentMethod The payment method used for the order.
     */
    public Order(int userID, LinkedList<OrderItem> orderItems, Date orderDate, String shippingAddress, String paymentMethod) {
        Access<Order> access = new Access<>("order", "orderID", Order.class);
        this.orderID = access.generateUniquePK();
        this.userID = userID;
        this.orderItems = orderItems;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
    }

    /**
     * Get the unique ID of the order.
     * @return The order ID.
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Set the unique ID of the order.
     * @param orderID The order ID to set.
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Get the user ID associated with this order.
     * @return The user ID.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Set the user ID associated with this order.
     * @param userID The user ID to set.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Get the list of order items in the order.
     * @return The list of order items.
     */
    public LinkedList<OrderItem> getOrderItems() {
        if (this.orderItems == null) {
            populateOrderItems();
        }

        return orderItems;
    }

    /**
     * Set the list of order items in the order.
     * @param orderItems The list of order items to set.
     */
    public void setOrderItems(LinkedList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * Get the date when the order was placed.
     * @return The order date.
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Set the date when the order was placed.
     * @param orderDate The order date to set.
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Get the address to which the order is shipped.
     * @return The shipping address.
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Set the address to which the order is shipped.
     * @param shippingAddress The shipping address to set.
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Get the payment method used for the order.
     * @return The payment method.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Set the payment method used for the order.
     * @param paymentMethod The payment method to set.
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Select an order from the database by its primary key (orderID) and populate the current object with the retrieved data.
     * @param PK The primary key (orderID) of the order to select.
     */
    public void select(int PK) {
        Access<Order> access = new Access<>("order", "orderID", Order.class);
        Order new_object = access.select(PK);

        try {
            Access.copyFields(new_object, this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save the current order object to the database.
     */
    public void save() {
        Access<Order> access = new Access<>("order", "orderID", Order.class);
        access.save(this);
    }

    /**
     * Update the current order object in the database.
     */
    public void update() {
        Access<Order> access = new Access<>("order", "orderID", Order.class);
        access.update(this);
    }

    /**
     * Delete the current order object from the database.
     */
    public void delete() {
        Access<Order> access = new Access<>("order", "orderID", Order.class);
        access.delete(this.getOrderID());
    }

    /**
     * Generate a String representation of the Order object.
     * @return A string representation of the order object.
     */
    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", orderItems=" + orderItems +
                ", orderDate=" + orderDate +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }

    public void populateOrderItems() {
        Access<OrderItem> access = new Access<>(OrderItem.class);
        this.orderItems = access.getAltRows("orderID", orderID);
    }

    public User getUser() {
        Access<User> userAccess = new Access(User.class);
        return userAccess.select(this.userID);
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : this.orderItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        return total;
    }

    public double getTax() {
        double total = getTotal();

        return 0.07 * total;
    }

    public static void cancelOrder(Order order) {
        LinkedList<OrderItem> items = order.getOrderItems();

        for (OrderItem item : items) {
            item.delete();
        }

        order.delete();
    }

    public static String orderCard(Order order) {
        order.populateOrderItems();

        int orderID = order.getOrderID();
        Date date = order.getOrderDate();
        User user = order.getUser();
        String name = user.getName();
        String username = user.getUsername();
        String email = user.getEmail();
        String address = order.getShippingAddress();

        PaymentFormat pf = PaymentFormat.fromString(order.getPaymentMethod());
        String cardNumber = pf.getCardNumber().substring(12, 16);

        DecimalFormat df = Util.priceFormat();

        StringBuilder card = new StringBuilder("""
        <div class="card border border-primary mb-4 shadow-0">
            <div class="card-body pb-0">
                <header class="d-lg-flex">
                    <div class="flex-grow-1">
                        <h6 class="mb-0">Order ID: %s <i class="dot"></i>
                            <span class="text-success"> Shipped</span>
                        </h6>
                        <span class="text-muted">Date: %s</span>
                    </div>
                    <form action="%s">
                        <button class="btn btn-sm btn-outline-danger">Cancel order</button>
                        <input type="hidden" name="orderID" value="%s">
                    </form>
                </header>
                <hr />
                <div class="row">
                    <div class="col-lg-4">
                        <p class="mb-0 text-muted">Contact</p>
                        <p class="m-0">
                            %s <br />
                            Username: %s <br />
                            Email: %s
                        </p>
                    </div>
                    <div class="col-lg-4 border-start">
                        <p class="mb-0 text-muted">Shipping Address</p>
                        <p class="m-0">
                            United States <br />
                            %s
                        </p>
                    </div>
                    <div class="col-lg-4 border-start">
                        <p class="mb-0 text-muted">Payment</p>
                        <p class="m-0">
                            <span class="text-success"> Visa **** %s </span> <br />
                            Shipping fee: $0 <br />
                            Total paid: $%s
                        </p>
                    </div>
                </div>
                <hr />
        <ul class="row list-unstyled">
        """.formatted(orderID, date, Util.webRoot("delete-order-servlet"), orderID, name, username, email, address, cardNumber, df.format(order.getTotal() + order.getTax())));

                for (OrderItem item : order.getOrderItems()) {
                    card.append(OrderItem.orderCard(item));
                }

        card.append("         </ul>\n" +
                "    </div>\n" +
                "</div>\n");

        return card.toString();
    }

}
