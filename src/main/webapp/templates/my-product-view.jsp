<%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/11/23
  Time: 10:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.User" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.Access" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.Order" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.Product" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 12:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  User user = (User) session.getAttribute("user");

  Access<Product> productAccess = new Access<>(Product.class);
  LinkedList<Product> products = productAccess.getAltRows("sellerID", user.getUserID());
%>

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
              <a class="nav-link my-0 active" href="user-products.jsp">
                <p class="pb-0 mb-0" style="width: 120px"><i class="fas fa-list-ul fa-lg"></i> My Products</p>
              </a>
              <a class="nav-link my-0 bg-white" href="${pageContext.request.contextPath}/logout">
                <p class="pb-0 mb-0" style="width: 120px"><i class="fas fa-sign-out-alt"></i> Log out</p>
              </a>
            </nav>
          </div>
        </div>
      </div>


      <main class="col-lg-9 col-xl-9">
        <div class="card p-4 mb-0 shadow-0 border">
          <div class="content-body">
            <h3 class="">Your Products</h3>
            <hr />

            <div class="row g-2 mb-3">
              <% if (products == null || products.isEmpty()) { %>
                <div class="col-md-12">
                  <div class="border border-danger text-white p-3 rounded-3 bg-danger text-center">
                    You have no products! Click below to add a product.
                  </div>
                </div>
              <% } else { %>
                <div class="col-md-12">
                  <div class="border border-success p-3 rounded-3 bg-white">
                    <b class="mx-2 text-muted"><i class="fas fa-signature"></i></b>
                    <%= user.getName() %> <a href="#" class="px-2"><i class="fa fa-edit"></i></a>
                  </div>
                </div>
              <% } %>

            <div class="col-md-12">
              <button class="btn btn-primary btn-block border border-primary p-3 rounded-3" data-mdb-toggle="modal" data-mdb-target="#insertProduct">
<%--                <b class="mx-2 text-white"><i class="fas fa-signature"></i></b>--%>
<%--                <%= user.getName() %>--%>
                <a href="#" class="px-2 text-white"><i class="fas fa-plus"></i></a>
              </button>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="insertProduct" tabindex="-1" aria-labelledby="insertProduct" aria-hidden="true">
              <div class="modal-dialog modal-xl">
                <form class="modal-content" enctype="multipart/form-data" method="POST" action="<%= Util.webRoot("add-product") %>">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add Product - Product Listing Details</h5>
                    <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <jsp:include page="product-creator.jsp"/>
<%--                    <div class="mb-4">--%>
<%--                      <input type="file" name="productImage" id="productImage" class="form-control" />--%>
<%--                      &lt;%&ndash;    <label class="form-label" for="productImage">Upload Image</label>&ndash;%&gt;--%>
<%--                    </div>--%>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Publish Product</button>
                  </div>
                </form>
              </div>
            </div>



<%--              <div class="col-md-6">--%>
<%--                <div class="border p-3 rounded-3 bg-light">--%>
<%--                  <b class="mx-2 text-muted"><i class="fas fa-at"></i></b>--%>
<%--                  <%= user.getEmail() %> <a href="#" class="px-2"><i class="fa fa-edit"></i></a>--%>
<%--                </div>--%>
<%--              </div>--%>

<%--              <div class="col-md-6">--%>
<%--                <div class="border p-3 rounded-3 bg-light">--%>
<%--                  <b class="mx-2 text-muted"><i class="fa fa-map-marker-alt"></i></b>--%>
<%--                  <%= user.getShippingAddress() %> <a href="#" class="px-2"><i class="fa fa-edit"></i></a>--%>
<%--                </div>--%>
<%--              </div>--%>

<%--              <div class="col-md-6">--%>
<%--                <div class="border p-3 rounded-3 bg-light">--%>
<%--                  <b class="mx-2 text-muted"><i class="fa fa-info-circle"></i></b>--%>
<%--                  <%= user.getUsername() %> <a href="#" class="px-2"><i class="fas fa-edit"></i></a>--%>
<%--                </div>--%>
<%--              </div>--%>
            </div>
          </div>
        </div>
      </main>
    </div>

  </div>
</section>
<!-- content -->