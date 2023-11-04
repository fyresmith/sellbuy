package com.peach.sellbuy_ecommerce.business;

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
    public Order() {}

    /**
     * Constructor for Order with all attributes provided.
     * @param orderID The unique ID of the order.
     * @param userID The user ID associated with this order.
     * @param orderItems A list of order items in the order.
     * @param orderDate The date when the order was placed.
     * @param shippingAddress The address to which the order is shipped.
     * @param paymentMethod The payment method used for the order.
     */
    public Order(int orderID, int userID, LinkedList<OrderItem> orderItems, Date orderDate, String shippingAddress, String paymentMethod) {
        this.orderID = orderID;
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
}
