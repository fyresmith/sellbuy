<%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/26/23
  Time: 4:22 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container my-5">

  <!-- MDB Card -->
  <div class="card d-lg-block px-3 py-1 border">
    <div class="card-body">
      <!-- Heading Section -->
      <h1 class="h1 display-4 fw-bold text-primary">Frequently Asked Questions</h1>
      <p class="lead">Have questions? We've got answers! Explore our Frequently Asked Questions (FAQ) to find information on a variety of topics. Whether you're curious about our services, policies, or how to make the most of your experience, this is the go-to resource for all things related to our platform. We've compiled a comprehensive list of queries to provide clarity and assist you on your journey. If you don't find what you're looking for, feel free to reach out to our dedicated support team. Let's make your experience seamless and enjoyable!</p>

      <!-- Section Separators -->
      <hr class="my-4">

      <div class="accordion w-100" id="basicAccordion">
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingOne">
            <button class="accordion-button collapsed" type="button" data-mdb-toggle="collapse"
                    data-mdb-target="#basicAccordionCollapseOne" aria-expanded="false" aria-controls="collapseOne">
              How do I create an account on SellBuy?
            </button>
          </h2>
          <div id="basicAccordionCollapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
               data-mdb-parent="#basicAccordion" style="">
            <div class="accordion-body">
              Creating an account on SellBuy is easy. Simply click on the "Sign Up" button at the top of our website, provide your details, and follow the on-screen instructions. It takes just a few minutes, and you'll be ready to start buying and selling.
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingTwo">
            <button class="accordion-button collapsed" type="button" data-mdb-toggle="collapse"
                    data-mdb-target="#basicAccordionCollapseTwo" aria-expanded="false" aria-controls="collapseTwo">
              What can I sell on SellBuy?
            </button>
          </h2>
          <div id="basicAccordionCollapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
               data-mdb-parent="#basicAccordion" style="">
            <div class="accordion-body">
              You can sell a wide range of items on SellBuy, including electronics, clothing, collectibles, handmade crafts, and much more. As long as your items comply with our policies, you're welcome to list them on our platform.
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingThree">
            <button class="accordion-button collapsed" type="button" data-mdb-toggle="collapse"
                    data-mdb-target="#basicAccordionCollapseThree" aria-expanded="false" aria-controls="collapseThree">
              How do I pay for the items I want to buy on SellBuy?
            </button>
          </h2>
          <div id="basicAccordionCollapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
               data-mdb-parent="#basicAccordion" style="">
            <div class="accordion-body">
              When you find an item you want to purchase, simply click the "Buy Now" or "Add to Cart" button. You can pay using various secure payment methods, including credit/debit cards, PayPal, and more, depending on the seller's preferences.
            </div>
          </div>
        </div>

        <div class="accordion-item">
          <h2 class="accordion-header" id="headingFour">
            <button class="accordion-button collapsed" type="button" data-mdb-toggle="collapse"
                    data-mdb-target="#b4" aria-expanded="false" aria-controls="collapseThree">
              Is it safe to share my personal information on SellBuy?
            </button>
          </h2>
          <div id="b4" class="accordion-collapse collapse" aria-labelledby="headingThree"
               data-mdb-parent="#basicAccordion" style="">
            <div class="accordion-body">
              Your privacy and security are important to us. We employ strict measures to protect your personal information and data. We recommend using a strong password and not sharing sensitive information in public discussions.
            </div>
          </div>
        </div>

        <div class="accordion-item">
          <h2 class="accordion-header" id="headingFive">
            <button class="accordion-button collapsed" type="button" data-mdb-toggle="collapse"
                    data-mdb-target="#b5" aria-expanded="false" aria-controls="collapseThree">
              What happens if I encounter a problem with a purchase or sale on SellBuy?
            </button>
          </h2>
          <div id="b5" class="accordion-collapse collapse" aria-labelledby="headingThree"
               data-mdb-parent="#basicAccordion" style="">
            <div class="accordion-body">
              If you encounter an issue with a transaction, our customer support team is here to help. You can reach out to us, and we'll work to resolve any problems and ensure your satisfaction with the transaction. We also have a robust buyer protection program to assist you in case of any disputes.
            </div>
          </div>
        </div>
      </div>

    </div> <!-- End of MDB Card -->
  </div>
</div>