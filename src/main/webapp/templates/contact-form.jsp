<%@ page import="java.util.Objects" %>
<%@ page import="com.peach.sellbuy.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 6:29 PM

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

  session.setAttribute("failMessage", "");
  session.setAttribute("successMessage", "");
%>

<div class="sign-in-card contact-form-card container border card rounded-4 d-lg-block p-5 pt-4 mt-5 mb-5">
  <form class="card-body justify-content-center align-items-center mb-0 pb-0" style="text-align: center;" action="<%= Util.webPage("contact-us") %>">
    <% if (!Objects.equals(failMessage, "")) { %>
    <p class="text-danger">${failMessage}</p>
    <% } %>

    <% if (!Objects.equals(successMessage, "")) { %>
    <p class="text-success">${successMessage}</p>
    <% } %>

    <h1 class="mb-4">Get In Touch!</h1>

    <!-- Email input -->
    <div class="card-title form-outline mb-4">
      <input type="email" name="userEmail" id="userEmail" class="form-control" required />
      <label class="form-label" for="userEmail">Your Email Address</label>
    </div>

    <!-- Name input -->
    <div class="form-outline mb-4">
      <input type="text" name="userName" id="userName" class="form-control" required />
      <label class="form-label" for="userName">Your Name</label>
    </div>

    <!-- Message input -->
    <div class="form-outline mb-4">
      <textarea class="form-control" name="userMessage" id="userMessage" rows="4" required></textarea>
      <label class="form-label" for="userMessage">Your Message</label>
    </div>

    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4">Send Message</button>
  </form>
</div>
