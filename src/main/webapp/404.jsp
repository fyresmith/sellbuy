<%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/6/23
  Time: 2:46 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="templates/head.jsp" />
<jsp:include page="templates/header.jsp" />

<div class="container my-5 h-auto">
    <div class="card d-lg-block px-3 py-3 border">
        <div class="card-body">
            <div class="col-md-6 offset-md-3 text-center">
                <h1 class="display-4">404</h1>
                <p class="lead">Page Not Found</p>
                <p>The page you are looking for might have been removed or is temporarily unavailable.</p>
                <a href="index.jsp" class="btn btn-primary">Go to Home</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="templates/footer-main.jsp" />