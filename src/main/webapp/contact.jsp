<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 6:28 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Contact Us", new String[]{"Home", "Contact Us"}, new String[]{"index.jsp", "contact.jsp"}) %>

<jsp:include page="templates/contact-form.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>
