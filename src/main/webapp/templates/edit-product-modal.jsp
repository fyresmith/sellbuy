<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/11/23
  Time: 11:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="editProduct" tabindex="-1" aria-labelledby="editProduct" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <form class="modal-content" enctype="multipart/form-data" method="POST" action="<%= Util.webRoot("add-product") %>">
      <div class="modal-header">
        <h5 class="modal-title" id="editProductTitle">Add Product - Product Listing Details</h5>
        <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="editProductBody">
        <div class="text-center p-1">
          <!-- Name/Title input -->
          <div class="form-outline mb-4">
            <input type="text" name="productName" id="editProductName" class="form-control" minlength="2" required />
            <label class="form-label" for="editProductName">Name/Title</label>
          </div>

          <!-- Description input -->
          <div class="form-outline mb-4">
            <textarea class="form-control" name="productDescription" id="editProductDescription" rows="4" minlength="15" required></textarea>
            <label class="form-label" for="editProductDescription">Description</label>
          </div>

          <!-- Category input -->
          <div class="form-outline mb-4">
            <input type="text" name="productCategory" id="editProductCategory" class="form-control" minlength="1" required />
            <label class="form-label" for="editProductCategory">Category</label>
          </div>

          <!-- Keywords input -->
          <div class="form-outline mb-4">
            <input type="text" name="productKeywords" id="editProductKeywords" class="form-control" />
            <label class="form-label" for="editProductKeywords">Keywords</label>
          </div>

          <div class="row">
            <!-- Price input -->
            <div class="col-md-6 mb-4">
              <div class="form-outline">
                <input type="number" name="productPrice" id="editProductPrice" class="form-control" min="1" max="100000" required />
                <label class="form-label" for="editProductPrice">Price</label>
              </div>
            </div>

            <!-- Quantity input -->
            <div class="col-md-6 mb-4">
              <div class="form-outline">
                <input type="number" name="productQuantity" id="editProductQuantity" class="form-control" min="1" max="20" required />
                <label class="form-label" for="editProductQuantity">Quantity</label>
              </div>
            </div>
          </div>


          <!-- File upload input -->
          <div class="mb-4">
            <input type="file" name="productImage" id="editProductImage" class="form-control" />
            <%--    <label class="form-label" for="productImage">Upload Image</label>--%>
          </div>
        </div>

        <!-- Centered Image at the Bottom -->
        <div class="text-center mt-4 border rounded-2">
          <img width="150" height="150" src="" alt="Centered Image" id="editImage" class="img-fluid rounded" />
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">Cancel</button>
        <button type="submit" class="btn btn-danger">Delete Product</button>
        <button type="submit" class="btn btn-primary">Republish Product</button>
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