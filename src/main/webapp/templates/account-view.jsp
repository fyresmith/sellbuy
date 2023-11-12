<%@ page import="com.peach.sellbuy_ecommerce.business.User" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.Access" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.Order" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 12:06 PM
  To change this template use File | Settings | File Templates.
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
        <nav class="nav flex-lg-column w-100 d-flex nav-pills mb-4">
          <a class="nav-link my-0 active" href="#"><p class="pb-0 mb-0" style="width: 100px">Account main</p></a>
          <a class="nav-link my-0 bg-light" href="#"><p class="pb-0 mb-0" style="width: 100px">New orders</p></a>
          <a class="nav-link my-0 bg-light" href="#"><p class="pb-0 mb-0" style="width: 100px">Orders history</p></a>
          <a class="nav-link my-0 bg-light" href="#"><p class="pb-0 mb-0" style="width: 100px">My wishlist</p></a>
          <a class="nav-link my-0 bg-light" href="#"><p class="pb-0 mb-0" style="width: 100px">Transactions</p></a>
          <a class="nav-link my-0 bg-light" href="#"><p class="pb-0 mb-0" style="width: 100px">Profile setting</p></a>
          <a class="nav-link my-0 bg-light" href="${pageContext.request.contextPath}/logout-servlet"><p class="pb-0 mb-0" style="width: 100px">Log out</p></a>
        </nav>
      </div>
      <main class="col-lg-9 col-xl-9">
        <div class="card p-4 mb-0 shadow-0 border">
          <div class="content-body">
            <div class="d-flex align-items-center">
              <div class="pt-2">
                <h6 class="pt-2">${user.getFirstName()} ${user.getLastName()}</h6>
                <p>
                  Email: ${user.getEmail()}
                  <a href="#" class="px-2"><i class="fa fa-pen"></i></a>
                </p>
              </div>
            </div>

            <hr />

            <div class="row g-2 mb-3">
              <div class="col-md-6">
                <div class="border p-3 rounded-3 bg-light">
                  <b class="mx-2 text-muted"><i class="fa fa-map-marker-alt"></i></b>
                  ${user.getShippingAddress()}
                </div>
              </div>
            </div>

<%--            <a href="#" class="btn btn-light border"><i class="me-2 fa fa-pen"></i>Change Address</a>--%>

            <hr class="my-4" />

            <h5 class="mb-3">Your Orders</h5>
            <% for (Order order : orders) { %>
              <%= Order.orderCard(order) %>
            <% } %>

