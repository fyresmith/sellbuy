<%@ page import="java.util.Objects" %>
<%@ page import="com.peach.sellbuy.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 11:43 AM

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


<div class="sign-in-card container border card rounded-4 d-lg-block p-5 pt-4 mt-5 mb-5">
    <form class=" card-body justify-content-center align-items-center mb-0 pb-0" style="text-align: center;" action="<%= Util.webPage("login") %>">
        <% if (!Objects.equals(failMessage, "")) { %>
            <p class="text-danger">${failMessage}</p>
        <% } %>

        <% if (!Objects.equals(successMessage, "")) { %>
            <p class="text-success">${successMessage}</p>
        <% } %>

        <h1 class="mb-4">Sign In!</h1>

        <!-- Email input -->
        <div class="card-title form-outline mb-4">
            <input type="email" name="userEmail" id="userEmail" class="form-control" />
            <label class="form-label" for="userEmail">Email Address</label>
        </div>

        <!-- Password input -->
        <div class="form-outline mb-4">
            <input type="password" name="userPassword" id="userPassword" class="form-control" />
            <label class="form-label" for="userPassword">Password</label>
        </div>

        <!-- 2 column grid layout for inline styling -->
        <div class="row mb-4">
            <div class="col d-flex justify-content-center">
                <!-- Checkbox -->
                <div class="form-check">
                    <input class="form-check-input" name="remember" type="checkbox" value="" id="rememberUser" checked />
                    <label class="form-check-label" for="rememberUser"> Remember me </label>
                </div>
            </div>

            <div class="col">
                <!-- Simple link -->
                <a href="password-reset.jsp">Forgot password?</a>
            </div>
        </div>

        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>

        <!-- Register buttons -->
        <div class="text-center mb-0 p-0">
            <p>Not a member? <a href="register.jsp">Register</a></p>
<%--            <p>or sign up with:</p>--%>
<%--            <button type="button" class="btn btn-link btn-floating mx-1">--%>
<%--                <i class="fab fa-facebook-f"></i>--%>
<%--            </button>--%>

<%--            <button type="button" class="btn btn-link btn-floating mx-1">--%>
<%--                <i class="fab fa-google"></i>--%>
<%--            </button>--%>

<%--            <button type="button" class="btn btn-link btn-floating mx-1">--%>
<%--                <i class="fab fa-twitter"></i>--%>
<%--            </button>--%>

<%--            <button type="button" class="btn btn-link btn-floating mx-1">--%>
<%--                <i class="fab fa-github"></i>--%>
<%--            </button>--%>
        </div>
    </form>
</div>