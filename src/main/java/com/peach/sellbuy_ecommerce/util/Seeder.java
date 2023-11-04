package com.peach.sellbuy_ecommerce.util;

import com.peach.sellbuy_ecommerce.business.*;
import org.w3c.dom.Text;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Seeder class responsible for populating data into the database for testing purposes.
 */
public class Seeder {
    /**
     * Generates a random username from a list of usernames.
     *
     * @return A random username.
     */
    public static String randomUsername() {
        String[] file = Util.textToArray(Data.testFile("usernames.txt"));
        return Util.selectRandomItem(file);
    }

    /**
     * Generates a random email from a list of emails.
     *
     * @return A random email address.
     */
    public static String randomEmail() {
        String[] file = Util.textToArray(Data.testFile("emails.txt"));
        return Util.selectRandomItem(file);
    }

    /**
     * Generates a random address from a list of addresses.
     *
     * @return A random address.
     */
    public static String randomAddress() {
        String[] file = Util.textToArray(Data.testFile("addresses.txt"));
        String string = Util.selectRandomItem(file);

        return string;
    }

    /**
     * Generates a random first name from a list of first names.
     *
     * @return A random first name.
     */
    public static String randomFirstName() {
        String[] file = Util.textToArray(Data.testFile("firstnames.txt"));
        String string = Util.selectRandomItem(file);

        return string;
    }

    /**
     * Generates a random last name from a list of last names.
     *
     * @return A random last name.
     */
    public static String randomLastName() {
        String[] file = Util.textToArray(Data.testFile("lastnames.txt"));
        String string = Util.selectRandomItem(file);

        return string;
    }

    /**
     * Generates a random payment method from a list of payment methods.
     *
     * @return A random payment method.
     */
    public static String randomPaymentMethod() {
        String[] file = Util.textToArray(Data.testFile("payments.txt"));
        return Util.selectRandomItem(file);
    }

    /**
     * Generates a random review from a list of reviews.
     *
     * @return A random review.
     */
    public static String randomReview() {
        String[] file = Util.textToArray(Data.testFile("randomReviews.txt"));
        return Util.selectRandomItem(file);
    }

