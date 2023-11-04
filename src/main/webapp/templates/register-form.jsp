<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if (session.getAttribute("failMessage") == null) {
    session.setAttribute("failMessage", "");
  }

  String failMessage = (String) session.getAttribute("failMessage");

  if (session.getAttribute("successMessage") == null) {
    session.setAttribute("successMessage", "");
  }

  String successMessage = (String) session.getAttribute("successMessage");
%>

<div class="container sign-in-card border card rounded-4 d-lg-block p-5 pt-4 mt-5 mb-5">
  <form class="justify-content-center align-items-center" action="${pageContext.request.contextPath}/create-account-servlet" style="text-align: center;">
    <% if (!Objects.equals(failMessage, "")) { %>
    <p class="text-danger">${failMessage}</p>
    <% } %>

    <% if (!Objects.equals(successMessage, "")) { %>
    <p class="text-success">${successMessage}</p>
    <% } %>

    <h1 class="mb-4">Sign Up!</h1>
    <!-- 2 column grid layout with text inputs for the first and last names -->
    <div class="row">
      <div class="col-md-6 mb-4">
        <div class="form-outline">
          <input type="text" name="firstName" id="firstName" class="form-control" />
          <label class="form-label" for="firstName">First name</label>
        </div>
      </div>
      <div class="col-md-6 mb-4">
        <div class="form-outline">
          <input type="text" name="lastName" id="lastName" class="form-control" />
          <label class="form-label" for="lastName">Last name</label>
        </div>
      </div>
    </div>

    <!-- username input -->
    <div class="form-outline mb-4">
      <input type="text" name="username" id="username" class="form-control" />
      <label class="form-label" for="username">Display Name</label>
    </div>

    <!-- Address input -->
    <div class="form-outline mb-4">
      <input type="address" name="address" id="address" class="form-control" />
      <label class="form-label" for="address">Address</label>
    </div>

    <!-- Email input -->
    <div class="form-outline mb-4">
      <input type="email" name="email" id="email" class="form-control" />
      <label class="form-label" for="email">Email address</label>
    </div>

    <!-- Password input -->
    <div class="form-outline mb-4">
      <input type="password" name="password" id="password" class="form-control" />
      <label class="form-label" for="password">Password</label>
    </div>

    <!-- Checkbox -->
    <div class="form-check d-flex justify-content-center mb-4">
      <input class="form-check-input me-2" name="subscribe" type="checkbox" value="" id="subscribe" checked />
      <label class="form-check-label" for="subscribe">
        Subscribe to our newsletter
      </label>
    </div>

    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4">
      Sign up
    </button>

    <!-- Register buttons -->
    <div class="text-center">
      <p>or sign up with:</p>
      <button type="button" class="btn btn-link btn-floating mx-1">
        <i class="fab fa-facebook-f"></i>
      </button>

      <button type="button" class="btn btn-link btn-floating mx-1">
        <i class="fab fa-google"></i>
      </button>

      <button type="button" class="btn btn-link btn-floating mx-1">
        <i class="fab fa-twitter"></i>
      </button>

      <button type="button" class="btn btn-link btn-floating mx-1">
        <i class="fab fa-github"></i>
      </button>
    </div>
  </form>
</div>