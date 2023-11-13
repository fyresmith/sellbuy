<%@ page import="com.peach.sellbuy.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 3:52 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("My Cart", new String[]{"Home", "My Cart"}, new String[]{"index.jsp", "my-cart.jsp"}) %>

<div class="container my-5">

    <!-- MDB Card -->
    <div class="card d-lg-block px-3 py-1 border">
        <h4 class="p-4 text-primary">Success!</h4>
    </div>
</div>

<jsp:include page="templates/footer-main.jsp"/>