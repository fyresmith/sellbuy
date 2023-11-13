<%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 3:19 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal for displaying the alert -->
<div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content border-primary border-3 rounded">
      <div class="modal-header">
        <h5 class="modal-title" id="alertTitle"></h5>
        <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body p-3">
        <h6 id="alertMessage"></h6>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary" data-dismiss="modal">Continue</button>
      </div>
    </div>
  </div>
</div>


<!-- Dark overlay -->
<%--<div id="overlay" class="overlay"></div>--%>

<%
  String alertMessage = (String) session.getAttribute("alertMessage");
  String alertTitle = (String) session.getAttribute("alertTitle");
  session.setAttribute("alertMessage", "");
  session.setAttribute("alertTitle", "");
%>

<script>
  $(document).ready(function() {
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

    // Example usage of showAlert function
    var alertMessage = "<%= alertMessage %>";
    var alertTitle = "<%= alertTitle %>";

    if (!alertTitle || alertTitle == "null") {
      alertTitle = "Alert!"
    }

    if (alertMessage && alertMessage !== "null") {
      showAlert(alertMessage, alertTitle);
    }
  });
</script>