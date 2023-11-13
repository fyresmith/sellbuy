<%@ page import="java.util.Objects" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 12:19 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if (session.getAttribute("failMessage") == null) {
    session.setAttribute("failMessage", "");
  }

  if (session.getAttribute("successMessage") == null) {
    session.setAttribute("successMessage", "");
  }

  String failMessage = (String) session.getAttribute("failMessage");
  String successMessage = (String) session.getAttribute("successMessage");
%>


<% if (Objects.equals(successMessage, "")) { %>
  <div class="container d-flex justify-content-center mb-5 mt-5">
    <form class="reset-card card d-lg-block text-center" action="<%= Util.webPage("password-reset") %>">
      <div class="card-header h5 text-white bg-primary">Password Reset</div>
      <div class="card-body px-5">
        <% if (!Objects.equals(failMessage, "")) { %>
          <p class="text-danger">${failMessage}</p>
        <% } %>

        <% if (!Objects.equals(successMessage, "")) { %>
          <p class="text-success">${successMessage}</p>
        <% } %>

        <p class="card-text py-2">
          Enter your email address and we'll send you an email with instructions to reset your password.
        </p>
        <div class="form-outline">
          <input type="email" name="userEmail" id="userEmail" class="form-control my-3" />
          <label class="form-label" for="userEmail">Email</label>
        </div>
        <button type="submit" class="btn btn-primary w-100">Reset password</button>
        <div class="d-flex justify-content-between mt-4">
          <a class="" href="login.jsp">Login</a>
          <a class="" href="register.jsp">Register</a>
        </div>
      </div>
    </form>
  </div>
<% } else if (!(Objects.equals(successMessage, "PWRESET"))) { %>
  <div class="container d-flex justify-content-center mb-5 mt-5">
    <form class="reset-card card d-lg-block text-center" action="<%= Util.webPage("reset-code") %>">
      <div class="card-header h5 text-white bg-primary">Enter Code</div>
      <div class="card-body px-5">
        <% if (!Objects.equals(failMessage, "")) { %>
          <p class="text-danger">${failMessage}</p>
        <% } %>

        <% if (!Objects.equals(successMessage, "")) { %>
          <p class="text-success">${successMessage}</p>
        <% } %>

        <p class="card-text py-2">
          Enter the code we just sent your email inbox, and we'll help you reset your password.
        </p>
        <div class="form-outline">
          <input maxlength="6" minlength="6" type="text" name="userCode" id="userCode" class="form-control my-3" />
          <label class="form-label" for="userCode">Enter Code</label>
        </div>
        <button type="submit" class="btn btn-primary w-100">Verify</button>
      </div>
    </form>
  </div>
<% } else { %>
<div class="container d-flex justify-content-center mb-5 mt-5">
  <form class="reset-card card d-lg-block text-center" action="<%= Util.webPage("password-reset-final") %>">
    <div class="card-header h5 text-white bg-primary">Enter New Password</div>
    <div class="card-body px-5">
      <% if (!Objects.equals(failMessage, "")) { %>
      <p class="text-danger">${failMessage}</p>
      <% } %>

      <p class="card-text py-2">
        Enter a new password and we'll reset it for you!
      </p>
      <div class="form-outline">
        <input type="password" name="newPassword" id="newPassword" class="form-control my-3" />
        <label class="form-label" for="newPassword">Enter Password</label>
      </div>
      <div class="form-outline">
        <input type="password" name="confirmedPassword" id="confirmedPassword" class="form-control my-3" />
        <label class="form-label" for="confirmedPassword">Confirm Password</label>
      </div>
      <button type="submit" class="btn btn-primary w-100">Reset Password</button>
    </div>
  </form>
</div>
<% } %>