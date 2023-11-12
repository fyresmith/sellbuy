package com.peach.sellbuy_ecommerce.util;

import com.peach.sellbuy_ecommerce.business.*;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Seeder class responsible for populating data into the database for testing purposes.
 */
public class Seeder {
    public static String[] usernames = Util.textToArray(Data.testFile("usernames.txt"));
    public static String[] emails = Util.textToArray(Data.testFile("emails.txt"));
    public static String[] addresses = Util.textToArray(Data.testFile("addresses.txt"));
    public static String[] firstNames = Util.textToArray(Data.testFile("firstnames.txt"));
    public static String[] lastNames = Util.textToArray(Data.testFile("lastnames.txt"));
    public static String[] payments = Util.textToArray(Data.testFile("payments.txt"));
    public static String[] reviews = Util.textToArray(Data.testFile("randomReviews.txt"));

    /**
     * Generates a random username from a list of usernames.
     *
     * @return A random username.
     */
    public static String randomUsername() {
        return Util.selectRandomItem(usernames);
    }

    /**
     * Generates a random email from a list of emails.
     *
     * @return A random email address.
     */
    public static String randomEmail() {
        return Util.selectRandomItem(emails);
    }

    /**
     * Generates a random address from a list of addresses.
     *
     * @return A random address.
     */
    public static String randomAddress() {
        String string = Util.selectRandomItem(addresses);

        return string;
    }

    /**
     * Generates a random first name from a list of first names.
     *
     * @return A random first name.
     */
    public static String randomFirstName() {
        String string = Util.selectRandomItem(firstNames);

        return string;
    }

    /**
     * Generates a random last name from a list of last names.
     *
     * @return A random last name.
     */
    public static String randomLastName() {
        String string = Util.selectRandomItem(lastNames);

        return string;
    }

    /**
     * Generates a random payment method from a list of payment methods.
     *
     * @return A random payment method.
     */
    public static String randomPaymentMethod() {
        return Util.selectRandomItem(payments);
    }

