<%@ page import="com.peach.sellbuy.util.Templates" %>
<%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 1:27 AM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Register", new String[]{"Home", "Register"}, new String[]{"index.jsp", "register.jsp"}) %>

<jsp:include page="templates/register-form.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>