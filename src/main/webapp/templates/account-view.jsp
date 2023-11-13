<%@ page import="com.peach.sellbuy.business.User" %>
<%@ page import="com.peach.sellbuy.business.Access" %>
<%@ page import="com.peach.sellbuy.business.Order" %>
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

  Access<Order> orderAccess = new Access<>(Order.class);
  LinkedList<Order> orders = orderAccess.getAltRows("userID", user.getUserID());
%>

<!-- content -->
<section class="py-5 bg-light">
  <div class="container">
    <div class="row">
      <div class="col-lg-3 col-xl-3">
        <div class="card border">
          <div class="card-body">
            <nav class="nav flex-lg-column w-100 d-flex nav-pills mb-1">
              <a class="nav-link my-0 active" href="user-account.jsp">
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
              <a class="nav-link my-0 bg-white" href="user-transactions.jsp">
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
            <h3 class="">Your Account</h3>
            <hr />

            <div class="row g-2 mb-3">
              <div class="col-md-6">
                <div class="border p-3 rounded-3 bg-light">
                  <b class="mx-2 text-muted"><i class="fa fa-info"></i></b>
                  <%= user.getName() %>, <%= user.getEmail() %>
                </div>
              </div>

              <div class="col-md-6">
                <div class="border p-3 rounded-3 bg-light">
                  <b class="mx-2 text-muted"><i class="fa fa-map-marker-alt"></i></b>
                  <%= user.getShippingAddress() %>
                </div>
              </div>
            </div>

<%--                              <a href="#" class="px-2"><i class="fa fa-pen"></i></a>--%>

<%--            <hr />--%>
<%--            <a href="#" class="btn btn-light border"><i class="me-2 fa fa-pen"></i>Change Address</a>--%>

            <hr class="my-4" />

            <h5 class="mb-3">Your Orders</h5>
            <% if (orders != null && !orders.isEmpty())  { %>
              <% for (Order order : orders) { %>
              <%= Order.orderCard(order) %>
              <% } %>
            <% } else { %>
            <div class="col-md-12">
              <div class="border border-danger text-white p-3 rounded-3 bg-danger text-center">
                You have made any orders!
              </div>
            </div>
            <% } %>
          </div>
        </div>
      </main>
    </div>

  </div>
</section>
<!-- content -->