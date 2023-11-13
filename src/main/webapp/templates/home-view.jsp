<%@ page import="com.peach.sellbuy_ecommerce.business.Product" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.Access" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/7/23
  Time: 12:15 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Access<Product> access = new Access<>("product", "productID", Product.class);
  LinkedList<Product> products = access.getRandomObjects(20);
%>

<!-- Products -->
<section>
  <div class="container my-5">
    <header class="mb-4">
      <h3>Featured Products</h3>
    </header>

    <div class="row">
        <% for (Product product : products) { %>
            <%= Product.homeViewCard(product) %>
        <% } %>
    </div>
  </div>
</section>