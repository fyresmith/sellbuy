package com.peach.sellbuy_ecommerce.business;

import com.peach.sellbuy_ecommerce.util.Util;

/**
 * This class represents a User in an e-commerce system and provides methods for interacting with user data in the database.
 */
public class User {
    // Fields representing user attributes
    private String firstName;
    private int userID;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String shippingAddress;
    private String paymentInformation;

    /**
     * Default constructor for User. Generates a unique 6-digit user ID.
     */
    public User() {
        Access<User> access = new Access<>("user", "userID", User.class);
        this.userID = access.generateUniquePK();
    }

    /**
     * Constructor for User with all attributes provided.
     * @param firstname The user's first name.
     * @param lastName The user's last name.
     * @param username The user's username.
     * @param password The user's password.
     * @param email The user's email.
     * @param shippingAddress The user's shipping address.
     * @param paymentInformation The user's payment information.
     */
    public User(String firstname, String lastName, String username, String password, String email, String shippingAddress, String paymentInformation) {
        Access<User> access = new Access<>("user", "userID", User.class);
        this.userID = access.generateUniquePK();
        this.username = username;
        this.password = password;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.paymentInformation = paymentInformation;
        this.firstName = firstname;
        this.lastName = lastName;
    }

    /**
     * Generates a random 6-digit user ID for the User.
     */
    public void genUserID() {
        Access<User> access = new Access<>("user", "userID", User.class);
        this.userID = access.generateUniquePK();
    }

    /**
     * Get the user's ID.
     * @return The user's ID.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Set the user's ID.
     * @param userID The user's ID to set.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Get the user's first name.
     * @return The user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the user's first name.
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the user's last name.
     * @return The user's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the user's last name.
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the user's full name.
     * @return The user's full name.
     */
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Get the user's username.
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the user's username.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the user's password.
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password.
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the user's email address.
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the user's email address.
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the user's shipping address.
     * @return The user's shipping address.
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Set the user's shipping address.
     * @param shippingAddress The shipping address to set.
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Get the user's payment information.
     * @return The user's payment information.
     */
    public String getPaymentInformation() {
        return paymentInformation;
    }

    /**
     * Set the user's payment information.
     * @param paymentInformation The payment information to set.
     */
    public void setPaymentInformation(String paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    /**
     * Select a user from the database by their primary key (userID) and populate the current object with the retrieved data.
     * @param PK The primary key (userID) of the user to select.
     */
    public void select(int PK) {
        Access<User> access = new Access<>("user", "userID", User.class);
        User new_object = access.select(PK);

        try {
            Access.copyFields(new_object, this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save the current user object to the database.
     */
    public void save() {
        Access<User> access = new Access<>("user", "userID", User.class);
        access.save(this);
    }

    /**
     * Update the current user object in the database.
     */
    public void update() {
        Access<User> access = new Access<>("user", "userID", User.class);
        access.update(this);
    }

    /**
     * Delete the current user object from the database.
     */
    public void delete() {
        Access<User> access = new Access<>("user", "userID", User.class);
        access.delete(this.getUserID());
    }

    /**
     * Generates a string representation of the User object.
     * @return A string representation of the User object's attributes.
     */
    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", userID=" + userID +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", paymentInformation='" + paymentInformation + '\'' +
                '}';
    }
}
