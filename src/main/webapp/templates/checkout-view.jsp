<%@ page import="com.peach.sellbuy.business.Cart" %>
<%@ page import="com.peach.sellbuy.business.CartItem" %>
<%@ page import="com.peach.sellbuy.business.User" %>
<%@ page import="com.peach.sellbuy.util.Util" %>
<%-- Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/7/23
  Time: 11:43 AM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Cart cart = (Cart) session.getAttribute("cart");
  if (cart == null) {
    cart = new Cart();
    session.setAttribute("cart", cart);
  }

  User user = (User) session.getAttribute("user");
  if (user == null) {
    session.setAttribute("failMessage", "You must sign in to access your cart!");
    response.sendRedirect(Util.webPage("login.jsp"));
  }
%>

<% if (!cart.isEmpty()) {
    assert user != null;%>
<section class="bg-light py-5">
  <div class="container">
    <div class="row">
      <div class="col-xl-8 col-lg-8 mb-4">
        <div class="card shadow-0 border">
          <div class="card-header py-3">
            <h5 class="mb-0">Billing details</h5>
          </div>
          <div class="card-body">
            <form action="<%= Util.webPage("checkout") %>">
<%--              <!-- 2 column grid layout with text inputs for the first and last names -->--%>
<%--              <div class="row mb-4">--%>
<%--                <div class="col">--%>
<%--                  <div class="form-outline">--%>
<%--                    <input type="text" name="firstName" id="form6Example1" value="<%= user.getFirstName() %>" class="form-control" />--%>
<%--                    <label class="form-label" for="form6Example1">First name</label>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--                <div class="col">--%>
<%--                  <div class="form-outline">--%>
<%--                    <input type="text" name="lastName" id="form6Example2" value="<%= user.getLastName() %>" class="form-control" />--%>
<%--                    <label class="form-label" for="form6Example2">Last name</label>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--              </div>--%>

<%--&lt;%&ndash;              <!-- Text input -->&ndash;%&gt;--%>
<%--&lt;%&ndash;              <div class="form-outline mb-4">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <input type="text" id="form6Example3" class="form-control" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                <label class="form-label" for="form6Example3">Company name</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;              </div>&ndash;%&gt;--%>

<%--              <!-- Text input -->--%>
<%--              <div class="form-outline mb-4">--%>
<%--                <input type="text" name="shippingAddress" id="form6Example4" value="<%= user.getShippingAddress() %>" class="form-control" />--%>
<%--                <label class="form-label" for="form6Example4">Address</label>--%>
<%--              </div>--%>

<%--              <!-- Email input -->--%>
<%--              <div class="form-outline mb-4">--%>
<%--                <input type="email" name="email" id="form6Example5" value="<%= user.getEmail() %>" class="form-control" />--%>
<%--                <label class="form-label" for="form6Example5">Email</label>--%>
<%--              </div>--%>

<%--              <!-- Number input -->--%>
<%--              <div class="form-outline mb-4">--%>
<%--                <input type="number" name="phoneNumber" id="form6Example6" class="form-control" />--%>
<%--                <label class="form-label" for="form6Example6">Phone</label>--%>
<%--              </div>--%>

<%--              <hr class="my-4" />--%>

<%--              <div class="form-check">--%>
<%--                <input class="form-check-input" type="checkbox" value="" id="checkoutForm1" />--%>
<%--                <label class="form-check-label" for="checkoutForm1">--%>
<%--                  Shipping address is the same as my billing address--%>
<%--                </label>--%>
<%--              </div>--%>

<%--              <div class="form-check mb-4">--%>
<%--                <input class="form-check-input" type="checkbox" value="" id="checkoutForm2" checked />--%>
<%--                <label class="form-check-label" for="checkoutForm2">--%>
<%--                  Save this information for next time--%>
<%--                </label>--%>
<%--              </div>--%>

<%--              <hr class="my-4" />--%>

              <h5 class="mb-4">Payment</h5>

              <div class="form-check">
                <input class="form-check-input" type="radio" name="cardType" id="checkoutForm3" value="credit" checked />
                <label class="form-check-label" for="checkoutForm3">
                  Credit card
                </label>
              </div>

              <div class="form-check mb-4">
                <input class="form-check-input" type="radio" name="cardType" id="checkoutForm4" value="debit" />
                <label class="form-check-label" for="checkoutForm4">
                  Debit card
                </label>
              </div>

<%--              <div class="form-check mb-4">--%>
<%--                <input class="form-check-input" type="radio" name="flexRadioDefault" id="checkoutForm5" />--%>
<%--                <label class="form-check-label" for="checkoutForm5">--%>
<%--                  Paypal--%>
<%--                </label>--%>
<%--              </div>--%>

              <div class="row mb-4">
                <div class="col">
                  <div class="form-outline">
                    <input type="text" name="nameOnCard" id="formNameOnCard" class="form-control" />
                    <label class="form-label" for="formNameOnCard">Name on Card</label>
                  </div>
                </div>
                <div class="col">
                  <div class="form-outline">
                    <input type="text" name="cardNumber" minlength="16" maxlength="16" id="formCardNumber" class="form-control" />
                    <label class="form-label" for="formCardNumber">Credit Card Number</label>
                  </div>
                </div>
              </div>

              <div class="row mb-4">
                <div class="col-3">
                  <div class="form-outline">
                    <input type="text" minlength="5" maxlength="5" name="expiration" id="formExpiration" class="form-control" />
                    <label class="form-label" for="formExpiration">Expiration</label>
                  </div>
                </div>
                <div class="col-3">
                  <div class="form-outline">
                    <input type="text" name="cvv" id="formCVV" class="form-control" />
                    <label class="form-label" for="formCVV">CVV</label>
                  </div>
                </div>
              </div>

              <button class="btn btn-primary btn-lg btn-block" type="submit">
                confirm order
              </button>
            </form>
          </div>
        </div>
      </div>
      <div class="col-xl-4 col-lg-4 d-flex justify-content-center justify-content-lg-end">
        <div class="ms-lg-4 mt-4 mt-lg-0" style="max-width: 320px;">
          <h6 class="mb-3">Summary</h6>
          <div class="d-flex justify-content-between">
            <p class="mb-2">Total Price:</p>
            <p class="mb-2">$<%= cart.getTax() + cart.getTotal() %></p>
          </div>
          <div class="d-flex justify-content-between">
            <p class="mb-2">Shipping Cost:</p>
            <p class="mb-2">+ $0.00</p>
          </div>
          <hr />
          <div class="d-flex justify-content-between">
            <p class="mb-2">Total Price:</p>
            <p class="mb-2 fw-bold">$<%= cart.getTax() + cart.getTotal() %></p>
          </div>

          <div class="input-group mt-3 mb-4">
            <input type="text" class="form-control border" name="" placeholder="Promo code" />
            <button class="btn btn-light text-primary border">Apply</button>
          </div>

          <hr />
          <h6 class="text-dark my-4">Items in Cart</h6>

          <% for (CartItem item : cart.getCartItems()) { %>
          <%= CartItem.checkoutCard(item) %>
          <% } %>
        </div>
      </div>
    </div>
  </div>
</section>
<% } else { %>
<div class="container my-5">
  <div class="card d-lg-block px-3 py-1 border">
    <h4 class="p-4">Your shopping cart is empty!</h4>
  </div>
</div>
<% } %>
