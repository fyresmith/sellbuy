<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.User" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        session.setAttribute("failMessage", "You must first sign in to view your cart!");
        response.sendRedirect(Util.webRoot("login.jsp"));
    }
%>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("My Cart", new String[]{"Home", "My Cart"}, new String[]{"index.jsp", "my-cart.jsp"}) %>

<jsp:include page="templates/cart-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>