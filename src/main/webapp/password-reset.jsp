<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 12:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<jsp:include page="templates/nav.jsp"/>

<%= Templates.locationJumbo("Password Reset", new String[]{"Home", "Login", "Password Reset"}) %>

<jsp:include page="templates/pw-reset-form.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>