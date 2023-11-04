package com.peach.sellbuy_ecommerce.util;

import com.peach.sellbuy_ecommerce.business.Product;
import com.peach.sellbuy_ecommerce.business.Review;
import com.peach.sellbuy_ecommerce.business.User;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Templates {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static String cartModal() {
        return "<div class=\"modal fade\" id=\"cartAddModal\" tabindex=\"-1\" aria-labelledby=\"cartAddModalLabel\" aria-hidden=\"true\">\n" +
                "  <div class=\"modal-dialog\">\n" +
                "    <div class=\"modal-content\">\n" +
                "      <div class=\"modal-header\">\n" +
                "        <h5 class=\"modal-title\" id=\"cartAddModalLabel\">Item Added to Cart!</h5>\n" +
                "        <button type=\"button\" class=\"btn-close\" data-mdb-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
                "      </div>\n" +
                "      <div class=\"modal-body\">...</div>\n" +
                "      <div class=\"modal-footer\">\n" +
                "        <button type=\"button\" class=\"btn btn-secondary\" data-mdb-dismiss=\"modal\">Continue Shopping</button>\n" +
                "        <button type=\"button\" class=\"btn btn-primary\">My Cart</button>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>";
    }

    public static String reviewCard(Review review) {
        User user = new User();
        user.select(review.getUserID());
        String pattern = "dd MMMM, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return "<div class=\"border rounded-2 px-3 py-2 bg-white mb-3\">\n" +
                "                    <div class=\"d-flex flex-row my-2\">\n" +
                "                        <span class=\"text-bold ms-0 mx-2 fw-bold\">" + user.getName() + "</span>\n" +
                "                        " + rating(review.getRating()) +
                "                        <span class=\"text-muted\"><i class=\"fas fa-comments fa-sm mx-1\"></i>Reviewed on " + simpleDateFormat.format(review.getDatePosted()) + "</span>\n" +
                "                    </div>\n" +
                "\n" +
                "                    <div>\n" +
                "                        <p class=\"p-1\">\n" +
                                            review.getReviewText() + "\n" +
                "                        </p>\n" +
                "                    </div>\n" +
                "                </div>";
    }

    public static String locationJumbo(String title, String[] locations) {
        StringBuilder string = new StringBuilder("<div class=\"bg-primary mb-4\">\n" +
                "  <div class=\"container py-4\">\n" +
                "    <h3 class=\"text-white mt-2\">" + title + "</h3>\n" +
                "    <!-- Breadcrumb -->\n" +
                "    <nav class=\"d-flex mb-2\">\n" +
                "      <h6 class=\"mb-0\">");

        for (int i = 0; i < locations.length - 1; i++) {
            string.append("<a href=\"\" class=\"text-white-50\">"  + locations[i] + "</a>\n" +
                    "        <span class=\"text-white-50 mx-2\"> > </span>");
        }

        string.append("<a href=\"\" class=\"text-white\"><u>" + locations[locations.length - 1] + "</u></a>\n" +
                        "      </h6>\n" +
                        "    </nav>\n" +
                        "    <!-- Breadcrumb -->\n" +
                        "  </div>\n" +
                        "</div>\n" +
                        "<!-- Heading -->\n" +
                        "</header>");


        return string.toString();
    }

    public static String rating(int initRating) {
        double rating = (double) initRating / 2;

        int starCount = 5; // Number of stars
        int fullStars = (int) rating; // Number of full stars

        StringBuilder stars = new StringBuilder("<div class=\"text-warning mb-1 me-2\">");

        for (int i = 1; i <= starCount; i++) {
            if (i <= fullStars) {
                stars.append("\t<i class=\"fas fa-star\"></i>\n");
            } else if (i - 1 < rating) {
                stars.append("\t<i class=\"fas fa-star-half-alt\"></i>\n");
            } else {
                stars.append("\t<i class=\"far fa-star\"></i>\n");
            }
        }

        stars.append("<span class=\"ms-1\">").append(rating).append("</span></div>");

        return stars.toString();
    }

    public static String rating(Product product) {
        int initRating = product.getRating();

        double rating = (double) initRating / 2;

        int starCount = 5; // Number of stars
        int fullStars = (int) rating; // Number of full stars

        StringBuilder stars = new StringBuilder("<div class=\"text-warning mb-1 me-2\">");

        for (int i = 1; i <= starCount; i++) {
            if (i <= fullStars) {
                stars.append("\t<i class=\"fas fa-star\"></i>\n");
            } else if (i - 1 < rating) {
                stars.append("\t<i class=\"fas fa-star-half-alt\"></i>\n");
            } else {
                stars.append("\t<i class=\"far fa-star\"></i>\n");
            }
        }

        stars.append("<span class=\"ms-1\">").append(rating).append("<span class=\"text-muted mx-2\"><i class=\"fas fa-user-edit\"></i> " + product.getReviews().size() + " Reviews</span></span></div>");

        return stars.toString();
    }

    public static void main(String[] args) {
        System.out.println(rating(1));
    }
}
