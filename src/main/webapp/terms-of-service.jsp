<%@ page import="com.peach.sellbuy.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 6:10 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Terms of Service", new String[]{"Home", "Terms of Service"}, new String[]{"index.jsp", "terms-of-service.jsp"}) %>

<jsp:include page="templates/tos-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>