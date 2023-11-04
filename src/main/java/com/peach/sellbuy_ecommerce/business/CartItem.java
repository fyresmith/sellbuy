package com.peach.sellbuy_ecommerce.business;

import com.peach.sellbuy_ecommerce.util.Util;

/**
 * This class represents an item in a shopping cart. It contains information about a product and its quantity in the cart.
 */
public class CartItem {
    // Fields representing cart item attributes
    private int cartItemID;
    private Product product;
    private int quantity;

    /**
     * Default constructor for CartItem. Initializes a unique cartItemID and other attributes.
     */
    public CartItem() {}

    /**
     * Constructor for CartItem with detailed information provided.
     * @param product The product added to the cart.
     * @param quantity The quantity of the product in the cart.
     */
    public CartItem(Product product, int quantity) {
        this.cartItemID = Util.generateSevenDigitNumber(); // Generate a unique cart item ID.
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Get the unique ID of the cart item.
     * @return The cart item ID.
     */
    public int getCartItemID() {
        return cartItemID;
    }

    /**
     * Set the unique ID of the cart item.
     * @param cartItemID The cart item ID to set.
     */
    public void setCartItemID(int cartItemID) {
        this.cartItemID = cartItemID;
    }

    /**
     * Get the product associated with this cart item.
     * @return The product in the cart.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Set the product associated with this cart item.
     * @param product The product to set.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Get the quantity of the product in the cart item.
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of the product in the cart item.
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
