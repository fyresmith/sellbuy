<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.Product" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.Access" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/7/23
  Time: 2:42 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  Access<Product> access = new Access<>("product", "productID", Product.class);
  LinkedList<String> categories = Util.styleCategories(access.getProductCategories());

  int firstThird = categories.size() / 3;
  int secondThird = (categories.size() / 3) * 2;
  int finalThird = categories.size();
%>

<div class="container my-5">
  <div class="card shadow border">
    <div class="card-body">
      <!-- Heading Section -->
      <h1 class="h1 display-4 fw-bold text-primary">Categories</h1>
      <p class="lead">Welcome to our Categories page, your gateway to a world of diverse offerings. Dive into a curated collection of product categories designed to cater to every interest and need. Whether you're searching for the latest in technology, fashion trends, or unique handmade crafts, our expansive range of categories ensures there's something for everyone. Browse through, discover new passions, and embark on a journey of exploration. Each category is a portal to a realm of possibilities. Start your adventure now!</p>

      <!-- Section Separators -->
      <hr class="my-4">

      <div class="row">
        <div class="col-md-4">
          <ul class="list-unstyled">
            <% for (int i = 0; i < firstThird; i++) { %>
              <li class="p-1"><a href="<%= Util.webPage("search?searchBar=" + Util.rawCategory(categories.get(i))) %>"><%= categories.get(i) %></a></li>
            <% } %>
          </ul>
        </div>
        <div class="col-md-4">
          <ul class="list-unstyled">
            <% for (int i = firstThird; i < secondThird; i++) { %>
              <li class="p-1"><a href="<%= Util.webPage("search?searchBar=" + Util.rawCategory(categories.get(i))) %>"><%= categories.get(i) %></a></li>
            <% } %>
          </ul>
        </div>
        <div class="col-md-4">
          <ul class="list-unstyled">
            <% for (int i = secondThird; i < finalThird; i++) { %>
              <li class="p-1"><a href="<%= Util.webPage("search?searchBar=" + Util.rawCategory(categories.get(i))) %>"><%= categories.get(i) %></a></li>
            <% } %>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>