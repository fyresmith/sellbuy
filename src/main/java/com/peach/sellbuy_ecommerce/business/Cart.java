package com.peach.sellbuy_ecommerce.business;

import com.peach.sellbuy_ecommerce.util.Util;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents a shopping cart in an e-commerce system. Each cart has a unique cartID, is associated with a user using userID,
 * and contains a list of cart items (CartItems) representing the products added to the cart.
 */
public class Cart {
    // Fields representing cart attributes
    private int cartID;
    private int userID;
    private LinkedList<CartItem> cartItems = new LinkedList<>();;

    /**
     * Default constructor for Cart. Initializes the cartItems list and generates a unique cartID.
     */
    public Cart() {
        this.cartID = Util.generateSevenDigitNumber();
    }

    /**
     * Constructor for Cart with the userID and a list of cart items provided.
     * @param userID The ID of the user associated with the cart.
     * @param cartItems The list of cart items in the cart.
     */
    public Cart(int userID, LinkedList<CartItem> cartItems) {
        this.cartID = Util.generateSevenDigitNumber();
        this.userID = userID;
        this.cartItems = cartItems;
    }

    /**
     * Get the unique ID of the cart.
     * @return The cart ID.
     */
    public int getCartID() {
        return cartID;
    }

    /**
     * Set the unique ID of the cart.
     * @param cartID The cart ID to set.
     */
    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    /**
     * Get the ID of the user associated with this cart.
     * @return The user ID.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Set the ID of the user associated with this cart.
     * @param userID The user ID to set.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Get the list of cart items in the cart.
     * @return The list of cart items.
     */
    public LinkedList<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * Set the list of cart items in the cart.
     * @param cartItems The list of cart items to set.
     */
    public void setCartItems(LinkedList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * Get the number of cart items in the cart.
     * @return The size of the cart (number of items).
     */
    public int getCartSize() {
        return cartItems.size();
    }

    /**
     * Return true if cart is empty.
     * @return True if the cart is empty, false if not.
     */
    public boolean isEmpty() { return cartItems.isEmpty(); }

    /**
     * Add a CartItem to the cart.
     * @param item The CartItem to add to the cart.
     */
    public void addItem(CartItem item) {
        this.cartItems.add(item);
    }

    /**
     * Add a product with a specified quantity to the cart. Creates a CartItem with the product and quantity and adds it to the cart.
     * @param item The Product to add to the cart.
     * @param quantity The quantity of the product to add.
     */
    public void addItem(Product item, int quantity) {
        this.cartItems.add(new CartItem(item, quantity));
    }

    public void delItem(int productID) {
        cartItems.removeIf(cartItem -> cartItem.getCartItemID() == productID);
    }


    public CartItem getByID(int id) {
        for (CartItem cartItem : this.cartItems) {
            if (cartItem.getCartItemID() == id) {
                return cartItem;
            }
        }

        return null;
    }

    public double getTax() {
        double total = getTotal();

        return 0.07 * total;
    }

    public double getTotal() {
        double total = 0;

        for (CartItem cartItem : this.cartItems) {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        return total;
    }

    public void clear() {
        this.cartItems.clear();
    }
}
