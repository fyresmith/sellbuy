package com.peach.sellbuy_ecommerce.util;

import com.peach.sellbuy_ecommerce.business.Access;
import com.peach.sellbuy_ecommerce.business.Product;
import com.peach.sellbuy_ecommerce.business.Review;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 * Utility class providing various methods for generating random data and other utility functions.
 */
public class Util {

    /**
     * Generates a random integer within the specified range (inclusive).
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A random integer between min and max.
     * @throws IllegalArgumentException if max is less than or equal to min.
     */
    public static int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Generates a random integer with bias, favoring lower values.
     *
     * @return A random integer with a bias towards lower values.
     */
    public static int generateBiasedRandomNumber() {
        Random random = new Random();

        // Generate a random number between 0 and 100.
        int randomNumber = random.nextInt(101);

        if (randomNumber < 25) {
            // 60% chance to generate numbers less than or equal to 6
            return random.nextInt(7);
        } else {
            // 40% chance to generate numbers between 7 and 10
            return random.nextInt(4) + 7;
        }
    }

    /**
     * Generates a random double within the specified range.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A random double between min and max.
     * @throws IllegalArgumentException if max is less than or equal to min.
     */
    public static double generateRandomDouble(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }

        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    /**
     * Generates a random six-digit number.
     *
     * @return A random six-digit number.
     */
    public static int generateSixDigitNumber() {
        return generateRandomNumber(100000, 999999);
    }

    /**
     * Generates a random seven-digit number.
     *
     * @return A random seven-digit number.
     */
    public static int generateSevenDigitNumber() {
        return generateRandomNumber(1000000, 9999999);
    }

    /**
     * Generates a random double between 0.01 and 500.00 (rounded to two decimal places).
     *
     * @return A random double within the specified range.
     */
    public static double generateRandomDouble() {
        Random random = new Random();
        double min = 0.01;  // Minimum value (greater than 0)
        double max = 500.0; // Maximum value (adjust as needed)

        double randomDouble = min + (max - min) * random.nextDouble();
        return Math.round(randomDouble * 100.0) / 100.0; // Round to two decimal places
    }

    /**
     * Generates a random date within a range (2012 to 2023).
     *
     * @return A random date between 2012 and 2023.
     */
    public static Date generateRandomDate() {
        Random random = new Random();
        int year = random.nextInt(12) + 2012;  // Random year between 2012 and 2023
        int month = random.nextInt(12) + 1;   // Random month between 1 and 12
        int day = random.nextInt(28) + 1;     // Random day between 1 and 28

        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        return calendar.getTime();
    }

    /**
     * Reads a text file and returns its content as an array of strings.
     *
     * @param filePath The path to the text file.
     * @return An array of strings representing the lines of the text file.
     */
    public static String[] textToArray(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines.toArray(new String[0]);
    }

    /**
     * Selects a random item from an array.
     *
     * @param array The array from which to select an item.
     * @param <T>   The type of the items in the array.
     * @return A random item from the array, or null if the array is empty or null.
     */
    public static <T> T selectRandomItem(T[] array) {
        if (array == null || array.length == 0) {
            return null; // Handle the case of an empty or null array.
        }

        Random random = new Random();
        int randomIndex = random.nextInt(array.length);
        return array[randomIndex];
    }

    /**
     * Generates a random list of products.
     *
     * @param size The size of the product list.
     * @return A linked list of random products.
     */
    public static LinkedList<Product> randomProductList(int size) {
        Access<Product> productAccess = new Access<>("product", "productID", Product.class);
        LinkedList<Product> productList = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            int pk = productAccess.getRandomIntegerFromColumn("productID");
            productList.add(productAccess.select(pk));
        }

