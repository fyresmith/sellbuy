<%@ page import="com.peach.sellbuy.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 12:18 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Password Reset", new String[]{"Home", "Login", "Password Reset"}, new String[]{"index.jsp", "login.jsp", "password-reset.jsp"}) %>

<jsp:include page="templates/pw-reset-form.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>