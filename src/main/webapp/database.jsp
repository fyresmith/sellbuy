<%@ page import="com.peach.sellbuy_ecommerce.business.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Pager" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.DBPager" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/2/23
  Time: 11:52 PM

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

  final DecimalFormat df = Util.priceFormat();

  int pageNum, pageTotal;

  if (session.getAttribute("pageNum") == null) {
      pageNum = 1;

      session.setAttribute("pageNum", pageNum);
  } else {
      pageNum = (int) session.getAttribute("pageNum");
  }

  DBPager<Product> productPager = new DBPager<>(new Access<>(Product.class), 100);
  DBPager<Image> imagePager = new DBPager<>(new Access<>(Image.class), 100);
  DBPager<Order> orderPager = new DBPager<>(new Access<>(Order.class), 100);
  DBPager<OrderItem> orderItemPager = new DBPager<>(new Access<>(OrderItem.class), 100);
  DBPager<Review> reviewPager = new DBPager<>(new Access<>(Review.class), 100);
  DBPager<User> userPager = new DBPager<>(new Access<>(User.class), 100);

  if (table.equals("image")) {
      pageTotal = imagePager.getNumberOfPages();
  } else if (table.equals("order")) {
    pageTotal = orderPager.getNumberOfPages();
  } else if (table.equals("orderitem")) {
    pageTotal = orderItemPager.getNumberOfPages();
  } else if (table.equals("review")) {
    pageTotal = reviewPager.getNumberOfPages();
  } else if (table.equals("user")) {
    pageTotal = userPager.getNumberOfPages();
  } else {
    pageTotal = productPager.getNumberOfPages();
  }

  session.setAttribute("pageTotal", pageTotal);
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
  <div class="row d-flex justify-content-center align-items-center">
    <div class="col-md-4">
      <form id="tableForm" action="<%= Util.webPage("change-table") %>">
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

    <div class="col-md-8">
      <!-- Pagination -->
      <div aria-label="Page navigation example" class="d-flex justify-content-center mt-3 pt-2">
        <ul class="pagination pb-0 mb-0">
          <% if (pageNum <= 1) { %>
            <form class="page-item disabled" action="<%= Util.webPage("page-first") %>">
          <% } else { %>
            <form class="page-item" action="<%= Util.webPage("page-first") %>">
          <% } %>
            <button class="page-link" aria-label="First">
              <span aria-hidden="true">&laquo;&laquo; First</span>
            </button>
          </form>

          <% if (pageNum <= 1) { %>
            <form class="page-item disabled" action="<%= Util.webPage("page-previous") %>">
          <% } else { %>
            <form class="page-item" action="<%= Util.webPage("page-previous") %>">
          <% } %>
              <button class="page-link" aria-label="Previous">
                <span aria-hidden="true">&laquo; Previous</span>
              </button>
            </form>

          <li class="page-item">
            <span class="page-link disabled fw-bold text-dark">Page <%= pageNum %> of <%= pageTotal %></span>
          </li>

          <% if (pageNum >= pageTotal) { %>
            <form class="page-item disabled" action="<%= Util.webPage("page-next") %>">
          <% } else { %>
            <form class="page-item" action="<%= Util.webPage("page-next") %>">
          <% } %>
              <button class="page-link" aria-label="Next">
                <span aria-hidden="true">Next &raquo;</span>
              </button>
            </form>

          <% if (pageNum >= pageTotal) { %>
            <form class="page-item disabled" action="<%= Util.webPage("page-final") %>">
          <% } else { %>
            <form class="page-item" action="<%= Util.webPage("page-final") %>">
          <% } %>
              <button class="page-link" aria-label="Final">
                <span aria-hidden="true">Final &raquo;&raquo;</span>
              </button>
            </form>
        </ul>
      </div>
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
                <th>productCategory</th>
                <th>keywords</th>
              </tr>
            </thead>
            <% for (Product product : productPager.getPage(pageNum)) { %>
              <form action="/edit-table">
                <tbody>
                  <tr>
                    <td class="editable-cell"><%= product.getProductID() %></td>
                    <td class="editable-cell"><%= product.getProductName() %></td>
                    <td class="editable-cell"><%= product.getDescription() %></td>
                    <td class="editable-cell"><%= df.format(product.getPrice()) %></td>
                    <td class="editable-cell"><%= product.getStockQuantity() %></td>
                    <td class="editable-cell"><%= product.getSellerID() %></td>
                    <td class="editable-cell"><%= product.getProductCategory() %></td>
                    <td class="editable-cell"><%= product.getKeywords() %></td>
                  </tr>
                </tbody>
              </form>
          <% } %>
          <% } else if (table.equals("image")) {%>
            <thead class="table-warning">
              <tr>
                <th>imageID</th>
                <th>imageURL</th>
                <th>productID</th>
              </tr>
            </thead>
            <% for (Image image : imagePager.getPage(pageNum)) { %>
              <tbody>
                <tr>
                  <td class="editable-cell"><%= image.getImageID() %></td>
                  <td class="editable-cell"><%= image.getImageURL() %></td>
                  <td class="editable-cell"><%= image.getProductID() %></td>
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
            <% for (Order order : orderPager.getPage(pageNum)) { %>
              <tbody>
              <tr>
                <td class="editable-cell"><%= order.getOrderID() %></td>
                <td class="editable-cell"><%= order.getUserID() %></td>
                <td class="editable-cell"><%= order.getOrderDate() %></td>
                <td class="editable-cell"><%= order.getShippingAddress() %></td>
                <td class="editable-cell"><%= order.getPaymentMethod() %></td>
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
            <% for (OrderItem orderItem : orderItemPager.getPage(pageNum)) { %>
              <tbody>
                <tr>
                  <td class="editable-cell"><%= orderItem.getOrderID() %></td>
                  <td class="editable-cell"><%= orderItem.getOrderItemID() %></td>
                  <td class="editable-cell"><%= orderItem.getProductID() %></td>
                  <td class="editable-cell"><%= orderItem.getQuantity() %></td>
                  <td class="editable-cell"><%= orderItem.getPrice() %></td>
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
            <% for (Review review : reviewPager.getPage(pageNum)) { %>
              <tbody>
                <tr>
                  <td class="editable-cell"><%= review.getReviewID() %></td>
                  <td class="editable-cell"><%= review.getProductID() %></td>
                  <td class="editable-cell"><%= review.getUserID() %></td>
                  <td class="editable-cell"><%= review.getRating() %></td>
                  <td class="editable-cell"><%= review.getReviewText() %></td>
                  <td class="editable-cell"><%= review.getDatePosted() %></td>
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
            <% for (User user : userPager.getPage(pageNum)) { %>
              <tbody>
                <tr>
                  <td class="editable-cell"><%= user.getFirstName() %></td>
                  <td class="editable-cell"><%= user.getUserID() %></td>
                  <td class="editable-cell"><%= user.getLastName() %></td>
                  <td class="editable-cell"><%= user.getUsername() %></td>
                  <td class="editable-cell"><%= user.getPassword() %></td>
                  <td class="editable-cell"><%= user.getEmail() %></td>
                  <td class="editable-cell"><%= user.getShippingAddress() %></td>
                  <td class="editable-cell"><%= user.getPaymentInformation() %></td>
                </tr>
              </tbody>
            <% } %>
          <% } %>
        </table>
      </div>
    </div>
  </div>

  <!-- Pagination -->
  <nav aria-label="Page navigation example" class="d-flex justify-content-center mt-3">
    <ul class="pagination">
      <% if (pageNum <= 1) { %>
        <form class="page-item disabled" action="<%= Util.webPage("page-first") %>">
      <% } else { %>
        <form class="page-item" action="<%= Util.webPage("page-first") %>">
      <% } %>
        <button class="page-link" aria-label="First">
          <span aria-hidden="true">&laquo;&laquo; First</span>
        </button>
      </form>

      <% if (pageNum <= 1) { %>
        <form class="page-item disabled" action="<%= Util.webPage("page-previous") %>">
      <% } else { %>
        <form class="page-item" action="<%= Util.webPage("page-previous") %>">
      <% } %>
          <button class="page-link" aria-label="Previous">
            <span aria-hidden="true">&laquo; Previous</span>
          </button>
        </form>

      <li class="page-item">
        <span class="page-link disabled fw-bold text-dark">Page <%= pageNum %> of <%= pageTotal %></span>
      </li>

      <% if (pageNum >= pageTotal) { %>
        <form class="page-item disabled" action="<%= Util.webPage("page-next") %>">
      <% } else { %>
        <form class="page-item" action="<%= Util.webPage("page-next") %>">
      <% } %>
          <button class="page-link" aria-label="Next">
            <span aria-hidden="true">Next &raquo;</span>
          </button>
        </form>

      <% if (pageNum >= pageTotal) { %>
        <form class="page-item disabled" action="<%= Util.webPage("page-final") %>">
      <% } else { %>
        <form class="page-item" action="<%= Util.webPage("page-final") %>">
      <% } %>
          <button class="page-link" aria-label="Final">
            <span aria-hidden="true">Final &raquo;&raquo;</span>
          </button>
        </form>
    </ul>
  </nav>
  <!-- Pagination -->
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
<script>
  // Function to make table cells editable on double-click
  const makeTableCellEditable = (cell) => {
    cell.contentEditable = true;
    cell.focus();
    cell.classList.add('editable-cell');
  };

  // Function to handle saving changes and submitting the form on Enter
  const handleTableCellEdit = (cell) => {
    cell.contentEditable = false;
    cell.classList.remove('editable-cell');
    // Update the corresponding hidden input field with the edited value
    const hiddenInput = cell.querySelector('.hidden-input');
    hiddenInput.value = cell.innerText;
    document.getElementById('tableForm').submit();
  };

  // Add event listeners to make table cells editable on double-click
  const tableCells = document.querySelectorAll('.editable-cell');
  tableCells.forEach((cell) => {
    cell.addEventListener('dblclick', () => makeTableCellEditable(cell));
    cell.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') {
        handleTableCellEdit(cell);
      }
    });
  });
</script>

</body>
</html>
