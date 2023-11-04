<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 6:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<jsp:include page="templates/nav.jsp"/>

<%= Templates.locationJumbo("Privacy Policy", new String[]{"Home", "Privicy Policy"}) %>

<jsp:include page="templates/privacy-policy-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>