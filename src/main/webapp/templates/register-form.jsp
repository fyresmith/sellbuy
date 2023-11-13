<%@ page import="java.util.Objects" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %>
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

  String firstName = (String) session.getAttribute("firstName");
  String lastName = (String) session.getAttribute("lastName");
  String username = (String) session.getAttribute("username");
  String streetAddress = (String) session.getAttribute("streetAddress");
  String city = (String) session.getAttribute("city");
  String state = (String) session.getAttribute("state");
  String zipCode = (String) session.getAttribute("zipCode");
  String password = (String) session.getAttribute("password");
  String email = (String) session.getAttribute("email");

  firstName = (firstName == null) ? "" : firstName;
  lastName = (lastName == null) ? "" : lastName;
  username = (username == null) ? "" : username;
  email = (email == null) ? "" : email;
  streetAddress = (streetAddress == null) ? "" : streetAddress;
  city = (city == null) ? "" : city;
  state = (state == null) ? "" : state;
  zipCode = (zipCode == null) ? "" : zipCode;
  password = (password == null) ? "" : password;
%>

<div class="container sign-in-card border card rounded-4 d-lg-block p-5 pt-4 mt-5 mb-5">
  <form class="justify-content-center align-items-center" action="<%= Util.webPage("register-servlet") %>" onsubmit="return validateForm()" style="text-align: center;">
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
          <input type="text" name="firstName" id="firstName" class="form-control" value="<%= firstName %>" />
          <label class="form-label" for="firstName">First Name</label>
        </div>
      </div>
      <div class="col-md-6 mb-4">
        <div class="form-outline">
          <input type="text" name="lastName" id="lastName" class="form-control" value="<%= lastName %>" />
          <label class="form-label" for="lastName">Last Name</label>
        </div>
      </div>
    </div>

    <!-- Email input -->
    <div class="form-outline mb-4">
      <input type="email" name="email" id="email" class="form-control" value="<%= email %>" />
      <label class="form-label" for="email">Email Address</label>
    </div>

    <!-- Address input -->
    <div class="row">
      <div class="col-sm-8 mb-3">
        <div class="form-outline">
          <input type="text" name="streetAddress" id="streetAddress" class="form-control" value="<%= streetAddress %>" />
          <label class="form-label" for="streetAddress">Street Address</label>
        </div>
      </div>
      <div class="col-sm-4 mb-3">
        <div class="form-outline">
          <input type="text" name="city" id="city" class="form-control" value="<%= city %>" />
          <label class="form-label" for="city">City</label>
        </div>
      </div>
      <div class="col-sm-6 col-6 mb-3">
        <div class="form-outline">
          <input type="text" name="state" id="state" class="form-control" minlength="2" maxlength="2" value="<%= state %>" />
          <label class="form-label" for="state">State</label>
        </div>
      </div>
      <div class="col-sm-6 col-6 mb-3">
        <div class="form-outline">
          <input type="text" name="zipCode" id="zipCode" class="form-control" minlength="5" value="<%= zipCode %>" />
          <label class="form-label" for="zipCode">Zip Code</label>
        </div>
      </div>
    </div>

    <!-- Username input -->
    <div class="form-outline mb-4">
      <input type="text" name="username" id="username" class="form-control" value="<%= username %>" />
      <label class="form-label" for="username">Username</label>
    </div>

    <!-- Password input -->
    <div class="form-outline mb-4">
      <input type="password" name="password" id="password" class="form-control" value="<%= password %>" />
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
  </form>
</div>


<script>
  // Function to show the alert modal
  function showAlert(message, title) {
    // Set the message in the modal
    document.getElementById("alertMessage").innerText = message;
    document.getElementById("alertTitle").innerText = title;
    $('#alertModal').modal('show');

    // Handle the "Continue Shopping" button click
    $('#alertModal').find('.btn-primary').click(function() {
      // Hide the modal
      $('#alertModal').modal('hide');
    });
  }

  function validateForm() {
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var email = document.getElementById("email").value;
    var streetAddress = document.getElementById("streetAddress").value;
    var city = document.getElementById("city").value;
    var state = document.getElementById("state").value;
    var zipCode = document.getElementById("zipCode").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if (
            firstName === "" ||
            lastName === "" ||
            email === "" ||
            streetAddress === "" ||
            city === "" ||
            state === "" ||
            zipCode === "" ||
            username === "" ||
            password === ""
    ) {
      showAlert("All fields are required", "Alert!");
      return false;
    }

    // Validate state length
    if (state.length !== 2) {
      showAlert("State must be exactly 2 characters", "Alert!");
      return false;
    }

    return true;
  }
</script>
