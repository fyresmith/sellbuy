package com.peach.sellbuy.business;

import java.util.Date;
import java.util.LinkedList;


/**
 * This class represents product reviews in an e-commerce system. Reviews contain information about a product's quality, written by users.
 * Each review has a unique reviewID, associated with a product and a user, and contains attributes such as rating, text, and posting date.
 */
public class Review {
    // Fields representing review attributes
    private int reviewID;
    private int productID;
    private int userID;
    private int rating;
    private String reviewText;
    private Date datePosted;

    /**
     * Default constructor for Review. Initializes the review's unique reviewID using a database access utility.
     */
    public Review() {
        Access<Review> reviewAccess = new Access<>("review", "reviewID", Review.class);
        this.reviewID = reviewAccess.generateUniquePK();
    }

    /**
     * Constructor for Review with detailed information provided.
     * @param productID The ID of the product associated with the review.
     * @param userID The ID of the user who wrote the review.
     * @param rating The rating given to the product in the review.
     * @param reviewText The text content of the review.
     * @param datePosted The date when the review was posted.
     */
    public Review(int productID, int userID, int rating, String reviewText, Date datePosted) {
        Access<Review> reviewAccess = new Access<>("review", "reviewID", Review.class);
        this.reviewID = reviewAccess.generateUniquePK();
        this.productID = productID;
        this.userID = userID;
        this.rating = rating;
        this.reviewText = reviewText;
        this.datePosted = datePosted;
    }

    /**
     * Get the unique ID of the review.
     * @return The review ID.
     */
    public int getReviewID() {
        return reviewID;
    }

    /**
     * Set the unique ID of the review.
     * @param reviewID The review ID to set.
     */
    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    /**
     * Get the ID of the product associated with this review.
     * @return The product ID.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Set the ID of the product associated with this review.
     * @param productID The product ID to set.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Get the ID of the user who wrote this review.
     * @return The user ID.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Set the ID of the user who wrote this review.
     * @param userID The user ID to set.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Get the rating given to the product in the review.
     * @return The rating.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Set the rating given to the product in the review.
     * @param rating The rating to set.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Get the text content of the review.
     * @return The review text.
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Set the text content of the review.
     * @param reviewText The review text to set.
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    /**
     * Get the date when the review was posted.
     * @return The posting date.
     */
    public Date getDatePosted() {
        return datePosted;
    }

    /**
     * Set the date when the review was posted.
     * @param datePosted The posting date to set.
     */
    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    /**
     * Override of the toString method to provide a string representation of the review.
     * @return A formatted string containing the review's attributes.
     */
    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", productID=" + productID +
                ", userID=" + userID +
                ", rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                ", datePosted=" + datePosted +
                '}';
    }

    /**
     * Select a review by its primary key (PK) using a database access utility.
     * @param PK The primary key of the review to select.
     */
    public void select(int PK) {
        Access<Review> access = new Access<>("review", "reviewID", Review.class);
        Review new_object = access.select(PK);

        try {
            Access.copyFields(new_object, this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save the review to the database using a database access utility.
     */
    public void save() {
        Access<Review> access = new Access<>("review", "reviewID", Review.class);
        access.save(this);
    }

    /**
     * Update the review in the database using a database access utility.
     */
    public void update() {
        Access<Review> access = new Access<>("review", "reviewID", Review.class);
        access.update(this);
    }

    /**
     * Delete the review from the database using a database access utility.
     */
    public void delete() {
        Access<Review> access = new Access<>("review", "reviewID", Review.class);
        access.delete(this.getReviewID());
    }

    /**
     * Calculate the average rating from a list of reviews.
     * @param reviews A list of reviews to calculate the average rating from.
     * @return The average rating as an integer.
     */
    public static int reviewAverage(LinkedList<Review> reviews) {
        int count = 0;
        double sum = 0;

        for (Review review : reviews) {
            sum += review.getRating();
            count += 1;
        }

        return (int) Math.round(sum / count);
    }
}
