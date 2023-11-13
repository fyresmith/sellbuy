<%@ page import="com.peach.sellbuy.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 6:23 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Privacy Policy", new String[]{"Home", "Privicy Policy"}, new String[]{"index.jsp", "privacy-policy.jsp"}) %>

<jsp:include page="templates/privacy-policy-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>