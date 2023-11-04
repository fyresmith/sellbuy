package com.peach.sellbuy_ecommerce.util;

import com.peach.sellbuy_ecommerce.business.Access;
import com.peach.sellbuy_ecommerce.business.User;

import java.util.regex.Pattern;


public class Validator {
    public static boolean isValidAddress(String address) {
        if (address == null || address.isEmpty()) {
            return false;
        }

        // You can add more specific checks here based on your requirements.
        // For a basic validation, you can check for the presence of key components.

        // Check for a street address (e.g., "123 Main St").
        if (!address.contains(" ")) {
            return false;
        }

        // Check for a city (e.g., "New York").
        if (!address.contains(",")) {
            return false;
        }

        // Check for a postal code (e.g., "10001").
        if (!address.matches(".*\\b\\d{5}\\b.*")) {
            return false;
        }

        return true;
    }

    public static boolean isValidEmail(String email) {
        // Define a regular expression pattern for basic email validation.
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Access<User> access = new Access<>("user", "userID", User.class);

        if (access.valueExists("email", email)) {
            return false;
        }

        // Use the pattern to match the email address.
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return (password.length() < 20) && (password.length() > 6);
    }

    public static boolean isValidName(String name) {
        return (name.length() < 20) && (name.length() > 1);
    }

    public static boolean isValidUsername(String username) {
        return (username.length() < 20) && (username.length() > 1);
    }
}
