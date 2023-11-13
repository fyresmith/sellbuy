<%@ page import="com.peach.sellbuy.business.Access" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/13/23
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if (!Access.isEmpty()) {
    response.sendRedirect("index.jsp");
  }
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/mdb-ui-kit/css/mdb.min.css" integrity="sha384-GLhlTQ8i1axG1b8DjML\
    nZlMqUaxL1/GGO1aUZt1P1nU1iSvLDJ7aR7M5PZIcad" crossorigin="anonymous">
  <title>MDB Form Page</title>
</head>

<body>
<div class="container mt-5 bg-light">
  <div class="card border">
    <div class="card-body">
      <h3 class="card-title text-center">Simple Form</h3>
      <form>
        <div class="mb-3">
          <label for="name" class="form-label">Name</label>
          <input type="text" class="form-control" id="name" placeholder="Enter your name">
        </div>
        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <input type="email" class="form-control" id="email" placeholder="Enter your email">
        </div>
        <div class="mb-3">
          <label for="message" class="form-label">Message</label>
          <textarea class="form-control" id="message" rows="3" placeholder="Enter your message"></textarea>
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-primary">Submit</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/mdb-ui-kit/js/mdb.min.js" integrity="sha384-rqss/aF8a33MzVZzMHV6Duet1Hq3zQ1r8RaPBM\
    P4Z12XtkCfYEXdAVqI5B9bpA3UZ" crossorigin="anonymous"></script>
</body>

</html>
