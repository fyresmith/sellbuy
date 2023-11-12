<%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script>--%>
<%--  // Function to show the alert modal--%>
<%--  function showAlert(message) {--%>
<%--    // Set the message in the modal--%>
<%--    document.getElementById("alertMessage").innerText = message;--%>

<%--    // Show the overlay--%>
<%--    document.getElementById("overlay").style.display = "block";--%>

<%--    // Show the modal--%>
<%--    jQuery('#alertModal').modal('show');--%>

<%--    // Hide the modal and overlay after 3 seconds--%>
<%--    setTimeout(function() {--%>
<%--      $('#alertModal').modal('hide');--%>
<%--      document.getElementById("overlay").style.display = "none";--%>
<%--    }, 3000);--%>
<%--  }--%>
<%--</script>--%>

<!-- Modal for displaying the alert -->
<div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content border-primary border-3 rounded">
      <div class="modal-header">
        <h5 class="modal-title" id="alertTitle"></h5>
        <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body p-3">
        <h6 id="alertMessage"></h6>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Continue Shopping</button>
      </div>
    </div>
  </div>
</div>


<!-- Dark overlay -->
<div id="overlay" class="overlay"></div>

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

      // Show the overlay
      document.getElementById("overlay").style.display = "block";

      // Show the modal
      $('#alertModal').modal('show');

      // Handle the modal's hide.bs.modal event
      $('#alertModal').on('hide.bs.modal', function (e) {
        // Clear the overlay
        document.getElementById("overlay").style.display = "none";
      });

      // Handle the "Continue Shopping" button click
      $('#alertModal').find('.btn-primary').click(function() {
        // Clear the overlay
        document.getElementById("overlay").style.display = "none";

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

<%--<!-- Modal -->--%>
<%--<div class="modal fade" id="cartAddModal" tabindex="-1" aria-labelledby="cartAddModalLabel" aria-hidden="true">--%>
<%--  <div class="modal-dialog">--%>
<%--    <div class="modal-content">--%>
<%--      <div class="modal-header">--%>
<%--        <h5 class="modal-title" id="cartAddModalLabel">Item Added to Cart!</h5>--%>
<%--        <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>--%>
<%--      </div>--%>
<%--      <div class="modal-body">...</div>--%>
<%--      <div class="modal-footer">--%>
<%--        <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">Continue Shopping</button>--%>
<%--        <button type="button" class="btn btn-primary">My Cart</button>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--</div>--%>