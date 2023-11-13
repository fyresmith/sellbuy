<%@ page import="com.peach.sellbuy.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/2/23
  Time: 6:54 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Search", new String[]{"Home", "Search"}, new String[]{"index.jsp", "search.jsp"}) %>

<jsp:include page="templates/search-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>