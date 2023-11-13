<%@ page import="com.peach.sellbuy.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 4:22 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("FAQ", new String[]{"Home", "FAQ"}, new String[]{"index.jsp", "faq.jsp"}) %>

<jsp:include page="templates/faq-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>