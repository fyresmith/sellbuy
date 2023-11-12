<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/7/23
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Checkout", new String[]{"Home", "Shopping Cart", "Checkout"}, new String[]{"index.jsp", "my-cart.jsp", "checkout.jsp"}) %>

<jsp:include page="templates/checkout-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>