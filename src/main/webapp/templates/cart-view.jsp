<%@ page import="com.peach.sellbuy.util.Templates" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Random" %>
<%@ page import="com.peach.sellbuy.util.Util" %>
<%@ page import="com.peach.sellbuy.business.*" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 3:55 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  Cart cart = (Cart) session.getAttribute("cart");
  if (cart == null) {
    cart = new Cart();
    session.setAttribute("cart", cart);
  }

  final DecimalFormat df = Util.priceFormat();

  Access<Product> access = new Access<>("product", "productID", Product.class);
  LinkedList<Product> products = access.getRandomObjects(4);
%>

<% if (!cart.isEmpty()) { %>
<!-- cart + summary -->
<section class="bg-light my-5">
  <div class="container">
    <div class="row">
      <!-- cart -->
        <div class="col-lg-9">

        <div class="card border shadow-0 d-lg-block">
          <div class="m-4">
              <h4 class="card-title mb-4">Your Shopping Cart</h4>

            <% for (CartItem item : cart.getCartItems()) {%>
              <%= Product.cartCard(item) %>
            <% } %>

          </div>

            <div class="border-top pt-4 mx-4 mb-4">
              <p><i class="fas fa-truck text-muted fa-lg"></i> Free Delivery within 1-2 weeks</p>
            </div>
        </div>
      </div>
        <div class="col-lg-3">
          <div class="card mb-3 border shadow-0">
            <div class="card-body">
              <form>
                <div class="form-group">
                  <label class="form-label">Have coupon?</label>
                  <div class="input-group">
                    <input type="text" class="form-control border" name="" placeholder="Coupon code" />
                    <button class="btn btn-light border">Apply</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
          <div class="card shadow-0 border">
            <div class="card-body">
              <div class="d-flex justify-content-between">
                <p class="mb-2">Pre-Tax:</p>
                <p class="mb-2">$<%= df.format(cart.getTotal()) %></p>
              </div>
<%--              <div class="d-flex justify-content-between">--%>
<%--                <p class="mb-2">Discount:</p>--%>
<%--                <p class="mb-2 text-success">-$60.00</p>--%>
<%--              </div>--%>
              <div class="d-flex justify-content-between">
                <p class="mb-2">Tax:</p>
                <p class="mb-2">$<%= df.format(cart.getTax()) %></p>
              </div>
              <hr />
              <div class="d-flex justify-content-between">
                <p class="mb-2">Total Price:</p>
                <p class="mb-2 fw-bold">$<%= df.format(cart.getTotal() + cart.getTax()) %></p>
              </div>

              <div class="mt-3">
                <a href="checkout.jsp" class="btn btn-success w-100 shadow-0 mb-2"> Make Purchase </a>
                <a href="index.jsp" class="btn btn-light w-100 border mt-2"> Back to shop </a>
              </div>
            </div>
          </div>
        </div>
      <!-- summary -->
    </div>
  </div>
</section>
<!-- cart + summary -->
<section>
  <div class="container my-5">
    <header class="mb-4">
      <h3>Recommended items</h3>
    </header>

    <div class="row">
      <% for (Product product : products) { %>
        <%= Product.recommendedItemsCard(product) %>
      <% } %>
    </div>
  </div>
</section>
<!-- Recommended -->
<% } else { %>
<div class="empty-cart-card card border shadow-2-strong container my-5 d-flex justify-content-center align-items-center flex-column my-2 py-5">
  <img width="250" height="250" src="<%= Util.webPage("image/empty-cart.svg") %>" alt="Empty Cart" class="img-fluid mb-4" style="filter: invert(24%) sepia(8%) saturate(5765%) hue-rotate(170deg) brightness(95%) contrast(88%);" />
  <h1 class="h1">Your Shopping Cart is Empty</h1>
  <p class="lead">Looks like you haven't added any items to your cart yet.</p>
  <a href="<%= Util.search("Cellular") %>" class="btn btn-primary btn-lg">Continue Shopping</a>
</div>

<%--<div class="container my-5">--%>

<%--  <!-- MDB Card -->--%>
<%--  <div class="card d-lg-block px-3 py-1 border">--%>
<%--    <h4 class="p-4">Your shopping cart is empty!</h4>--%>
<%--  </div>--%>
<%--</div>--%>
<% } %>


<script>
  document.addEventListener('DOMContentLoaded', function () {
    const quantityInputs = document.querySelectorAll('.quantity-input');

    quantityInputs.forEach(function (input) {
      input.addEventListener('change', function () {
        const form = input.closest('.product-form');
        form.submit();
      });
    });
  });
</script>


<%--<script>--%>
<%--  // Listen for changes in the select field--%>
<%--  document.getElementById('tableSelect').addEventListener('change', function () {--%>
<%--    // Submit the form when an option is selected--%>
<%--    document.getElementById('tableForm').submit();--%>
<%--  });--%>
<%--</script>--%>