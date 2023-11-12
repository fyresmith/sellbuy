<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/11/23
  Time: 11:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="insertProduct" tabindex="-1" aria-labelledby="insertProduct" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <form class="modal-content" enctype="multipart/form-data" method="POST" action="<%= Util.webRoot("add-product") %>">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add Product - Product Listing Details</h5>
        <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="text-center border border-light p-1">
          <!-- Name/Title input -->
          <div class="form-outline mb-4">
            <input type="text" name="productName" id="createProductName" class="form-control" minlength="2" required />
            <label class="form-label" for="createProductName">Name/Title</label>
          </div>

          <!-- Description input -->
          <div class="form-outline mb-4">
            <textarea class="form-control" name="productDescription" id="createProductDescription" rows="4" minlength="15" required></textarea>
            <label class="form-label" for="createProductDescription">Description</label>
          </div>

          <!-- Category input -->
          <div class="form-outline mb-4">
            <input type="text" name="productCategory" id="createProductCategory" class="form-control" minlength="1" required />
            <label class="form-label" for="createProductCategory">Category</label>
          </div>

          <!-- Keywords input -->
          <div class="form-outline mb-4">
            <input type="text" name="productKeywords" id="createProductKeywords" class="form-control" />
            <label class="form-label" for="createProductKeywords">Keywords</label>
          </div>

          <div class="row">
            <!-- Price input -->
            <div class="col-md-6 mb-4">
              <div class="form-outline">
                <input type="number" name="productPrice" id="createProductPrice" class="form-control" min="1" max="100000" required />
                <label class="form-label" for="createProductPrice">Price</label>
              </div>
            </div>

            <!-- Quantity input -->
            <div class="col-md-6 mb-4">
              <div class="form-outline">
                <input type="number" name="productQuantity" id="createProductQuantity" class="form-control" min="1" max="20" required />
                <label class="form-label" for="createProductQuantity">Quantity</label>
              </div>
            </div>
          </div>


          <!-- File upload input -->
          <div class="mb-4">
            <input type="file" multiple name="productImage" id="createProductImage" class="form-control" />
            <%--    <label class="form-label" for="createProductImage">Upload Image</label>--%>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">Cancel</button>
        <button type="submit" class="btn btn-primary">Publish Product</button>
      </div>
    </form>
  </div>
</div>

<script>
  $("#productImage").on("change", function (e) {

    let count = 1;
    let files = e.currentTarget.files; // puts all files into an array

    // call them as such; files[0].size will get you the file size of the 0th file
    for (var x in files) {

      var filesize = ((files[x].size/1024)/1024).toFixed(4); // MB

      if (files[x].name !== "item" && typeof files[x].name != "undefined" && filesize <= 10) {

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