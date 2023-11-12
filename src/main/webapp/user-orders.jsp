<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.User" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 12:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% User user = (User) session.getAttribute("user"); %>

<% if (user == null) { %>
    <jsp:forward page="login.jsp"/>
<% } %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Orders", new String[]{"Home", "User Account", "Orders"}, new String[]{"index.jsp", "user-account.jsp", "user-orders.jsp"}) %>

<jsp:include page="templates/orders-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>