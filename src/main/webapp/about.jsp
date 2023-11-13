<%@ page import="com.peach.sellbuy.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 4:09 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("About", new String[]{"Home", "About"}, new String[]{"index.jsp", "about.jsp"}) %>

<jsp:include page="templates/about-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>