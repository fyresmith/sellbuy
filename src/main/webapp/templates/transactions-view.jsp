<%@ page import="com.peach.sellbuy.business.User" %>
<%@ page import="com.peach.sellbuy.business.Product" %>
<%@ page import="com.peach.sellbuy.business.Access" %>
<%@ page import="com.peach.sellbuy.business.Order" %>
<%@ page import="com.peach.sellbuy.business.OrderItem" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.peach.sellbuy.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 12:06 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  User user = (User) session.getAttribute("user");

  Access<Product> productAccess = new Access<>(Product.class);
  LinkedList<Product> products = productAccess.getAltRows("sellerID", user.getUserID());

  Access<OrderItem> orderItemAccess = new Access<>(OrderItem.class);
  LinkedList<OrderItem> items = new LinkedList<>();

  for (Product product : products) {
    items.addAll(orderItemAccess.getAltRows("productID", product.getProductID()));
  }
%>

<style>
  .card-link {
    text-decoration: none;
    color: inherit;
    transition: transform 0.3s, box-shadow 0.3s, border-color 0.3s;
    display: block; /* Ensure the anchor takes up the entire space */
  }

  .card-link:hover {
    transform: scale(1.025);
    color: inherit;
    transform-origin: center; /* Set the transform origin to the center */
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    border-color: #22bb33 !important;
    cursor: pointer; /* Set the cursor to a pointer on hover */
  }
</style>

<!-- content -->
<section class="py-5 bg-light">
  <div class="container">
    <div class="row">
      <div class="col-lg-3 col-xl-3">
        <div class="card border">
          <div class="card-body">
            <nav class="nav flex-lg-column w-100 d-flex nav-pills mb-1">
              <a class="nav-link my-0 bg-white" href="user-account.jsp">
                <p class="pb-0 mb-0" style="width: 120px"><i class="fas fa-home fa-lg"></i> Account main</p>
              </a>
              <a class="nav-link my-0 bg-white" href="user-profile.jsp">
                <p class="pb-0 mb-0" style="width: 120px"><i class="fas fa-user-circle fa-lg"></i> Profile</p>
              </a>
              <a class="nav-link my-0 bg-white" href="user-orders.jsp">
                <p class="pb-0 mb-0" style="width: 120px"><i class="fas fa-history fa-lg"></i> Orders History</p>
              </a>
              <a class="nav-link my-0 bg-white" href="user-products.jsp">
                <p class="pb-0 mb-0" style="width: 120px"><i class="fas fa-list-ul fa-lg"></i> My Products</p>
              </a>
              <a class="nav-link my-0 active" href="user-transactions.jsp">
                <p class="pb-0 mb-0" style="width: 120px"><i class="fas fa-dollar-sign fa-lg"></i> Transactions</p>
              </a>
              <a class="nav-link my-0 bg-white" href="<%= Util.webPage("logout") %>">
                <p class="pb-0 mb-0" style="width: 120px"><i class="fas fa-sign-out-alt"></i> Log out</p>
              </a>
            </nav>
          </div>
        </div>
      </div>


      <main class="col-lg-9 col-xl-9">
        <div class="card p-4 mb-0 shadow-0 border">
          <div class="content-body">
            <h3 class="mb-3">Your Transactions</h3>
            <hr />

            <div class="row g-2 mb-3">
              <% if (items == null || items.isEmpty()) { %>
              <div class="col-md-12">
                <div class="border border-danger text-white p-3 rounded-3 bg-danger text-center">
                  You have not sold any products!
                </div>
              </div>
              <% } else { %>
                <% for (OrderItem item : items) { %>
                  <div class="col-md-4 mb-2">
                    <div class="card-link border p-3 rounded-3 bg-white d-flex align-items-center">
                      <div class="d-flex">
                        <div class="flex-grow-1">
                          <h6 class="mb-0">You Sold:
                            <span class="text-warning"> <%= item.getProduct().getProductName() %></span>
                          </h6>
                          <h6 class="mb-0">Quantity Sold:
                            <span class="text-primary"> <%= item.getQuantity() %></span>
                          </h6>
                          <h6 class="mb-0">You Earned:
                            <span class="text-success"> $<%= item.getPrice() %></span>
                          </h6>
                        </div>
                      </div>
                    </div>
                  </div>
                <% } %>
              <% } %>
            </div>
          </div>
        </div>
      </main>
    </div>

  </div>
</section>
<!-- content -->