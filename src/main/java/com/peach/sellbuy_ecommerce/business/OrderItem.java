package com.peach.sellbuy_ecommerce.business;

/**
 * This class represents an order item within an e-commerce system.
 * An order item is a specific product with a quantity and price associated with it in an order.
 */
public class OrderItem {
    // Fields representing order item attributes
    private int orderID;
    private int orderItemID;
    private int productID;
    private int quantity;
    private double price;

    /**
     * Default constructor for OrderItem.
     */
    public OrderItem() {}

    /**
     * Constructor for OrderItem with all attributes provided.
     * @param orderID The ID of the order to which this order item belongs.
     * @param orderItemID The unique ID of the order item.
     * @param productID The ID of the product associated with this order item.
     * @param quantity The quantity of the product in the order.
     * @param price The price of the order item.
     */
    public OrderItem(int orderID, int orderItemID, int productID, int quantity, double price) {
        this.orderID = orderID;
        this.orderItemID = orderItemID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Get the ID of the order to which this order item belongs.
     * @return The order ID.
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Set the ID of the order to which this order item belongs.
     * @param orderID The order ID to set.
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Get the unique ID of the order item.
     * @return The order item ID.
     */
    public int getOrderItemID() {
        return orderItemID;
    }

    /**
     * Set the unique ID of the order item.
     * @param orderItemID The order item ID to set.
     */
    public void setOrderItemID(int orderItemID) {
        this.orderItemID = orderItemID;
    }

    /**
     * Get the ID of the product associated with this order item.
     * @return The product ID.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Set the ID of the product associated with this order item.
     * @param productID The product ID to set.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Get the quantity of the product in the order item.
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of the product in the order item.
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Get the price of the order item.
     * @return The price of the order item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of the order item.
     * @param price The price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Select an order item from the database by its primary key (orderItemID) and populate the current object with the retrieved data.
     * @param PK The primary key (orderItemID) of the order item to select.
     */
    public void select(int PK) {
        Access<OrderItem> access = new Access<>("order_item", "orderItemID", OrderItem.class);
        OrderItem new_object = access.select(PK);

        try {
            Access.copyFields(new_object, this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save the current order item object to the database.
     */
    public void save() {
        Access<OrderItem> access = new Access<>("order_item", "orderItemID", OrderItem.class);
        access.save(this);
    }

    /**
     * Update the current order item object in the database.
     */
    public void update() {
        Access<OrderItem> access = new Access<>("order_item", "orderItemID", OrderItem.class);
        access.update(this);
    }

    /**
     * Delete the current order item object from the database.
     */
    public void delete() {
        Access<OrderItem> access = new Access<>("order_item", "orderItemID", OrderItem.class);
        access.delete(this.getOrderID());
    }
}
