package com.peach.sellbuy_ecommerce.business;

import com.peach.sellbuy_ecommerce.util.Data;
import com.peach.sellbuy_ecommerce.util.Templates;
import com.peach.sellbuy_ecommerce.util.Util;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * The `Product` class represents a product in an e-commerce system. It contains information about the product,
 * such as its ID, name, description, price, stock quantity, category, seller ID, images, and reviews.
 */
public class Product {
    // Fields representing the attributes of a product
    private int productID;            // The unique identifier for the product.
    private String productName;       // The name of the product.
    private String description;       // A description of the product.
    private double price;            // The price of the product.
    private int stockQuantity;       // The quantity of this product in stock.
    private String productCategory;  // The category to which the product belongs.
    private int sellerID;            // The ID of the seller who listed the product.
    private String keywords;         // the keywords describing the product
    private LinkedList<Image> images; // A list of images associated with the product.
    private LinkedList<Review> reviews; // A list of reviews for the product.

    /**
     * Default constructor for the Product class. Initializes fields to default values.
     */
    public Product() {
        Access<Product> access = new Access<>("product", "productID", Product.class);
        this.productID = access.generateUniquePK();
    }

    /**
     * Parameterized constructor for the Product class.
     * @param name The name of the product.
     * @param description A description of the product.
     * @param price The price of the product.
     * @param stockQuantity The quantity of this product in stock.
     * @param category The category to which the product belongs.
     * @param sellerID The ID of the seller who listed the product.
     */
    public Product(String name, String description, double price, int stockQuantity, String category, int sellerID) {
        Access<Product> access = new Access<>("product", "productID", Product.class);
        this.productID = access.generateUniquePK();
        this.productName = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productCategory = category;
        this.sellerID = sellerID;
        this.populateImages();
        this.populateReviews(); // Populates reviews if not already done.
    }

    /**
     * Get the unique identifier of the product.
     * @return The product's unique identifier.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Set the unique identifier of the product.
     * @param productID The product's unique identifier.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Get the name of the product.
     * @return The name of the product.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Get the keywords of the product.
     * @return The product keywords.
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Set the keywords of the product.
     * @param keywords Product keywords.
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Set the name of the product.
     * @param productName The name of the product.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Get the description of the product.
     * @return The product's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the product.
     * @param description The product's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the price of the product.
     * @return The product's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of the product.
     * @param price The product's price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the stock quantity of the product.
     * @return The stock quantity of the product.
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Set the stock quantity of the product.
     * @param stockQuantity The stock quantity of the product.
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Get the category to which the product belongs.
     * @return The product's category.
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     * Set the category to which the product belongs.
     * @param productCategory The product's category.
     */
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    /**
     * Get the ID of the seller who listed the product.
     * @return The seller's ID.
     */
    public int getSellerID() {
        return sellerID;
    }

    /**
     * Set the ID of the seller who listed the product.
     * @param sellerID The seller's ID.
     */
    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    /**
     * Get the list of images associated with the product.
     * @return A list of images.
     */
    public LinkedList<Image> getImages() {
        return images;
    }

    /**
     * Set the list of images associated with the product.
     * @param images A list of images.
     */
    public void setImages(LinkedList<Image> images) {
        this.images = images;
    }

    /**
     * Get the list of reviews for the product. If the list is null, it will be populated.
     * @return A list of reviews.
     */
    public LinkedList<Review> getReviews() {
        if (this.reviews == null) {
            this.populateReviews(); // Populates reviews if not already done.
        }
        return reviews;
    }



    /**
     * Sets the reviews for the product.
     * @param reviews The list of reviews to set for the product.
     */
    public void setReviews(LinkedList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Generates a string representation of the Product.
     * @return A string containing the product's attributes.
     */
    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", productCategory='" + productCategory + '\'' +
                ", sellerID=" + sellerID +
                ", images=" + images +
                ", reviews=" + reviews +
                '}';
    }