<%--            <div class="card border border-primary mb-4 shadow-0">--%>
<%--              <div class="card-body pb-0">--%>
<%--                <header class="d-lg-flex">--%>
<%--                  <div class="flex-grow-1">--%>
<%--                    <h6 class="mb-0">Order ID: 8924 <i class="dot"></i>--%>
<%--                      <span class="text-success"> Shipped</span>--%>
<%--                    </h6>--%>
<%--                    <span class="text-muted">Date: 16 December 2022</span>--%>
<%--                  </div>--%>
<%--                  <div>--%>
<%--                    <a href="#" class="btn btn-sm btn-outline-danger">Cancel order</a>--%>
<%--                    <a href="#" class="btn btn-sm btn-primary shadow-0">Track order</a>--%>
<%--                  </div>--%>
<%--                </header>--%>
<%--                <hr />--%>
<%--                <div class="row">--%>
<%--                  <div class="col-lg-4">--%>
<%--                    <p class="mb-0 text-muted">Contact</p>--%>
<%--                    <p class="m-0">--%>
<%--                      Mike Johnatan <br />--%>
<%--                      Phone: 371-295-9131 <br />--%>
<%--                      Email: info@mywebsite.com--%>
<%--                    </p>--%>
<%--                  </div>--%>
<%--                  <div class="col-lg-4 border-start">--%>
<%--                    <p class="mb-0 text-muted">Shipping address</p>--%>
<%--                    <p class="m-0">--%>
<%--                      United States <br />--%>
<%--                      3601 Old Capitol Trail, Unit A-7, Suite 170777, Wilmington, DE 19808--%>
<%--                    </p>--%>
<%--                  </div>--%>
<%--                  <div class="col-lg-4 border-start">--%>
<%--                    <p class="mb-0 text-muted">Payment</p>--%>
<%--                    <p class="m-0">--%>
<%--                      <span class="text-success"> Visa **** 4216 </span> <br />--%>
<%--                      Shipping fee: $56 <br />--%>
<%--                      Total paid: $456--%>
<%--                    </p>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--                <hr />--%>
<%--                <ul class="row list-unstyled">--%>
<%--                  <li class="col-xl-4 col-lg-6">--%>
<%--                    <div class="d-flex mb-3 mb-xl-0">--%>
<%--                      <div class="me-3">--%>
<%--                        <img width="72" height="72" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/10.webp" class="img-sm rounded border" />--%>
<%--                      </div>--%>
<%--                      <div class="">--%>
<%--                        <p class="mb-0">T-shirts with multiple colors</p>--%>
<%--                        <strong> 2x = $25.98 </strong>--%>
<%--                      </div>--%>
<%--                    </div>--%>
<%--                  </li>--%>
<%--                  <li class="col-xl-4 col-lg-6">--%>
<%--                    <div class="d-flex mb-3 mb-xl-0">--%>
<%--                      <div class="me-3">--%>
<%--                        <img width="72" height="72" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/7.webp" class="img-sm rounded border" />--%>
<%--                      </div>--%>
<%--                      <div class="">--%>
<%--                        <p class="mb-0">Gaming Headset 32db Black</p>--%>
<%--                        <strong> 2x = $339.90 </strong>--%>
<%--                      </div>--%>
<%--                    </div>--%>
<%--                  </li>--%>
<%--                  <li class="col-xl-4 col-lg-6">--%>
<%--                    <div class="d-flex mb-3 mb-md-0">--%>
<%--                      <div class="me-3">--%>
<%--                        <img width="72" height="72" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/5.webp" class="img-sm rounded border" />--%>
<%--                      </div>--%>
<%--                      <div class="">--%>
<%--                        <p class="mb-0">Apple Watch Series 4 Space Gray</p>--%>
<%--                        <strong> 2x = $339.90 </strong>--%>
<%--                      </div>--%>
<%--                    </div>--%>
<%--                  </li>--%>
<%--                </ul>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--            <div class="card border border-primary shadow-0">--%>
<%--              <div class="card-body pb-0">--%>
<%--                <header class="d-lg-flex">--%>
<%--                  <div class="flex-grow-1">--%>
<%--                    <h6 class="mb-0">--%>
<%--                      Order ID: 9088--%>
<%--                      <i class="dot"></i>--%>
<%--                      <span class="text-danger"> Pending </span>--%>
<%--                    </h6>--%>
<%--                    <span class="text-muted">Date: 16 December 2022</span>--%>
<%--                  </div>--%>
<%--                  <div>--%>
<%--                    <a href="#" class="btn btn-sm btn-outline-danger">Cancel order</a>--%>
<%--                    <a href="#" class="btn btn-sm btn-primary shadow-0">Track order</a>--%>
<%--                  </div>--%>
<%--                </header>--%>
<%--                <hr />--%>
<%--                <div class="row">--%>
<%--                  <div class="col-lg-4">--%>
<%--                    <p class="mb-0 text-muted">Contact</p>--%>
<%--                    <p class="m-0">--%>
<%--                      Mike Johnatan <br />--%>
<%--                      Phone: 371-295-9131 <br />--%>
<%--                      Email: info@mywebsite.com--%>
<%--                    </p>--%>
<%--                  </div>--%>
<%--                  <div class="col-lg-4 border-start">--%>
<%--                    <p class="mb-0 text-muted">Shipping address</p>--%>
<%--                    <p class="m-0">--%>
<%--                      United States <br />--%>
<%--                      600 Markley Street, Suite 170777 Port Reading, NJ 07064--%>
<%--                    </p>--%>
<%--                  </div>--%>
<%--                  <div class="col-lg-4 border-start">--%>
<%--                    <p class="mb-0 text-muted">Payment</p>--%>
<%--                    <p class="m-0">--%>
<%--                      <span class="text-success"> Visa **** 4216 </span> <br />--%>
<%--                      Shipping fee: $56 <br />--%>
<%--                      Total paid: $456--%>
<%--                    </p>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--                <hr />--%>
<%--                <ul class="row list-unstyled">--%>
<%--                  <li class="col-xl-4 col-lg-6">--%>
<%--                    <div class="d-flex mb-3 mb-lg-0">--%>
<%--                      <div class="me-3 mb-xl-0">--%>
<%--                        <img width="72" height="72" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/10.webp" class="img-sm rounded border" />--%>
<%--                      </div>--%>
<%--                      <div class="info">--%>
<%--                        <p class="mb-0">T-shirts with multiple colors</p>--%>
<%--                        <strong> 2x = $25.98 </strong>--%>
<%--                      </div>--%>
<%--                    </div>--%>
<%--                  </li>--%>
<%--                  <li class="col-xl-4 col-lg-6">--%>
<%--                    <div class="d-flex mb-0 mb-md-0">--%>
<%--                      <div class="me-3">--%>
<%--                        <img width="72" height="72" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/7.webp" class="img-sm rounded border" />--%>
<%--                      </div>--%>
<%--                      <div class="info">--%>
<%--                        <p class="mb-0">Gaming Headset 32db Black</p>--%>
<%--                        <strong> 2x = $339.90 </strong>--%>
<%--                      </div>--%>
<%--                    </div>--%>
<%--                  </li>--%>
<%--                </ul>--%>
<%--              </div>--%>
<%--            </div>--%>
          </div>
        </div>
      </main>
    </div>

  </div>
</section>
<!-- content -->