    public static String generateRandomPassword(int length) {
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String digitChars = "0123456789";
        String specialChars = "!@#$%^&*()-_+=<>?";

        String allChars = uppercaseChars + lowercaseChars + digitChars + specialChars;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allChars.length());
            password.append(allChars.charAt(randomIndex));
        }

        return password.toString();
    }

    /**
     * Populates the "user" table with random user data.
     *
     * @param count The number of users to generate and insert into the database.
     */
    public static void populateUsers(int count) {
        User user;

        for (int i = 0; i < count; i++) {
            user = new User();
            user.setUsername(randomUsername());
            user.setFirstName(randomFirstName());
            user.setLastName(randomLastName());
            user.setPassword(generateRandomPassword(10));
            user.setEmail(randomEmail());
            user.setShippingAddress(randomAddress());
            user.setPaymentInformation(randomPaymentMethod());

            user.save();

            Util.printLoadingBar(i, count, " populating user \"" + user.getUsername() + "\"");
        }

        Util.printLoadingBar(1, 1, " DONE POPULATING USERS! (" + count + " populated)");
    }

    /**
     * Populates the "review" table with random review data.
     */
    public static void populateReviews() {
        Access<User> userAccess = new Access<>("user", "userID", User.class);
        Access<Product> productAccess = new Access<>("product", "productID", Product.class);
        int counter = 0;

        LinkedList<Product> productList = productAccess.getTable();

        Product product;
        Review review;

        for (int i = 0; i < productList.size(); i++) {
            product = productList.get(i);

            for (int j = 0; j < Util.generateRandomNumber(5, 80); j++) {
                review = new Review(product.getProductID(),
                        userAccess.getRandomIntegerFromColumn("userID"), Util.generateBiasedRandomNumber(),
                        randomReview(), Util.generateRandomDate());
                review.save();
                counter += 1;
            }

            Util.printLoadingBar(i, productList.size(), " populating reviews for product \"" + product.getProductName() + "\"");
        }

        Util.printLoadingBar(1, 1, " DONE POPULATING REVIEWS! (" + counter + " populated)");
    }

    /**
     *
     * @param count
     */
    public static void populateProducts(int count) {
        // Read data from a text file, shuffle it, and store it in a string array.
        String[] file = Util.collectProductData();
        Util.shuffleStringArray(file);

        // Create an instance of Access to interact with the "user" table in the database.
        Access<User> userAccess = new Access<>("user", "userID", User.class);

        // Create a linked list to store test data.
        String[] data;
        String[] secondary;
        Image image;
        Product product;
        String test;

        // Loop to populate the "product" and "image" tables with data.
        for (int i = 0; i < count; i++) {
            // Parse the data from the shuffled array into an array of strings.
            data = parseStringToArray(file[i]);

            try {
                test = data[6];
            } catch(Exception e) {
                System.out.println("\n" + file[i]);
            }

            // Create a new Product object and set its properties.
            product = new Product();
            product.setProductCategory(data[1]);
            product.setProductName(data[2]);
            product.setKeywords(data[3]);
            product.setDescription(data[4]);
            product.setPrice(Util.generateRandomDouble(10.00, 1599.00));
            product.setStockQuantity(Util.generateRandomNumber(1, 200));

            // Set the Seller ID for the product by fetching a random user ID from the "user" table.
            product.setSellerID(userAccess.getRandomIntegerFromColumn("userID"));

            // Create an Image object and set its properties.
            image = new Image();
            image.setProductID(product.getProductID());
            image.setImageURL(data[5]);

            // Save the image to the "image" table in the database.
            image.save();

            Util.printLoadingBar(i, count, " populating image \"" + image.getImageURL() + "\"");

            // Split the secondary image URLs and create Image objects for each URL.
            secondary = splitStringToArray(data[6]);
            for (String imageURL : secondary) {
                image = new Image();
                image.setProductID(product.getProductID());
                image.setImageURL(imageURL);
                // Save each image to the "image" table.
                image.save();

                Util.printLoadingBar(i, count, " populating image \"" + image.getImageURL() + "\"");
            }

            // Save the product to the "product" table in the database.
            product.save();

            Util.printLoadingBar(i, count, " populating product \"" + product.getProductName() + "\"");
        }

        Util.printLoadingBar(1, 1, " DONE POPULATING PRODUCTS & IMAGES! (" + count + " populated)");
    }


    public static String[] parseStringToArray(String input) {
        // Remove the surrounding square brackets
        String withoutBrackets = input.substring(1, input.length() - 1);

        // Split by commas, while considering single quotes
        String[] parts = withoutBrackets.split("',\\s*'");

        // Remove single quotes from each part and trim
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].replaceAll("^'|'$", "").trim();
        }

        return parts;
    }

    public static String[] splitStringToArray(String input) {
        // Split the input string by comma
        String[] parts = input.split(",");

        return parts;
    }

    /**
     * Clears all data from a specific table in the database.
     *
     * @param tableName The name of the table to be cleared.
     */
    public static void clearTable(String tableName) {
        String deleteQuery = "DELETE FROM " + tableName;

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Cleared " + rowsAffected + " rows from the table: " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears all data from multiple tables in the database.
     */
    public static void clearDB() {
        clearTable("user");
        clearTable("product");
        clearTable("image");
        clearTable("order");
        clearTable("order_item");
        clearTable("review");
        System.out.println();
    }

    public static int[] userBuildSizes() {
        return new int[]{100, 200, 500, 1000, 2000, 5000, 10000, 20000};
    }

    public static int[] productBuildSizes() {
        return new int[]{100, 500, 3000, 10000, 30000, 50000, 70000, 80000};
    }

    public static void populateDatabase(int users, int products) {
        System.out.println("\nPOPULATING DATABASE WITH " + users + " users AND " + products + " products!");

        clearDB();

        populateUsers(users);
        populateProducts(products);
        populateReviews();

        System.out.println("\nDATABASE POPULATED :)");
    }

    public static String shortenNumber(long number) {
        if (number < 1000) {
            return Long.toString(number); // No need to shorten
        } else if (number < 1000000) {
            if (number % 1000 == 0) {
                return String.format("%dK", number / 1000);
            } else {
                return String.format("%.1fK", number / 1000.0);
            }
        } else {
            if (number % 1000000 == 0) {
                return String.format("%dM", number / 1000000);
            } else {
                return String.format("%.1fM", number / 1000000.0);
            }
        }
    }

    public static void main(String[] args) {
        TextMenu main = new TextMenu("Main Menu");
        main.addItem("Select From Preset Database Dimensions");
        main.addItem("Input Custom Database Dimensions");

        int menuChoice = main.run();

        if (menuChoice == 1) {

            int[] userSizes = userBuildSizes();
            int[] productSizes = productBuildSizes();

            TextMenu menu = new TextMenu("Test Database Builder");
            menu.addItem("Light (" + userSizes[0] + " users, " + productSizes[0] + " products, ~" + shortenNumber(productSizes[0] * 5L) + "-" + shortenNumber(productSizes[0] * 80L) + " reviews)");
            menu.addItem("Extremely Small (" + userSizes[1] + " users, " + productSizes[1] + " products, ~" + shortenNumber(productSizes[1] * 5L) + "-" + shortenNumber(productSizes[1] * 80L) + " reviews)");
            menu.addItem("Small (" + userSizes[2] + " users, " + productSizes[2] + " products, ~" + shortenNumber(productSizes[2] * 5L) + "-" + shortenNumber(productSizes[2] * 80L) + " reviews)");
            menu.addItem("Medium (" + userSizes[3] + " users, " + productSizes[3] + " products, ~" + shortenNumber(productSizes[3] * 5L) + "-" + shortenNumber(productSizes[3] * 80L) + " reviews)");
            menu.addItem("Large (" + userSizes[4] + " users, " + productSizes[4] + " products, ~" + shortenNumber(productSizes[4] * 5L) + "-" + shortenNumber(productSizes[4] * 80L) + " reviews)");
            menu.addItem("Extra Large (" + userSizes[5] + " users, " + productSizes[5] + " products, ~" + shortenNumber(productSizes[5] * 5L) + "-" + shortenNumber(productSizes[5] * 80L) + " reviews)");
            menu.addItem("Extremely Large (" + userSizes[6] + " users, " + productSizes[6] + " products, ~" + shortenNumber(productSizes[6] * 5L) + "-" + shortenNumber(productSizes[6] * 80L) + " reviews)");
            menu.addItem("Exit");

            int choice = menu.run();

            if (choice == 8) {
                System.out.println("Exiting program.");
                return;
            }

            populateDatabase(userSizes[choice - 1], productSizes[choice - 1]);
        } else {
            int users = Util.getNumberInRange("Enter a number of users", 1, 100000);
            int products = Util.getNumberInRange("Enter a number of products", 1, 110000);

            if (Util.getYesNoChoice("Are you sure you would like to generate this database? y/n")) {
                populateDatabase(users, products);
            } else {
                System.out.println("Exiting program.");
            }
        }
    }
}
