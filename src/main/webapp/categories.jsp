<%@ page import="com.peach.sellbuy.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/7/23
  Time: 2:42 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Categories", new String[]{"Home", "Categories"}, new String[]{"index.jsp", "categories.jsp"}) %>

<jsp:include page="templates/category-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>