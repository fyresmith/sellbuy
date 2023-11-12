<%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/11/23
  Time: 11:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="text-center border border-light p-1">
  <!-- Name/Title input -->
  <div class="form-outline mb-4">
    <input type="text" name="productName" id="productName" class="form-control" minlength="2" required />
    <label class="form-label" for="productName">Name/Title</label>
  </div>

  <!-- Description input -->
  <div class="form-outline mb-4">
    <textarea class="form-control" name="productDescription" id="productDescription" rows="4" minlength="15" required></textarea>
    <label class="form-label" for="productDescription">Description</label>
  </div>

  <!-- Category input -->
  <div class="form-outline mb-4">
    <input type="text" name="productCategory" id="productCategory" class="form-control" minlength="1" required />
    <label class="form-label" for="productCategory">Category</label>
  </div>

  <!-- Keywords input -->
  <div class="form-outline mb-4">
    <input type="text" name="productKeywords" id="productKeywords" class="form-control" />
    <label class="form-label" for="productKeywords">Keywords</label>
  </div>

  <div class="row">
    <!-- Price input -->
    <div class="col-md-6 mb-4">
      <div class="form-outline">
        <input type="number" name="productPrice" id="productPrice" class="form-control" min="1" max="100000" required />
        <label class="form-label" for="productPrice">Price</label>
      </div>
    </div>

    <!-- Quantity input -->
    <div class="col-md-6 mb-4">
      <div class="form-outline">
        <input type="number" name="productQuantity" id="productQuantity" class="form-control" min="1" max="20" required />
        <label class="form-label" for="productQuantity">Quantity</label>
      </div>
    </div>
  </div>


  <!-- File upload input -->
  <div class="mb-4">
    <input type="file" name="productImage" id="productImage" class="form-control" />
<%--    <label class="form-label" for="productImage">Upload Image</label>--%>
  </div>
</div>

<script>
  $("#productImage").on("change", function (e) {

    var count=1;
    var files = e.currentTarget.files; // puts all files into an array

    // call them as such; files[0].size will get you the file size of the 0th file
    for (var x in files) {

      var filesize = ((files[x].size/1024)/1024).toFixed(4); // MB

      if (files[x].name != "item" && typeof files[x].name != "undefined" && filesize <= 10) {

        if (count > 1) {

          approvedHTML += ", "+files[x].name;
        }
        else {

          approvedHTML += files[x].name;
        }

        count++;
      }
    }
    $("#approvedFiles").val(approvedHTML);

  });
</script>