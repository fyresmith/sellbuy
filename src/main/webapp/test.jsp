<%@ page import="io.github.cdimascio.dotenv.Dotenv" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/13/23
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--  Dotenv dotenv = Dotenv.configure().directory(".").filename(".env").load();--%>
<%--%>--%>

<%--<%= dotenv.get("DATA") %>--%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<div class="container">
  <h2>Basic Progress Bar</h2>
  <div class="progress" id="progressBarContainer" style="height:30px; !important;">
    <div id="progressBar" class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
      <span class="sr-only">70% Complete</span>
    </div>
  </div>
</div>

<%--<div class="container p-5">--%>
<%--  <!-- Your HTML structure with a progress bar -->--%>
<%--  <div id="progressBarContainer" class="progress">--%>
<%--    <div id="progressBar" style="width: 25%;" aria-valuenow="0" max="100" aria-valuemin="0" aria-valuemax="300" role="progressbar" class="progress-bar"></div>--%>
<%--  </div>--%>
<%--</div>--%>

<script>
  // Function to update the progress bar
  function updateProgressBar(progress) {
    $('#progressBar').attr('aria-valuenow', progress);
    $('#progressBar').css('width', progress + "%");
    // If '#progressBar' is an input element, you can use the 'val' method
    // $('#progressBar').val(progress);
  }

  // Function to start the population process
  function startPopulation() {
    // Set interval to call the server every 1000 milliseconds (1 second)
    var intervalId = setInterval(function () {
      $.ajax({
        url: 'TestServlet',
        method: 'GET',
        success: function (response) {
          if (response === 'DONE') {
            // Database population is complete, handle completion
            console.log('Database population complete!');
            updateProgressBar("100")
            clearInterval(intervalId); // Stop calling the server when done
          } else {
            console.log(response);
            // Update the progress bar
            updateProgressBar(response);
          }
        },
        error: function (error) {
          console.error('Error:', error);
          clearInterval(intervalId); // Stop calling the server on error
        }
      });
    }, 1000); // Adjust the interval as needed (in milliseconds)
  }

  // Start the population process when the page loads
  $(document).ready(function () {
    startPopulation();
  });

</script>
