package com.peach.sellbuy;

import java.io.*;
import java.util.Date;
import java.util.Objects;

import com.peach.sellbuy.business.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "addReviewServlet", value = "/add-review")
public class AddReviewServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        double rating = 0;

        String text = request.getParameter("reviewText");
        int productID = Integer.parseInt(request.getParameter("productID"));
        rating = Double.parseDouble(request.getParameter("rating"));

        if (Objects.equals(text, "") || text == null) {
            text = "This product is a lifesaver. I don't know how I managed without it. It's worth every penny.";
        }

        Review review = new Review();

        User user = (User) session.getAttribute("user");

        review.setReviewText(text);
        review.setProductID(productID);
        review.setRating((int) (rating * 2));
        review.setDatePosted(new Date());
        review.setUserID(user.getUserID());
        review.save();

//        URI url = Util.appendUri(Util.webPage("product.jsp"), "pid=" + productID);
//
//        assert url != null;
//        response.sendRedirect(url.toString());
        session.setAttribute("alertTitle", "Success!");
        session.setAttribute("alertMessage", "Your review was added to the product!");
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    public void destroy() {
    }
}