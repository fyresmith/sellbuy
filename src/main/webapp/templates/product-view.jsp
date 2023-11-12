<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.*" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/24/23
  Time: 7:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int productID = Integer.parseInt(request.getParameter("pid"));
    Product product = new Product();
    product.select(productID);

    final DecimalFormat df = Util.priceFormat();

    LinkedList<Review> reviews = product.getReviews();

    LinkedList<Product> productList = Util.randomProductList(5);

    User user = (User) session.getAttribute("user");
%>

<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/lightslider/1.1.6/css/lightslider.min.css'>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0.5/dist/fancybox.css'>

<style>
    .vrmedia-gallery {
        max-width: 567px;
    }

    .vrmedia-gallery img {
        object-fit: cover;
        width: 100%;
        border-radius: 5px;
    }

    .vrmedia-gallery .lSGallery {
        display: inline-flex;
    }

    .vrmedia-gallery .lSGallery li {
        border-radius: 12px !important;
    }

    .vrmedia-gallery .lSGallery li.active {
        border: 1px solid #242423;
    }

</style>

<script>
    $("[type='number']").keypress(function (evt) {
        evt.preventDefault();
    });

    function add(element_value) {
        let element = document.getElementById(element_value);
        let countString = element.value;
        let count = parseInt(countString)

        if (count) {
            count = count + 1;
        } else {
            count = 1;
        }

        element.value = count;
    }

    function subtract(element_value) {
        let element = document.getElementById(element_value);
        let countString = element.value;
        let count = parseInt(countString)

        if (Number.isInteger(count)) {
            if (count  > 0) {
                count = count - 1;
            }
        } else {
            count = 0;
        }

        element.value = count;
    }
</script>

<!-- content -->
<section class="py-5">
    <div class="container">
        <div class="row gx-5">
            <div class="vrmedia-gallery">
                <ul class="ecommerce-gallery">
                    <% for (Image image : product.getImages()) { %>
                        <li data-fancybox="gallery" data-src="<%= Util.image(image.getImageURL(), "622") %>" data-thumb="<%= Util.image(image.getImageURL(), "622") %>">
                            <img src="<%= Util.image(image.getImageURL(), "622") %>">
                        </li>
                    <% } %>
                </ul>
            </div>
            <main class="col-lg-6">
                <div class="ps-lg-3">
                    <h4 class="title text-dark">
                        <%= product.getProductName() %>
                    </h4>
                    <div class="d-flex flex-row my-3">
                        <%= Templates.rating(product) %>

                        <span class="text-success ms-2">In stock</span>
                    </div>

                    <div class="mb-3">
                        <span class="h5">$<%= df.format(product.getPrice()) %></span>
                        <span class="text-muted">/per item</span>
                    </div>

                    <p>
                        <%= product.getDescription() %>
                    </p>

<%--                    <div class="row">--%>
<%--                        <dt class="col-3">Type:</dt>--%>
<%--                        <dd class="col-9">Regular</dd>--%>

<%--                        <dt class="col-3">Color</dt>--%>
<%--                        <dd class="col-9">Brown</dd>--%>

<%--                        <dt class="col-3">Material</dt>--%>
<%--                        <dd class="col-9">Cotton, Jeans</dd>--%>

<%--                        <dt class="col-3">Brand</dt>--%>
<%--                        <dd class="col-9">Reebook</dd>--%>
<%--                    </div>--%>

                    <hr />

                    <div class="row mb-4">
<%--                        <div class="col-md-4 col-6">--%>
<%--                            <label class="mb-2">Size</label>--%>
<%--                            <select class="form-select border border-secondary" style="height: 35px;">--%>
<%--                                <option>Small</option>--%>
<%--                                <option>Medium</option>--%>
<%--                                <option>Large</option>--%>
<%--                            </select>--%>
<%--                        </div>--%>
                        <!-- col.// -->
                        <form action="<%= Util.webRoot("add-to-cart-servlet") %>">
                            <div class="col-md-4 col-6 mb-3">
                                <div class="input-group mb-3" style="width: 170px;">
                                    <button class="btn btn-white border border-secondary px-3" onclick="subtract('quantity');" type="button" data-mdb-ripple-color="dark">
                                        <i class="fas fa-minus"></i>
                                    </button>
                                    <input type="text" name="quantity" id="quantity" class="form-control text-center border border-secondary" value="1" aria-label="text" aria-describedby="button-addon1" />
                                    <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                                    <button class="btn btn-white border border-secondary px-3" onclick="add('quantity');" type="button" data-mdb-ripple-color="dark">
                                        <i class="fas fa-plus"></i>
                                    </button>
                                </div>
                            </div>

                            <div class="d-flex align-items-center">
                                <a href="#" class="btn btn-warning shadow-0 me-2">Buy now</a>
                                <div class="mb-0">
                                    <button type="submit" class="btn btn-primary shadow-0">
                                        <i class="me-1 fa fa-shopping-basket"></i> Add to cart
                                    </button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </main>
        </div>
    </div>
</section>
<!-- content -->
<section class="bg-light border-top py-4">
    <div class="container">
        <div class="row gx-4">
            <div class="col-lg-8 mb-4">
                <!-- REVIEW CARD -->
                <%
                    int top = 10;

                    if (reviews.size() < top) {
                        top = reviews.size();
                    }

                    Collections.shuffle(reviews);

                for (int i = 0; i < top; i++) { %>
                    <%= Templates.reviewCard(reviews.get(i)) %>
                <% } %>

                <% if (user == null) { %>
                    <div class="border rounded-2 px-3 py-2 bg-white mb-3"><span class="fw-bold">Sign in to give a review!</span></div>
                <% } else { %>
                    <form class="border rounded-2 px-3 py-2 bg-white mb-3" action="<%= Util.webRoot("add-review-servlet") %>">
                        <div class="form-outline my-2">
                            <textarea class="form-control" id="reviewText" name="reviewText" rows="4"></textarea>
                            <label class="form-label" for="reviewText">Review</label>
                        </div>

                        <div class="row mb-2">
                            <div class="col-md-3">
                                <div class="form-outline">
                                    <input value="0" type="number" name="rating" id="rating" step="0.5" min="0" max="5" class="form-control" />
                                    <label class="form-label" for="rating">Rating</label>
                                </div>
                            </div>
                            <input type="hidden" name="productID" id="productID" value="<%= product.getProductID() %>">
                            <input type="hidden" name="prevURL" value="<%= Util.appendUri(Util.webRoot("product.jsp"), "pid=" + product.getProductID()) %>">
                            <div class="col-md-9">
                                <button type="submit" class="btn btn-primary btn-block">
                                    Submit!
                                </button>
                            </div>
                        </div>
                    </form>
                <% } %>
            </div>


            <div class="col-lg-4">
                <div class="px-0 border rounded-2 shadow-0">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Other items</h5>

                            <% for (Product productItem : productList) { %>
                                <%= Product.otherItemsCard(productItem) %>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/lightslider/1.1.6/js/lightslider.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0.5/dist/fancybox.umd.js'></script>
<script>
    jQuery(document).ready(function(){jQuery(".ecommerce-gallery").lightSlider({gallery:true,item:1,loop:true,thumbItem:4,thumbMargin:10,});});
</script>