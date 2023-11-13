<%@ page import="java.util.Objects" %>
<%@ page import="com.peach.sellbuy.business.User" %>
<%@ page import="com.peach.sellbuy.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/19/23
  Time: 9:54 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  User user = (User) session.getAttribute("user");
  String query = (String) session.getAttribute("query");

  if (query != null) {
    if (Util.isRawCategory(query)) {
      query = Util.styleCategory(query);
    }
  }
%>

<header>
  <!-- Jumbotron -->
  <div class="p-3 text-center bg-white border-bottom">
    <div class="container">
      <div class="row gy-3">
        <!-- Left elements -->
        <div class="col-lg-2 col-sm-4 col-4">
          <a href="<%= Util.webPage("") %>" class="float-start">
            <img src="assets/sellbuy_logo.png" height="35" />
          </a>
        </div>
        <!-- Left elements -->

        <!-- Center elements -->
        <div class="order-lg-last col-lg-5 col-sm-8 col-8">
          <div class="d-flex float-end">
            <% if (user != null) { %>
              <a href="user-account.jsp" class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center"> <i class="fas fa-user-alt m-1 me-md-2"></i><p class="d-none d-md-block mb-0">User Profile</p> </a>
            <% } else {%>
              <a href="login.jsp" class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center"> <i class="fas fa-user-alt m-1 me-md-2"></i><p class="d-none d-md-block mb-0">Sign in</p> </a>
            <% } %>

            <a href="my-cart.jsp" class="border rounded py-1 px-3 nav-link d-flex align-items-center"> <i class="fas fa-shopping-cart m-1 me-md-2"></i><p class="d-none d-md-block mb-0">My cart</p> </a>
          </div>
        </div>
        <!-- Center elements -->

        <!-- Right elements -->
        <div class="col-lg-5 col-md-12 col-12">
          <form class="input-group float-center" action="<%= Util.webPage("search") %>">
            <div class="form-outline input-group">
              <% if (query == null) { %>
                <input type="search" name="searchBar" id="searchBar" class="amber-border form-control" />
              <% } else { %>
                <input type="search" name="searchBar" id="searchBar" class="amber-border form-control" value="<%= query %>" />
              <% } %>
              <label class="form-label" for="searchBar">Search</label>
            </div>
            <button type="submit" class="btn btn-primary shadow-0">
              <i class="fas fa-search"></i>
            </button>
          </form>
        </div>
        <!-- Right elements -->
      </div>
    </div>
  </div>