    /**
     * Selects a product from the database based on its primary key.
     * @param PK The primary key of the product to select.
     */
    public void select(int PK) {
        Access<Product> access = new Access<>("product", "productID", Product.class);
        Product new_object = access.select(PK);

        try {
            Access.copyFields(new_object, this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        this.populateReviews();
        this.populateImages();
    }

    /**
     * Saves the product in the database.
     */
    public void save() {
        Access<Product> access = new Access<>("product", "productID", Product.class);
        access.save(this);
    }

    /**
     * Updates the product in the database.
     */
    public void update() {
        Access<Product> access = new Access<>("product", "productID", Product.class);
        access.update(this);
    }

    /**
     * Deletes the product from the database.
     */
    public void delete() {
        Access<Product> access = new Access<>("product", "productID", Product.class);
        access.delete(this.getProductID());
    }

    /**
     * Gets the average rating of the product based on its reviews.
     * @return The average rating of the product.
     */
    public int getRating() {
        populateReviews();

        return Review.reviewAverage(this.reviews);
    }

    /**
     * Retrieves a list of the top N products based on some criteria.
     * @param topN The number of top products to retrieve.
     * @return A list of top products.
     */
    public static LinkedList<Product> topProductList(int topN) {
        LinkedList<Product> productList = new LinkedList<>();
        Product product;

        String selectQuery = "SELECT productID FROM product LIMIT ?"; // MySQL syntax, adjust for your database

        try (Connection connection = DriverManager.getConnection(Data.DATABASE)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, topN);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        product = new Product();
                        product.select(resultSet.getInt(1));
                        productList.add(product);
                    }
                }
            }

            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
            return productList;
        }
    }

    /**
     * Populates the reviews associated with the product from the database.
     */
    public void populateReviews() {
        Access<Review> access = new Access<>("review", "reviewID", Review.class);

        this.reviews = access.getAltRows("productID", this.productID);
    }

    public void populateImages() {
        Access<Image> imageAccess = new Access<>("image", "imageID", Image.class);

        this.images = imageAccess.getAltRows("productID", this.productID);
    }

    /**
     * Checks if a product with the given primary key exists in the database.
     * @param PK The primary key of the product to check for existence.
     * @return True if the product with the given primary key exists; false otherwise.
     */
    public static boolean exists(int PK) {
        Dotenv dotenv = Dotenv.load();
        String database = dotenv.get("DATABASE");

        String selectQuery = "SELECT 1 FROM product WHERE productID = ?";

        try (var connection = DriverManager.getConnection(database);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, PK);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if a row with the value exists.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred or value doesn't exist.
        }
    }

    public static String cartPageCard(Product product) {
        final DecimalFormat df = new DecimalFormat("0.00");
        product.populateImages();

        return "<form class=\"col-lg-3 col-md-6 col-sm-6\" action=\"" + Util.webRoot("add-to-cart-servlet") + "\">\n" +
                "        <div class=\"card px-4 border shadow-0 mb-4 mb-lg-0\">\n" +
                "          <div class=\"mask px-2\" style=\"height: 50px;\">\n" +
                "            <a href=\"#\"><i class=\"fas fa-heart text-primary fa-lg float-end pt-3 m-2\"></i></a>\n" +
                "          </div>\n" +
                "          <a href=\"#\" class=\"\">\n" +
                "            <img src=\"" + Util.image(product.getImages().get(0).getImageURL(), "86") + "\" class=\"card-img-top rounded-2\" />\n" +
                "          </a>\n" +
                "          <div class=\"card-body d-flex flex-column pt-3 border-top\">\n" +
                "            <a href=\"#\" class=\"nav-link\">" + product.getProductName() + "</a>\n" +
                "            <div class=\"price-wrap mb-2\">\n" +
                "              <strong class=\"\">$" + df.format(product.getPrice()) + "</strong>\n" +
                "            </div>\n" +
                "            <div class=\"card-footer d-flex align-items-end pt-3 px-0 pb-0 mt-auto\">\n" +
                "              <input type=\"hidden\" name=\"productID\" value=\"" + product.getProductID() + "\">" +
                "              <button type=\"submit\" class=\"btn btn-outline-primary w-100\">Add to cart</button>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </form>";
    }

    public static String cartCard(CartItem item) {
        final DecimalFormat df = new DecimalFormat("0.00");
        Product product = item.getProduct();
        double price = product.getPrice();
        product.populateImages();

        return "<div class=\"row gy-3 mb-4\">\n" +
                "              <div class=\"col-lg-5\">\n" +
                "                <div class=\"me-lg-5\">\n" +
                "                  <div class=\"d-flex\">\n" +
                "                    <img src=\"" + Util.image(product.getImages().get(0).getImageURL(), "86") + "\" class=\"border rounded me-3\" style=\"width: 96px; height: 96px;\" />\n" +
                "                    <div class=\"\">\n" +
                "                      <a href=\"#\" class=\"nav-link\">" + product.getProductName() + "</a>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "\n" +
                "              <div class=\"col-lg-2 col-sm-6 col-6 d-flex flex-row flex-lg-column flex-xl-row text-nowrap\">\n" +
                "                <div class=\"\">\n" +
                "                  <select style=\"width: 100px;\" class=\"form-select me-4\">\n" +
                "                    <option>1</option>\n" +
                "                    <option>2</option>\n" +
                "                    <option>3</option>\n" +
                "                    <option>4</option>\n" +
                "                  </select>\n" +
                "                </div>\n" +
                "                <div class=\"\">\n" +
                "                  <text class=\"h6\">$" + df.format(price * item.getQuantity()) + "</text> <br />\n" +
                "                  <small class=\"text-muted text-nowrap\"> $" + df.format(price) + " / per item </small>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "              <div class=\"col-lg col-sm-6 d-flex justify-content-sm-center justify-content-md-start justify-content-lg-center justify-content-xl-end mb-2\">\n" +
                "                <form class=\"float-md-end\" action=\"" + Util.webRoot("remove-cart-item") + "\">\n" +
                "                  <button type=\"submit\" class=\"btn btn-light border text-danger icon-hover-danger\">Remove</button>\n" +
                "                  <input type=\"hidden\" name=\"productID\" value=\"" + item.getCartItemID() + "\">" +
                "                </form>\n" +
                "              </div>\n" +
                "            </div>";
    }

    public static String miniProductCard(Product product) {
        final DecimalFormat df = new DecimalFormat("0.00");
        product.populateImages();

        return "<div class=\"d-flex mb-3\">\n" +
                "                       <a data-fslightbox=\"mygalley\" class=\"border rounded-2\" target=\"_blank\" data-type=\"image\" href=\"" + Util.getBaseURL("") + "product.jsp?pid=" + product.getProductID() + "\" class=\"item-thumb\">\n" +
                "                           <img width=\"86\" height=\"86\" class=\"rounded-2\" src=\"" + Util.image(product.getImages().get(0).getImageURL(), "86") + "\" />\n" +
                "                       </a>" +
                "                                <div class=\"info mx-3\">\n" +
                "<a class=\"nav-link mb-1\" href=\"" + Util.getBaseURL("") + "product.jsp?pid=" + product.getProductID() + "\">" + product.getProductName() + "</a>\n" +
                "                                    <strong class=\"text-dark\"> $" + df.format(product.getPrice()) + "</strong>\n" +
                "                                </div>\n" +
                "                            </div>";
    }

    public static String productCard(Product product, String url) {
        final DecimalFormat df = new DecimalFormat("0.00");
        double price = product.getPrice();
        product.populateImages();

        return "<form class=\"col-lg-3 col-md-6 col-sm-6 d-flex\" action=\"" + Util.webRoot("add-to-cart-servlet") + "\">\n" +
                "                <div class=\"card w-100 my-2 shadow-2-strong\">\n" +
                "                    <a href=\"" + Util.appendUri(Util.webRoot("product.jsp"), "pid=" + product.getProductID()) + "\"><img src=\"" + Util.image(product.getImages().get(0).getImageURL(), "300") + "\" class=\"card-img-top\" style=\"aspect-ratio: 1 / 1\" /></a>\n" +
                "                    <div class=\"card-body d-flex flex-column\">\n" +
                "                        <a class=\"text-dark\" href=\"" + Util.getBaseURL(url) + "product.jsp?pid=" + product.getProductID() + "\"><h5 class=\"card-title\">" + product.getProductName() + "</h5></a>\n" +
                "                        <p class=\"card-text\">$" + df.format(price) + "</p>\n" +
                "                        <div class=\"card-footer d-flex align-items-center justify-content-center pt-3 px-0 pb-0 mt-auto\">\n" +
                "                            <button type=\"submit\" class=\"btn border border-primary btn-primary shadow-0 me-1\">Add to cart</button>\n" +
                "                            <input type=\"hidden\" name=\"productID\" value=\"" + product.getProductID() + "\">" +
//                "                            <a href=\"#!\" class=\"btn btn-light border shadow-0 me-1 icon-hover \"><i class=\"fas fa-heart text-secondary\" style=\"font-size: 1.147rem;\"></i></a>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </form>";
    }

    public static String searchCard(Product product) {
        final DecimalFormat df = new DecimalFormat("0.00");
        product.populateImages();

        return "<form class=\"row justify-content-center mb-3\" action=\"" + Util.webRoot("add-to-cart-servlet") + "\">\n" +
                "                    <div class=\"col-md-12\">\n" +
                "                        <div class=\"card shadow-0 border rounded-3\">\n" +
                "                            <div class=\"card-body\">\n" +
                "                                <div class=\"row g-0\">\n" +
                "                                    <div class=\"col-xl-3 col-md-4 d-flex justify-content-center\">\n" +
                "                                        <div class=\"bg-image hover-zoom ripple rounded ripple-surface me-md-3 mb-3 mb-md-0\">\n" +
                "                                            <img src=\"" + Util.image(product.getImages().get(0).getImageURL(), "200") + "\" + Util.generateSixDigitNumber() + \"\" class=\"w-100\" />\n" +
                "                                            <a href=\"" + Util.webRoot("product.jsp?pid=" + product.getProductID()) + "\">\n" +
                "                                                <div class=\"hover-overlay\">\n" +
                "                                                    <div class=\"mask\" style=\"background-color: rgba(253, 253, 253, 0.15);\"></div>\n" +
                "                                                </div>\n" +
                "                                            </a>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"col-xl-6 col-md-5 col-sm-7\">\n" +
                "                                    <a class=\"text-dark\" href=\"" + Util.webRoot("product.jsp?pid=" + product.getProductID()) + "\">\n" +
                "                                        <h5>" + product.getProductName() + "</h5>\n" +
        "                                            </a>\n" +
                                                        Templates.rating(product) +
                "\n" +
                "                                        <p class=\"text mb-4 mb-md-0\">\n" +
                "                                            " + product.getDescription() + "\n" +
                "                                        </p>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"col-xl-3 col-md-3 col-sm-5\">\n" +
                "                                        <div class=\"d-flex flex-row align-items-center mb-1\">\n" +
                "                                            <h4 class=\"mb-1 me-1\">$" + df.format(product.getPrice()) + "</h4>\n" +
//                "                                            <span class=\"text-danger\"><s>$" + df.format(product.getPrice()) + "</s></span>\n" +
                "                                        </div>\n" +
                "                                        <h6 class=\"text-success\">Free shipping</h6>\n" +
                "                                        <div class=\"mt-4\">\n" +
                "                                        <input type=\"hidden\" name=\"productID\" value=\"" + product.getProductID() + "\">" +
                "                                            <button class=\"btn btn-primary shadow-0\" type=\"button\">Add to Cart</button>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </form>";
    }

    public static void sortProductsDescending(LinkedList<Product> productList) {
        productList.sort(Comparator.comparing(Product::getRating).reversed());
    }

    public static void sortProductsRandomly(LinkedList<Product> productList) {
        Collections.shuffle(productList);
    }
}
