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
        border-color: #0d6efd !important;
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


<%--                <div class="col-md-6 mb-2">--%>
<%--                    <a class="card-link border p-3 rounded-3 bg-white d-flex align-items-center">--%>
<%--                        <!-- Image Container with Rounded Corners -->--%>
<%--                        <div class="me-3">--%>
<%--                            <img width="100%" height="100%" src="<%= Util.image("71Iy1eCkadL", "300") %>" id="image" class="img-rounded border rounded-2" />--%>
<%--                        </div>--%>
<%--                        <!-- Information Container -->--%>
<%--                        <div class="d-flex flex-column">--%>
<%--                            <div class="mb-1" id="name"><strong>Floating Tube</strong></div>--%>
<%--                            <div class="mb-1" id="description">Description Text Goes Here</div>--%>
<%--                            <div class="mb-1" id="category"><strong>Category Text</strong></div>--%>
<%--                            <div class="mb-1 invisible" id="keywords"><strong>Keywords</strong></div>--%>
<%--                            <div class="mb-1 invisible" id="stock"><strong>3</strong></div>--%>
<%--                            <div class="mb-1 invisible" id="price"><strong>100</strong></div>--%>
<%--                            <div class="mt-auto" id="stockString">3 in stock, $100/item</div>--%>
<%--                        </div>--%>
<%--                        <!-- User Information Container -->--%>
<%--                        &lt;%&ndash; <div class="mx-2 text-muted"><i class="fas fa-signature"></i></div> &ndash;%&gt;--%>
<%--                    </a>--%>
<%--                </div>--%>

                    <% for (Product product: products) { %>
                            <%= Product.myProductsCard(product) %>
                    <% } %>



                <% } %>

            <div class="col-md-12">
              <button class="btn btn-primary btn-block border border-primary p-3 rounded-3" data-mdb-toggle="modal" data-mdb-target="#insertProduct">
<%--                <b class="mx-2 text-white"><i class="fas fa-signature"></i></b>--%>
<%--                <%= user.getName() %>--%>
                <a href="#" class="px-2 text-white"><i class="fas fa-plus"></i></a>
              </button>
            </div>
                <jsp:include page="create-product-modal.jsp"/>
                <jsp:include page="edit-product-modal.jsp"/>
            </div>
          </div>
        </div>
      </main>
    </div>

  </div>
</section>
<!-- content -->

<script>
    $(document).ready(function () {
        // Handle card click event
        $('.card-link').click(function () {
            // Get card information relative to the clicked card
            let card = $(this);  // The clicked card
            let title = card.find('.mb-1#name').text();
            let description = card.find('.mb-1#description').text();
            let category = card.find('.mb-1#category').text();
            let keywords = card.find('.mb-1#keywords').text();
            let stock = card.find('.mb-1#stock').text();
            let price = card.find('.mb-1#price').text();
            let productID = card.find('.mb-1#productID').text();  // Make sure you have an element with id 'productID'
            let imageUrl = card.find('img').attr('src');

            // Populate modal with card information
            $('#editProductTitle').text("Edit " + title);

            // Set values for the form fields
            $('#editProductName').val(title);
            $('#editProductDescription').val(description);
            $('#editProductCategory').val(category);
            $('#editProductKeywords').val(keywords);
            $('#editProductPrice').val(price);
            $('#editProductQuantity').val(stock);
            $('#editProductID').val(productID);
            $('#editImage').attr('src', imageUrl);

            // Show the modal
            $('#editProduct').modal('show');
        });
    });

</script>