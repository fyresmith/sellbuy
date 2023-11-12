package com.peach.sellbuy_ecommerce.business;

import com.peach.sellbuy_ecommerce.util.Util;

import java.text.DecimalFormat;

/**
 * This class represents an item in a shopping cart. It contains information about a product and its quantity in the cart.
 */
public class CartItem {
    // Fields representing cart item attributes
    private int cartItemID;
    private Product product;
    private int quantity;

    /**
     * Default constructor for CartItem. Initializes a unique cartItemID and other attributes.
     */
    public CartItem() {}

    /**
     * Constructor for CartItem with detailed information provided.
     * @param product The product added to the cart.
     * @param quantity The quantity of the product in the cart.
     */
    public CartItem(Product product, int quantity) {
        this.cartItemID = Util.generateSevenDigitNumber(); // Generate a unique cart item ID.
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Get the unique ID of the cart item.
     * @return The cart item ID.
     */
    public int getCartItemID() {
        return cartItemID;
    }

    /**
     * Set the unique ID of the cart item.
     * @param cartItemID The cart item ID to set.
     */
    public void setCartItemID(int cartItemID) {
        this.cartItemID = cartItemID;
    }

    /**
     * Get the product associated with this cart item.
     * @return The product in the cart.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Set the product associated with this cart item.
     * @param product The product to set.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Get the quantity of the product in the cart item.
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of the product in the cart item.
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static String checkoutCard(CartItem item) {
        final DecimalFormat df = Util.priceFormat();

        int quantity = item.getQuantity();
        String imageURL = Util.image(item.getProduct().getImages().get(0).getImageURL(), "96");
        String baseURL = Util.getBaseURL("");
        String productID = String.valueOf(item.getProduct().getProductID());
        String fullName = item.getProduct().getProductName();
        String productName = Util.limitString(fullName, 50);
        String priceFormatted = df.format(item.getProduct().getPrice());

        return """
                <div class="d-flex align-items-center mb-4">
                    <div class="me-3 position-relative">
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill badge-secondary">
                            %s
                        </span>
                        <img src="%s" style="height: 96px; width: 96x;" class="img-sm rounded border" />
                    </div>
                    <div class="">
                        <a title="%s" href="%sproduct.jsp?pid=%s" class="nav-link">
                          %s
                        </a>
                        <div class="price text-muted">Total: $%s</div>
                    </div>
                </div>
                """.formatted(quantity, imageURL, fullName, baseURL, productID, productName, priceFormatted);
    }
}
