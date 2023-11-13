<%@ page import="com.peach.sellbuy.util.Templates" %>
<%@ page import="com.peach.sellbuy.business.User" %>
<%@ page import="com.peach.sellbuy.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/7/23
  Time: 11:40 AM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        session.setAttribute("failMessage", "You must first sign in to view your cart!");
        response.sendRedirect(Util.webPage("login.jsp"));
    }
%>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Checkout", new String[]{"Home", "Shopping Cart", "Checkout"}, new String[]{"index.jsp", "my-cart.jsp", "checkout.jsp"}) %>

<jsp:include page="templates/checkout-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>