    /**
     * Generates a random review from a list of reviews.
     *
     * @return A random review.
     */
    public static String randomReview() {
        return Util.selectRandomItem(reviews);
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
        String insertQuery = "INSERT INTO user (username, firstName, lastName, password, email, shippingAddress, paymentInformation, userID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        int userID = 2;

        try {
            PreparedStatement insertStatement = DB.getInstance().getConnection().prepareStatement(insertQuery);

            int batchSize = 100; // Adjust the batch size as needed

            for (int i = 0; i < count; i++) {
                insertStatement.setString(1, randomUsername());
                insertStatement.setString(2, randomFirstName());
                insertStatement.setString(3, randomLastName());
                insertStatement.setString(4, generateRandomPassword(10));
                insertStatement.setString(5, randomEmail());
                insertStatement.setString(6, randomAddress());
                insertStatement.setString(7, randomPaymentMethod());
                insertStatement.setInt(8, userID);
                insertStatement.addBatch();

                userID += 1;

                if (i % batchSize == 0) {
                    insertStatement.executeBatch();
                }

                Util.printLoadingBar(i, count, " populating user #" + i);
            }

            // Execute any remaining batches
            insertStatement.executeBatch();

            Util.printLoadingBar(1, 1, " DONE POPULATING USERS! (" + count + " users)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Populates the "review" table with random review data.
     */
    public static void populateReviews() {
        Access<User> userAccess = new Access<>("user", "userID", User.class);
        Access<Product> productAccess = new Access<>("product", "productID", Product.class);
        int counter = 0;

        LinkedList<Product> productList = productAccess.getTable();
        LinkedList<Integer> userIDs = userAccess.getColumn("userID");
        int reviewID = 10000000;

        String query = "INSERT INTO review (productID, userID, rating, reviewText, datePosted, reviewID) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement insertStatement = DB.getInstance().getConnection().prepareStatement(query);

            for (Product product : productList) {
                int batchSize = 100;  // Adjust the batch size as needed

                for (int j = 0; j < Util.generateRandomNumber(5, 80); j++) {
                    // Set parameters for the insert statement
                    insertStatement.setInt(1, product.getProductID());
                    insertStatement.setInt(2, userIDs.get(new Random().nextInt(userIDs.size())));
                    insertStatement.setInt(3, Util.generateBiasedRandomNumber());
                    insertStatement.setString(4, randomReview());
                    insertStatement.setDate(5, new java.sql.Date(Util.generateRandomDate().getTime()));
                    insertStatement.setInt(6, reviewID);

                    reviewID += 1;

                    insertStatement.addBatch();

                    if (counter % batchSize == 0) {
                        insertStatement.executeBatch();
                        DB.getInstance().getConnection();
                    }

                    counter += 1;
                }

                // Execute any remaining batches
                if (counter % batchSize == 0) {
                    insertStatement.executeBatch();
                }

                Util.printLoadingBar(productList.indexOf(product), productList.size(), " populating reviews for product \"" + product.getProductName() + "\"");
            }

            Util.printLoadingBar(1, 1, " DONE POPULATING REVIEWS! (" + counter + " reviews)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateProducts(int count) {
        // Create a prepared statement for product insertion
        String productInsertSQL = "INSERT INTO product (productID, productCategory, productName, keywords, description, price, stockQuantity, sellerID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String imageInsertSQL = "INSERT INTO image (productID, imageURL, imageID) VALUES (?, ?, ?)";

        Access<User> userAccess = new Access<>("user", "userID", User.class);
        LinkedList<Integer> userIDs = userAccess.getColumn("userID");

        try {
            PreparedStatement productInsertStatement = DB.getInstance().getConnection().prepareStatement(productInsertSQL);
            PreparedStatement imageInsertStatement = DB.getInstance().getConnection().prepareStatement(imageInsertSQL);

            // Define the batch size
            int batchSize = 100;
            int batchCount = 0;

            // Read data from a text file, shuffle it, and store it in a string array
            String[] file = Util.collectProductData();
            Util.shuffleStringArray(file);

            // Create an instance of Access to interact with the "user" table in the database
            String[] data;
            String[] secondary;
            Image image = new Image();
            Product product = new Product();
            int imageBatchSize;

            int productID = 10000000;
            int imageID = 10000000;
            int imageCounter = 0;

            // Loop to populate the "product" and "image" tables with data
            for (int i = 0; i < count; i++) {
                imageBatchSize = 0;
                // Parse the data from the shuffled array into an array of strings
                data = parseStringToArray(file[i]);

                // Create a new Product object and set its properties
                product.setProductID(productID);
                product.setProductCategory(data[1]);
                product.setProductName(data[2]);
                product.setKeywords(data[3]);
                product.setDescription(data[4]);
                product.setPrice(Util.generateRandomDouble(10.00, 1599.00));
                product.setStockQuantity(Util.generateRandomNumber(1, 200));
                product.setSellerID(userIDs.get(new Random().nextInt(userIDs.size())));

                productID += 1;

                // Add product and image data to the batch
                productInsertStatement.setInt(1, product.getProductID());
                productInsertStatement.setString(2, product.getProductCategory());
                productInsertStatement.setString(3, product.getProductName());
                productInsertStatement.setString(4, product.getKeywords());
                productInsertStatement.setString(5, product.getDescription());
                productInsertStatement.setDouble(6, product.getPrice());
                productInsertStatement.setInt(7, product.getStockQuantity());
                productInsertStatement.setInt(8, product.getSellerID());
                productInsertStatement.addBatch();

                // Create an Image object and set its properties
                image.setProductID(product.getProductID());
                image.setImageURL(data[5]);
                image.setImageID(imageID);

                imageID += 1;
                imageCounter += 1;

                imageInsertStatement.setInt(1, image.getProductID());
                imageInsertStatement.setString(2, image.getImageURL());
                imageInsertStatement.setInt(3, image.getImageID());
                imageInsertStatement.addBatch();

                secondary = splitStringToArray(data[6]);

                Util.printLoadingBar(i, count, " populating image \"" + image.getImageURL() + "\"");

                for (String imageURL : secondary) {
                    // Create an Image object and set its properties
                    image.setImageURL(imageURL);
                    image.setImageID(imageID);

                    imageID += 1;
                    imageBatchSize += 1;

                    imageInsertStatement.setInt(1, image.getProductID());
                    imageInsertStatement.setString(2, image.getImageURL());
                    imageInsertStatement.setInt(3, image.getImageID());
                    imageInsertStatement.addBatch();

                    Util.printLoadingBar(i, count, " populating image \"" + image.getImageURL() + "\"");
                }

                // Execute the batch when it reaches the defined batch size
                if (batchCount % batchSize == batchSize - 1 || i == count - 1) {
                    productInsertStatement.executeBatch();
                    imageInsertStatement.executeBatch();
                    DB.getInstance().getConnection();
                }

                Util.printLoadingBar(i, count, " populating product \"" + product.getProductName() + "\"");

                batchCount++;
            }

            // Execute any remaining batches
            if (batchCount % batchSize != 0) {
                productInsertStatement.executeBatch();
                imageInsertStatement.executeBatch();
            }

            Util.printLoadingBar(1, 1, " DONE POPULATING PRODUCTS & IMAGES! (" + count + " products, " + imageCounter + " images)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        return new int[]{100, 500, 1000, 2000, 4000, 8000, 12000, 16000};
    }

    public static int[] productBuildSizes() {
        return new int[]{1000, 2000, 5000, 8000, 10000, 15000, 20000, 25000};
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
            menu.addItem("Mini (" + userSizes[1] + " users, " + productSizes[1] + " products, ~" + shortenNumber(productSizes[1] * 5L) + "-" + shortenNumber(productSizes[1] * 80L) + " reviews)");
            menu.addItem("Small (" + userSizes[2] + " users, " + productSizes[2] + " products, ~" + shortenNumber(productSizes[2] * 5L) + "-" + shortenNumber(productSizes[2] * 80L) + " reviews)");
            menu.addItem("Medium (" + userSizes[3] + " users, " + productSizes[3] + " products, ~" + shortenNumber(productSizes[3] * 5L) + "-" + shortenNumber(productSizes[3] * 80L) + " reviews)");
            menu.addItem("Large (" + userSizes[4] + " users, " + productSizes[4] + " products, ~" + shortenNumber(productSizes[4] * 5L) + "-" + shortenNumber(productSizes[4] * 80L) + " reviews)");
            menu.addItem("Massive (" + userSizes[5] + " users, " + productSizes[5] + " products, ~" + shortenNumber(productSizes[5] * 5L) + "-" + shortenNumber(productSizes[5] * 80L) + " reviews)");
            menu.addItem("Excessive (" + userSizes[6] + " users, " + productSizes[6] + " products, ~" + shortenNumber(productSizes[6] * 5L) + "-" + shortenNumber(productSizes[6] * 80L) + " reviews)");
            menu.addItem("Exit");

            int choice = menu.run();

            if (choice == 6) {
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
