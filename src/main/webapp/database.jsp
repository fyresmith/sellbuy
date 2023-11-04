<%@ page import="com.peach.sellbuy_ecommerce.business.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/2/23
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  Access<Product> productAccess = new Access<>("product", "productID", Product.class);
  Access<Image> imageAccess = new Access<>("image", "imageID", Image.class);
  Access<Order> orderAccess = new Access<>("order", "orderID", Order.class);
  Access<OrderItem> orderItemAccess = new Access<>("order_item", "orderItemID", OrderItem.class);
  Access<Review> reviewAccess = new Access<>("review", "reviewID", Review.class);
  Access<User> userAccess = new Access<>("user", "userID", User.class);

  String table = (String) session.getAttribute("table");

  if (table == null) {
    table = "product";
  }

  DecimalFormat df = new DecimalFormat("0.00");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Database Viewer</title>
  <!-- Add the MDB Bootstrap CSS and JavaScript -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css">
</head>

<body>
<div class="container my-5">
  <div class="row">
    <div class="col-md-6">
      <form id="tableForm" action="<%= Util.webRoot("change-table-servlet") %>">
        <label for="tableSelect" class="form-label">Select Table:</label>
        <select class="form-select" id="tableSelect" name="table">
          <option <% if (table.equals("product")) {%>selected="selected"<%}%> value="product">Product - (<%= productAccess.size() %> rows)</option>
          <option <% if (table.equals("image")) {%>selected="selected"<%}%> value="image">Image - (<%= imageAccess.size() %> rows)</option>
          <option <% if (table.equals("order")) {%>selected="selected"<%}%> value="order">Order - (<%= orderAccess.size() %> rows)</option>
          <option <% if (table.equals("orderitem")) {%>selected="selected"<%}%> value="orderitem">OrderItem - (<%= orderItemAccess.size() %> rows)</option>
          <option <% if (table.equals("review")) {%>selected="selected"<%}%> value="review">Review - (<%= reviewAccess.size() %> rows)</option>
          <option <% if (table.equals("user")) {%>selected="selected"<%}%> value="user">User - (<%= userAccess.size() %> rows)</option>
        </select>
      </form>
    </div>
  </div>

  <div class="row mt-3">
    <div class="col">
      <div id="table1">
        <table class="table table-sm table-hover table-bordered table-ro">
          <% if (table.equals("product")) { %>
            <thead class="table-warning">
              <tr>
                <th>productID</th>
                <th>productName</th>
                <th>description</th>
                <th>price</th>
                <th>stockQuantity</th>
                <th>sellerID</th>
                <th>keywords</th>
              </tr>
            </thead>
            <% for (Product product : productAccess.getTable()) { %>
              <tbody>
                <tr>
                  <td><%= product.getProductID() %></td>
                  <td><%= product.getProductName() %></td>
                  <td><%= product.getDescription() %></td>
                  <td><%= df.format(product.getPrice()) %></td>
                  <td><%= product.getStockQuantity() %></td>
                  <td><%= product.getSellerID() %></td>
                  <td><%= product.getKeywords() %></td>
                </tr>
              </tbody>
            <% } %>
          <% } else if (table.equals("image")) {%>
            <thead class="table-warning">
              <tr>
                <th>imageID</th>
                <th>imageURL</th>
                <th>productID</th>
              </tr>
            </thead>
            <% for (Image image : imageAccess.getTable()) { %>
              <tbody>
                <tr>
                  <td><%= image.getImageID() %></td>
                  <td><%= image.getImageURL() %></td>
                  <td><%= image.getProductID() %></td>
                </tr>
              </tbody>
            <% } %>
          <% } else if (table.equals("order")) {%>
            <thead class="table-warning">
              <tr>
                <th>orderID</th>
                <th>userID</th>
                <th>orderDate</th>
                <th>shippingAddress</th>
                <th>paymentMethod</th>
              </tr>
            </thead>
            <% for (Order order : orderAccess.getTable()) { %>
              <tbody>
              <tr>
                <td><%= order.getOrderID() %></td>
                <td><%= order.getUserID() %></td>
                <td><%= order.getOrderDate() %></td>
                <td><%= order.getShippingAddress() %></td>
                <td><%= order.getPaymentMethod() %></td>
              </tr>
              </tbody>
            <% } %>
          <% } else if (table.equals("orderitem")) {%>
            <thead class="table-warning">
              <tr>
                <th>orderID</th>
                <th>orderItemID</th>
                <th>productID</th>
                <th>quantity</th>
                <th>price</th>
              </tr>
            </thead>
            <% for (OrderItem orderItem : orderItemAccess.getTable()) { %>
              <tbody>
                <tr>
                  <td><%= orderItem.getOrderID() %></td>
                  <td><%= orderItem.getOrderItemID() %></td>
                  <td><%= orderItem.getProductID() %></td>
                  <td><%= orderItem.getQuantity() %></td>
                  <td><%= orderItem.getPrice() %></td>
                </tr>
              </tbody>
            <% } %>
          <% } else if (table.equals("review")) {%>
            <thead class="table-warning">
              <tr>
                <th>reviewID</th>
                <th>productID</th>
                <th>userID</th>
                <th>rating</th>
                <th>reviewTest</th>
                <th>datePosted</th>
              </tr>
            </thead>
            <% for (Review review : reviewAccess.getTable()) { %>
              <tbody>
                <tr>
                  <td><%= review.getReviewID() %></td>
                  <td><%= review.getProductID() %></td>
                  <td><%= review.getUserID() %></td>
                  <td><%= review.getRating() %></td>
                  <td><%= review.getReviewText() %></td>
                  <td><%= review.getDatePosted() %></td>
                </tr>
              </tbody>
            <% } %>
          <% } else if (table.equals("user")) {%>
            <thead class="table-warning">
              <tr>
                <th>firstName</th>
                <th>userID</th>
                <th>lastName</th>
                <th>username</th>
                <th>password</th>
                <th>email</th>
                <th>shippingAddress</th>
                <th>paymentMethod</th>
              </tr>
            </thead>
            <% for (User user : userAccess.getTable()) { %>
              <tbody>
                <tr>
                  <td><%= user.getFirstName() %></td>
                  <td><%= user.getUserID() %></td>
                  <td><%= user.getLastName() %></td>
                  <td><%= user.getUsername() %></td>
                  <td><%= user.getPassword() %></td>
                  <td><%= user.getEmail() %></td>
                  <td><%= user.getShippingAddress() %></td>
                  <td><%= user.getPaymentInformation() %></td>
                </tr>
              </tbody>
            <% } %>
          <% } %>
        </table>
      </div>


      <div id="table2" style="display: none;">
        <table class="table table-striped table-bordered">
          <thead>
          <tr>
            <th>#</th>
            <th>Column A</th>
            <th>Column B</th>
            <!-- Add more columns as needed -->
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>1</td>
            <td>Data A1</td>
            <td>Data B1</td>
            <!-- Add more rows as needed -->
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<!-- Add jQuery and Popper.js (required by MDB) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.6/umd/popper.min.js"></script>
<!-- Add the MDB Bootstrap JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>
<script>
  // Listen for changes in the select field
  document.getElementById('tableSelect').addEventListener('change', function () {
    // Submit the form when an option is selected
    document.getElementById('tableForm').submit();
  });
</script>
</body>
</html>