        return productList;
    }

    /**
     * Generates a random list of reviews.
     *
     * @param size The size of the review list.
     * @return A linked list of random reviews.
     */
    public static LinkedList<Review> randomReviewList(int size) {
        Access<Review> reviewAccess = new Access<>("review", "reviewID", Review.class);
        LinkedList<Review> reviewList = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            int pk = reviewAccess.getRandomIntegerFromColumn("reviewID");
            reviewList.add(reviewAccess.select(pk));
        }

        return reviewList;
    }

    /**
     * Gets the base URL from a given URL.
     *
     * @param url The URL from which to extract the base URL.
     * @return The base URL.
     */
    public static String getBaseURL(String url) {
        try {
            URL u = new URL(url);
            return u.getPath().replace("index.jsp", "");
        } catch (Exception e) {
            return "http://localhost:8080/SellBuy_eCommerce-1.0-SNAPSHOT/";
        }
    }

    public static String webPage(String page) {
        return "http://localhost:8080/SellBuy_eCommerce-1.0-SNAPSHOT/" + page;
    }

    public static String search(String query) {
        return "http://localhost:8080/SellBuy_eCommerce-1.0-SNAPSHOT/search?searchBar=" + query;
    }

    public static URI appendUri(String uri, String appendQuery) {
        try {
            URI oldUri = new URI(uri);
            return new URI(oldUri.getScheme(), oldUri.getAuthority(), oldUri.getPath(),
                    oldUri.getQuery() == null ? appendQuery : oldUri.getQuery() + "&" + appendQuery, oldUri.getFragment());
        } catch (URISyntaxException ignored) {
            return null;
        }
    }

    public static String image(String uniqueID, String size) {
        if (Validator.isImage(uniqueID)) {
            return Util.webPage("image/" + uniqueID);
        } else{
            return "https://m.media-amazon.com/images/I/" + uniqueID + "._US" + size + "_.jpg";
        }
    }

    public static void printLoadingBar(int currentStep, int totalSteps, String data) {
        int barLength = 50; // Length of the loading bar in characters.
        int progress = (int) (((double) currentStep / totalSteps) * barLength);

        StringBuilder loadingBar = new StringBuilder("[");

        for (int i = 0; i < barLength; i++) {
            if (i < progress) {
                loadingBar.append("=");
            } else if (i == progress) {
                loadingBar.append(">");
            } else {
                loadingBar.append(" ");
            }
        }

        loadingBar.append("] " + (int) (((double) currentStep / totalSteps) * 100) + "% " + data);

        System.out.print("\r" + loadingBar.toString());

        if (currentStep == totalSteps) {
            System.out.println(); // Print a newline character to finish the line.
        }
    }

    public static boolean getYesNoChoice(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.print(prompt + " >> ");
            choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("y")) {
                return true;
            } else if (choice.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    public static int getNumberInRange(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int number;

        while (true) {
            System.out.printf(prompt + " (A number between %d and %d) >> ", min, max);

            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if (number >= min && number <= max) {
                    break; // Valid input, exit the loop
                } else {
                    System.out.println("Invalid input. Number is out of range.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume invalid input
            }
        }

        return number;
    }


    public static void shuffleStringArray(String[] array) {
        int n = array.length;
        Random random = new Random();

        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            // Swap array[i] and array[j]
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static String[] collectProductData() {
        String[] array1 = Util.textToArray(Data.testFile("products_1.txt"));
        String[] array2 = Util.textToArray(Data.testFile("products_2.txt"));
        String[] array3 = Util.textToArray(Data.testFile("products_3.txt"));
        String[] array4 = Util.textToArray(Data.testFile("products_4.txt"));

        return Stream.of(array1, array2, array3, array4)
                .flatMap(Stream::of)
                .toArray(String[]::new);
    }

    public static String limitString(String input, int maxLength) {
        if (input.length() <= maxLength) {
            return input;
        } else {
            return input.substring(0, maxLength - 3) + "...";
        }
    }

    public static DecimalFormat priceFormat() {
        return new DecimalFormat("#,###.00");
    }

    public static String styleCategory(String category) {
        // Split the category into words
        String[] words = category.split("_");

        // Capitalize the first letter of each word and join them with spaces
        StringBuilder styledCategory = new StringBuilder();
        for (String word : words) {
            if (styledCategory.length() > 0) {
                styledCategory.append(" "); // Add space between words
            }
            styledCategory.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase());
        }

        return styledCategory.toString();
    }


    public static LinkedList<String> styleCategories(LinkedList<String> categories) {
        LinkedList<String> styledCategories = new LinkedList<>();

        for (String category : categories) {
            String styledCategory = styleCategory(category);

            styledCategories.add(styledCategory);
        }

        return styledCategories;
    }

    public static String generateRandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomCharType = random.nextInt(3); // 0: uppercase letter, 1: lowercase letter, 2: digit

            switch (randomCharType) {
                case 0:
                    stringBuilder.append((char) ('A' + random.nextInt(26)));
                    break;
                case 1:
                    stringBuilder.append((char) ('a' + random.nextInt(26)));
                    break;
                case 2:
                    stringBuilder.append((char) ('0' + random.nextInt(10)));
                    break;
            }
        }

        return stringBuilder.toString();
    }

    public static String rawCategory(String styledCategory) {
        // Split the styled category into words
        String[] words = styledCategory.split(" ");

        // Convert the first letter of each word to lowercase and join with underscores
        StringBuilder originalCategory = new StringBuilder();
        for (String word : words) {
            if (originalCategory.length() > 0) {
                originalCategory.append("_"); // Add underscore between words
            }
            originalCategory.append(word.substring(0, 1).toLowerCase()).append(word.substring(1));
        }

        return originalCategory.toString().toUpperCase();
    }

    public static boolean isRawCategory(String text) {
        // Check if the text is all uppercase and contains underscores
        return text.matches("^[A-Z_]+$");
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
