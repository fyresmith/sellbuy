<%@ page import="com.peach.sellbuy.util.Util" %>

<%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/13/23
  Time: 11:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="editEmailModal" tabindex="-1" aria-labelledby="editEmailModal" aria-hidden="true">
  <div class="modal-dialog">
    <form onsubmit="return validateEmails()" class="modal-content" enctype="multipart/form-data" action="<%= Util.webPage("change-email") %>">
      <div class="modal-header">
        <h5 class="modal-title" id="editProductTitle">Add Product - Product Listing Details</h5>
        <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="editProductBody">

        <!-- Email input -->
        <div class="form-outline mb-4">
          <input type="email" name="newEmail" id="newEmail" class="form-control" value="" />
          <label class="form-label" for="newEmail">Email Address</label>
        </div>

        <!-- Email input -->
        <div class="form-outline mb-4">
          <input type="email" name="confirmedEmail" id="confirmNewEmail" class="form-control" value="" />
          <label class="form-label" for="confirmNewEmail">Confirm Email Address</label>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">Cancel</button>
        <button type="submit" class="btn btn-primary">Confirm Changes</button>
      </div>
    </form>
  </div>
</div>

<!-- Add this script to your HTML file -->
<script>
  function validateEmails() {
    // Get the values of the email inputs
    var newEmail = document.getElementById("newEmail").value;
    var confirmNewEmail = document.getElementById("confirmNewEmail").value;

    // Compare the email values
    if (newEmail !== confirmNewEmail) {
      // If they don't match, show an error message
      alert("Email addresses do not match!");
      return false; // Prevent form submission
    }

    // If they match, you can proceed with the form submission or any other action
    return true;
  }
</script>