//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.peach.sellbuy_ecommerce.util;

public class AddressFormat {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    public AddressFormat(String streetAddress, String city, String state, String zipCode) {
        if (Validator.isValidStreetAddress(streetAddress) && Validator.isValidCity(city) && Validator.isValidState(state) && Validator.isValidZipCode(zipCode)) {
            this.streetAddress = streetAddress;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
        } else {
            throw new IllegalArgumentException("Invalid input for address attributes.");
        }
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public String toString() {
        return this.streetAddress + ", " + this.city + ", " + this.state + " " + this.zipCode;
    }

    public static AddressFormat fromString(String addressString) {
        String[] parts = addressString.split(", ");
        if (parts.length == 3) {
            String[] cityState = parts[1].split(" ");
            if (cityState.length == 2) {
                String streetAddress = parts[0];
                String city = cityState[0];
                String state = cityState[1];
                String zipCode = parts[2];
                if (Validator.isValidStreetAddress(streetAddress) && Validator.isValidCity(city) && Validator.isValidState(state) && Validator.isValidZipCode(zipCode)) {
                    return new AddressFormat(streetAddress, city, state, zipCode);
                }
            }
        }

        throw new IllegalArgumentException("Invalid input string for AddressFormat.");
    }

    public static void main(String[] args) {
        AddressFormat address = new AddressFormat("123 Main St", "Springfield", "IL", "62701");
        System.out.println(address.toString());
        String addressString = "456 Elm St, Shelbyville, KY 40065";
        AddressFormat parsedAddress = fromString(addressString);
        System.out.println(parsedAddress.toString());
    }
}
