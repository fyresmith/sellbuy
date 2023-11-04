<%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/19/23
  Time: 9:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white">
  <!-- Container wrapper -->
  <div class="container justify-content-center justify-content-md-between">
    <!-- Toggle button -->
    <button
            class="navbar-toggler border py-2 text-dark"
            type="button"
            data-mdb-toggle="collapse"
            data-mdb-target="#navbarLeftAlignExample"
            aria-controls="navbarLeftAlignExample"
            aria-expanded="false"
            aria-label="Toggle navigation"
    >
      <i class="fas fa-bars"></i>
    </button>

    <!-- Collapsible wrapper -->
    <div class="collapse navbar-collapse" id="navbarLeftAlignExample">
      <!-- Left links -->
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link text-dark" aria-current="page" href="http://localhost:8080/SellBuy_eCommerce-1.0-SNAPSHOT/">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-dark" href="about.jsp">About Us</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-dark" href="login.jsp">Sign In</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-dark" href="#">Categories</a>
        </li>
        <!-- Navbar dropdown -->
<%--        <li class="nav-item dropdown">--%>
<%--          <a class="nav-link dropdown-toggle text-dark" href="#" id="navbarDropdown" role="button" data-mdb-toggle="dropdown" aria-expanded="false">--%>
<%--            Others--%>
<%--          </a>--%>
<%--          <!-- Dropdown menu -->--%>
<%--          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
<%--            <li>--%>
<%--              <a class="dropdown-item" href="#">Action</a>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--              <a class="dropdown-item" href="#">Another action</a>--%>
<%--            </li>--%>
<%--            <li><hr class="dropdown-divider" /></li>--%>
<%--            <li>--%>
<%--              <a class="dropdown-item" href="#">Something else here</a>--%>
<%--            </li>--%>
<%--          </ul>--%>
<%--        </li>--%>
      </ul>
      <!-- Left links -->
    </div>
  </div>
  <!-- Container wrapper -->
</nav>