package com.peach.sellbuy.util;

import java.util.ArrayList;
import java.util.Scanner;

public class TextMenu {
    private ArrayList<String> menuItems;
    private String name;

    public TextMenu(String name) {
        this.name = name;
        this.menuItems = new ArrayList<>();
    }

    public void addItem(String item) {
        menuItems.add(item);
    }

    public int run() {
        if (menuItems.isEmpty()) {
            System.out.println("No menu items to display.");
            return 0;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(" == " + this.name.toUpperCase() + " == ");
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.println((i + 1) + ". " + menuItems.get(i));
            }

            int choice;

            try {
                System.out.print(">> ");
                choice = scanner.nextInt();
                if (choice < 1 || choice > menuItems.size()) {
                    System.out.println("Invalid option. Please select a valid option.");
                    continue;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            System.out.println("You have selected: " + menuItems.get(choice - 1));
            return choice;
        }
    }

    public static void main(String[] args) {
        TextMenu menu = new TextMenu("DB Loader");
        menu.addItem("Small");
        menu.addItem("Medium");
        menu.addItem("Large");
        menu.run();
    }
}