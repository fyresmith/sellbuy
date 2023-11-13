<%@ page import="io.github.cdimascio.dotenv.Dotenv" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/13/23
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Dotenv dotenv = Dotenv.configure().directory(".").filename(".env").load();
%>

<%= dotenv.get("DATA") %>
