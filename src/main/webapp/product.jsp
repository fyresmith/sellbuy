<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/24/23
  Time: 7:10 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Product Details", new String[]{"Home", "Search", "Product Details"}, new String[]{"index.jsp", "search.jsp", "product.jsp"}) %>

<jsp:include page="templates/product-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>