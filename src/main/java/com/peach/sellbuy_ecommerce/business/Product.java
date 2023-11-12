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
    public double getRating() {
        populateReviews();

        return (double) Review.reviewAverage(this.reviews) / 2;
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

    public static String recommendedItemsCard(Product product) {
        final DecimalFormat df = Util.priceFormat();
        product.populateImages();

        String baseURL = Util.getBaseURL("");
        String webRoot = Util.webRoot("add-to-cart");
        String productID = String.valueOf(product.getProductID());
        String imageURL = Util.image(product.getImages().get(0).getImageURL(), "86");
        String productName = Util.limitString(product.getProductName(), 50);
        String priceFormatted = df.format(product.getPrice());

        return """
        <div class="col-lg-3 col-md-6 col-sm-6">
            <div class="card px-4 border shadow-0">
                <a href="%sproduct.jsp?pid=%s" class="">
                    <img src="%s" class="card-img-top rounded-2" />
                </a>
                <div class="card-body d-flex flex-column pt-3 border-top">
                    <a href="%sproduct.jsp?pid=%s" class="nav-link">%s</a>
                    <div class="price-wrap mb-2">
                      <strong class="">$%s</strong>
                    </div>
                    <form class="card-footer d-flex align-items-end pt-3 px-0 pb-0 mt-auto" action="%s">
                      <input type="hidden" name="productID" value="%s">
                      <button type="submit" class="btn btn-outline-primary w-100">Add to cart</button>
                    </form>
                </div>
            </div>
        </div>
                """.formatted(baseURL, productID, imageURL, baseURL, productID, productName, priceFormatted, webRoot, productID);
    }

    public static String cartPageCard(Product product) {
        final DecimalFormat df = Util.priceFormat();
        product.populateImages();

        String webRoot = Util.webRoot("add-to-cart");
        String imageURL = Util.image(product.getImages().get(0).getImageURL(), "86");
        String productName = product.getProductName();
        String priceFormatted = df.format(product.getPrice());
        String productID = String.valueOf(product.getProductID());

        return """
        <form class="col-lg-3 col-md-6 col-sm-6" action="%s">
            <div class="card px-4 border shadow-0 mb-4 mb-lg-0">
                <div class="mask px-2" style="height: 50px;">
                    <a href="#"><i class="fas fa-heart text-primary fa-lg float-end pt-3 m-2"></i></a>
                </div>
                <a href="#">
                    <img src="%s" class="card-img-top rounded-2" />
                </a>
                <div class="card-body d-flex flex-column pt-3 border-top">
                    <a href="#" class="nav-link">%s</a>
                    <div class="price-wrap mb-2">
                        <strong class="">$%s</strong>
                    </div>
                    <div class="card-footer d-flex align-items-end pt-3 px-0 pb-0 mt-auto">
                        <input type="hidden" name="productID" value="%s">
                        <button type="submit" class="btn btn-outline-primary w-100">Add to cart</button>
                    </div>
                </div>
            </div>
        </form>
        """.formatted(webRoot, imageURL, productName, priceFormatted, productID);
    }


    public static String cartCard(CartItem item) {
        final DecimalFormat df = Util.priceFormat();
        Product product = item.getProduct();
        double price = product.getPrice();
        product.populateImages();

        String imageURL = Util.image(product.getImages().get(0).getImageURL(), "86");
        String productName = product.getProductName();
        String totalPrice = df.format(price * item.getQuantity());
        String pricePerItem = df.format(price);
        int cartItemID = item.getCartItemID();
        String removeAction = Util.webRoot("remove-cartitem");

        return """
                <div class="row gy-3 mb-4">
                    <div class="col-lg-5">
                        <div class="me-lg-5">
                            <div class="d-flex">
                                <img src="%s" class="border rounded me-3" style="width: 96px; height: 96px;" />
                                <div>
                                    <a href="#" class="nav-link">%s</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 col-sm-6 col-6 d-flex flex-row flex-lg-column flex-xl-row text-nowrap">
                        <form class="product-form" action="%s">
                            <div style="width: 100px" class="form-outline me-4">
                                <input value="%s" type="number" name="quantity" id="quantity" step="1" min="1" max="20" class="quantity-input form-control" />
                                <input type="hidden" name="cartID" value="%s">
                                <label class="form-label" for="quantity">Quantity</label>
                            </div>
                        </form>
                        <div>
                            <text class="h6">$%s</text> <br />
                            <small class="text-muted text-nowrap"> $%s / per item </small>
                        </div>
                    </div>
                    <div class="col-lg col-sm-6 d-flex justify-content-sm-center justify-content-md-start justify-content-lg-center justify-content-xl-end mb-2">
                        <form class="float-md-end" action="%s">
                            <button type="submit" class="btn btn-light border text-danger icon-hover-danger">Remove</button>
                            <input type="hidden" name="productID" value="%s">
                        </form>
                    </div>
                </div>
                """.formatted(imageURL, productName, Util.webRoot("update-cart"), item.getQuantity(), item.getCartItemID(), totalPrice, pricePerItem, removeAction, cartItemID);
    }


    public static String otherItemsCard(Product product) {
        final DecimalFormat df = Util.priceFormat();
        product.populateImages();

        String baseURL = Util.getBaseURL("");
        String productID = String.valueOf(product.getProductID());
        String imageURL = Util.image(product.getImages().get(0).getImageURL(), "86");
        String productName = Util.limitString(product.getProductName(), 50);
        String priceFormatted = df.format(product.getPrice());

        return """
        <div class="d-flex mb-3">
            <a data-fslightbox="mygalley" class="border rounded-2" target="_blank" data-type="image" href="%sproduct.jsp?pid=%s" class="item-thumb">
                <img width="86" height="86" class="rounded-2" src="%s" />
            </a>
            <div class="info mx-3">
                <a class="nav-link mb-1" href="%sproduct.jsp?pid=%s">%s</a>
                <strong class="text-dark"> $%s</strong>
            </div>
        </div>
        """.formatted(baseURL, productID, imageURL, baseURL, productID, productName, priceFormatted);
    }


    public static String homepageCard(Product product, String url) {
        final DecimalFormat df = Util.priceFormat();
        double price = product.getPrice();
        product.populateImages();

        String webRoot = Util.webRoot("add-to-cart");
        String productID = String.valueOf(product.getProductID());
        String imageURL = Util.image(product.getImages().get(0).getImageURL(), "300");
        String productName = Util.limitString(product.getProductName(), 50);
        String baseURL = Util.getBaseURL(url);

        return """
        <form class="col-lg-3 col-md-6 col-sm-6 d-flex" action="%s">
            <div class="card w-100 my-2 shadow-2-strong">
                <a href="%s"><img src="%s" class="card-img-top" style="aspect-ratio: 1 / 1" /></a>
                <div class="card-body d-flex flex-column">
                    <a class="text-dark" href="%sproduct.jsp?pid=%s"><h5 class="card-title">%s</h5></a>
                    <p class="card-text">$%s</p>
                    <div class="card-footer d-flex align-items-center justify-content-center pt-3 px-0 pb-0 mt-auto">
                        <button type="submit" class="btn border border-primary btn-primary shadow-0 me-1">Add to cart</button>
                        <input type="hidden" name="productID" value="%s">
                    </div>
                </div>
            </div>
        </form>
        """.formatted(webRoot, Util.appendUri(Util.webRoot("product.jsp"), "pid=" + productID),
                imageURL, baseURL, productID, productName, df.format(price), productID);
    }


    public static String searchCard(Product product) {
        final DecimalFormat df = Util.priceFormat();
        product.populateImages();

        String webRoot = Util.webRoot("");
        String productID = String.valueOf(product.getProductID());
        String imageURL = Util.image(product.getImages().get(0).getImageURL(), "200");
        String productName = product.getProductName();
        String description = product.getDescription();
        String priceFormatted = df.format(product.getPrice());

        return """
        <form class="row justify-content-center mb-3" action="%sadd-to-cart">
            <div class="col-md-12">
                <div class="card shadow-0 border rounded-3">
                    <div class="card-body">
                        <div class="row g-0">
                            <div class="col-xl-3 col-md-4 d-flex justify-content-center">
                                <div class="bg-image hover-zoom ripple rounded ripple-surface me-md-3 mb-3 mb-md-0">
                                    <img src="%s" class="w-100" />
                                    <a href="%sproduct.jsp?pid=%s">
                                        <div class="hover-overlay">
                                            <div class="mask" style="background-color: rgba(253, 253, 253, 0.15);"></div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="col-xl-6 col-md-5 col-sm-7">
                                <a class="text-dark" href="%sproduct.jsp?pid=%s">
                                    <h5>%s</h5>
                                </a>
                                %s
                                <p class="text mb-4 mb-md-0">
                                    %s
                                </p>
                            </div>
                            <div class="col-xl-3 col-md-3 col-sm-5 px-3">
                                <div class="d-flex flex-row align-items-center mb-1">
                                    <h4 class="mb-1 me-1">$%s</h4>
                                </div>
                                <h6 class="text-success">Free shipping</h6>
                                <div class="mt-4">
                                    <input type="hidden" name="productID" value="%s">
                                    <button class="btn btn-primary shadow-0" type="submit">Add to Cart</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        """.formatted(webRoot, imageURL, webRoot, productID, webRoot, productID, productName, Templates.rating(product), description, priceFormatted, productID);
    }

    public static String homeViewCard(Product product) {
        final DecimalFormat df = Util.priceFormat();
        product.populateImages();

        String webRoot = Util.webRoot("");
        String productID = String.valueOf(product.getProductID());
        String imageURL = Util.image(product.getImages().get(0).getImageURL(), "200");
        String productName = product.getProductName();
        String priceFormatted = df.format(product.getPrice());

        return """
               <form class="col-lg-3 col-md-6 col-sm-6" action="%sadd-to-cart">
                   <div class="card my-2 shadow-0">
                       <a href="%sproduct.jsp?pid=%s" class="img-wrap">
                           <img src="%s" class="card-img-top" style="aspect-ratio: 1 / 1">
                       </a>
                       <div class="card-body p-0 pt-3">
                           <input type="hidden" name="productID" value="%s">
                           <button type="submit" class="btn btn-light border px-2 pt-2 float-end icon-hover"><i class="fas fa-shopping-cart fa-lg px-1 text-secondary"></i></button>
                           <h5 class="card-title">$%s</h5>
                           <p class="card-text mb-0 pt-2">%s</p>
                       </div>
                   </div>
               </form>
                """.formatted(webRoot, webRoot, productID, imageURL, productID, priceFormatted, productName);
    }


    public static void sortProductsDescending(LinkedList<Product> productList) {
        productList.sort(Comparator.comparing(Product::getRating).reversed());
    }

    public static void sortProductsRandomly(LinkedList<Product> productList) {
        Collections.shuffle(productList);
    }
}
