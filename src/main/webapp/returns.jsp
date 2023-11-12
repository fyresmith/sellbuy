<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<%= Templates.locationHeader("Returns", new String[]{"Home", "Returns"}, new String[]{"index.jsp", "returns.jsp"}) %>

<jsp:include page="templates/return-policy-view.jsp"/>

<jsp:include page="templates/footer-main.jsp"/>