<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 12:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<jsp:include page="templates/nav.jsp"/>

<%= Templates.locationJumbo("Login", new String[]{"Home", "Login"}) %>

<jsp:include page="templates/login-form.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>