package com.peach.sellbuy_ecommerce.util;

import java.util.Objects;

public class PaymentFormat {
    private final String cardHolderName;
    private final String cardNumber;
    private final String expirationDate;
    private final String cvv;
    private final String cardType;

    public PaymentFormat(String cardHolderName, String cardNumber, String expirationDate, String cvv, String cardType) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.cardType = cardType;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public String getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return cardHolderName + "," + cardNumber + "," + expirationDate + "," + cvv + "," + cardType;
    }

    public static PaymentFormat fromString(String input) {
        String[] parts = input.split(",");
        if (parts.length == 5) {
            String cardHolderName = parts[0];
            String cardNumber = parts[1];
            String expirationDate = parts[2];
            String cvv = parts[3];
            String cardType = parts[4];
            return new PaymentFormat(cardHolderName, cardNumber, expirationDate, cvv, cardType);
        } else {
            throw new IllegalArgumentException("Invalid input format for PaymentFormat");
        }
    }

    public static boolean isValid(String input) {
        try {
            fromString(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentFormat that = (PaymentFormat) o;
        return Objects.equals(cardHolderName, that.cardHolderName) &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(expirationDate, that.expirationDate) &&
                Objects.equals(cvv, that.cvv) &&
                Objects.equals(cardType, that.cardType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardHolderName, cardNumber, expirationDate, cvv, cardType);
    }
}
