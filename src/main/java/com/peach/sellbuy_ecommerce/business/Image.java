package com.peach.sellbuy_ecommerce.business;

import com.peach.sellbuy_ecommerce.util.Data;

import java.io.File;


/**
 * This class represents an image associated with a product in an e-commerce system. Each image has a unique
 * image ID, references a product using a product ID, and provides a URL to the image file.
 */
public class Image {
    // Fields representing image attributes
    private int imageID;
    private int productID;
    private String imageURL;

    /**
     * Default constructor for Image.
     */
    public Image() {
        Access<Image> access = new Access<>("image", "imageID", Image.class);
        this.imageID = access.generateUniquePK();
    }

    /**
     * Constructor for Image with all attributes provided.
     * @param productId The ID of the product that this image is associated with.
     * @param imageURL The URL to the image file.
     */
    public Image(int productId, String imageURL) {
        Access<Image> access = new Access<>("image", "imageID", Image.class);
        this.imageID = access.generateUniquePK();
        this.productID = productId;
        this.imageURL = imageURL;
    }

    /**
     * Get the unique ID of the image.
     * @return The image ID.
     */
    public int getImageID() {
        return imageID;
    }

    /**
     * Set the unique ID of the image.
     * @param imageID The image ID to set.
     */
    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    /**
     * Get the ID of the product associated with this image.
     * @return The product ID.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Set the ID of the product associated with this image.
     * @param productID The product ID to set.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Get the URL to the image file.
     * @return The image URL.
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Set the URL to the image file.
     * @param imageURL The image URL to set.
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Select an image from the database by its primary key (imageID) and populate the current object with the retrieved data.
     * @param PK The primary key (imageID) of the image to select.
     */
    public void select(int PK) {
        Access<Image> access = new Access<>("image", "imageID", Image.class);
        Image new_object = access.select(PK);

        try {
            Access.copyFields(new_object, this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save the current image object to the database.
     */
    public void save() {
        Access<Image> access = new Access<>("image", "imageID", Image.class);
        access.save(this);
    }

    /**
     * Update the current image object in the database.
     */
    public void update() {
        Access<Image> access = new Access<>("image", "imageID", Image.class);
        access.update(this);
    }

    /**
     * Delete the current image object from the database.
     */
    public void delete() {
        Access<Image> access = new Access<>("image", "imageID", Image.class);

        boolean fileDeleted = new File(Data.IMAGE + this.imageURL).delete();

        access.delete(this.getImageID());
    }
}
