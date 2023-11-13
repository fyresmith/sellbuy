package com.peach.sellbuy.util;

import com.peach.sellbuy.business.Access;
import com.peach.sellbuy.business.User;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    public Validator() {
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Access<User> access = new Access("user", "userID", User.class);
        return access.existsInColumn("email", email) ? false : pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password.length() < 20 && password.length() > 6;
    }

    public static boolean isValidName(String name) {
        return name.length() < 20 && name.length() > 1;
    }

    public static boolean isValidUsername(String username) {
        return username.length() < 20 && username.length() > 1;
    }

    public static boolean isValidStreetAddress(String streetAddress) {
        return streetAddress != null && streetAddress.matches("^[\\w\\s,-]+");
    }

    public static boolean isValidCity(String city) {
        return city != null && city.matches("^[A-Za-z\\s-]+");
    }

    public static boolean isValidState(String state) {
        return state != null && state.matches("^[A-Z]{2}$");
    }

    public static boolean isValidZipCode(String zipCode) {
        return zipCode != null && zipCode.matches("^\\d{5}(-\\d{4})?$");
    }

    public static boolean isValidCardNumber(String cardNumber) {
        cardNumber = cardNumber.replaceAll("\\D", "");
        if (cardNumber.length() >= 13 && cardNumber.length() <= 19) {
            int sum = 0;
            boolean doubleDigit = false;

            for(int i = cardNumber.length() - 1; i >= 0; --i) {
                int digit = Character.getNumericValue(cardNumber.charAt(i));
                if (doubleDigit) {
                    digit *= 2;
                    if (digit > 9) {
                        digit -= 9;
                    }
                }

                sum += digit;
                doubleDigit = !doubleDigit;
            }

            return sum % 10 == 0;
        } else {
            return false;
        }
    }

    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList("png", "jpg", "jpeg", "gif", "bmp", "webp", "avif");

    public static boolean isImage(String filePath) {
        String lowerCaseFilePath = filePath.toLowerCase();

        for (String extension : SUPPORTED_EXTENSIONS) {
            if (lowerCaseFilePath.endsWith("." + extension)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isPath(String filePath) {
        if (filePath.contains("png") || filePath.contains("jpg")) {
            return true;
        } else {
            return false;
        }
//        Path path = Paths.get(filePath);
//        return Files.exists(path) && Files.isRegularFile(path);
    }


    public static boolean isValidCVV(String cvv) {
        cvv = cvv.replaceAll("\\D", "");
        int cvvLength = cvv.length();
        return cvvLength == 3 || cvvLength == 4;
    }
}
