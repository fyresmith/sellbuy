<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 12:53 AM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Login", new String[]{"Home", "Login"}, new String[]{"index.jsp", "login.jsp"}) %>

<jsp:include page="templates/login-form.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>