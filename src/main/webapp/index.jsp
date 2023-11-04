<%@ page import="com.peach.sellbuy_ecommerce.business.Product" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Templates" %>
<%@ page import="io.github.cdimascio.dotenv.Dotenv" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/19/23
  Time: 6:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String modal = (String) session.getAttribute("modal");
    if (modal == null) {
        modal = "";
    }



//    Dotenv dotenv = Dotenv.load();
//    String database = dotenv.get("DATABASE");
%>

<!--Main Navigation-->
<jsp:include page="templates/head.jsp"/>

<jsp:include page="templates/header.jsp"/>

<jsp:include page="templates/nav.jsp"/>

<jsp:include page="templates/jumbo-home.jsp"/>

<% LinkedList<Product> products = Product.topProductList(30);  %>

<!-- Products -->
<section>
    <div class="container my-5">
        <header class="mb-4">
            <h3>New products</h3>
        </header>

        <div class="row">
            <% for (Product product : products) { %>
                <%= Product.productCard(product, request.getRequestURL().toString()) %>
            <% } %>
        </div>
    </div>
</section>
<!-- Products -->

<jsp:include page="templates/feature-view.jsp"/>

<!-- Blog -->
<section class="mt-5 mb-4">
    <div class="container text-dark">
        <header class="mb-4">
            <h3>Blog posts</h3>
        </header>

        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
                <article>
                    <a href="#" class="img-fluid">
                        <img class="rounded w-100" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/posts/1.webp" style="object-fit: cover;" height="160" />
                    </a>
                    <div class="mt-2 text-muted small d-block mb-1">
            <span>
              <i class="fa fa-calendar-alt fa-sm"></i>
              23.12.2022
            </span>
                        <a href="#"><h6 class="text-dark">How to promote brands</h6></a>
                        <p>When you enter into any new area of science, you almost reach</p>
                    </div>
                </article>
            </div>
            <!-- col.// -->
            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
                <article>
                    <a href="#" class="img-fluid">
                        <img class="rounded w-100" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/posts/2.webp" style="object-fit: cover;" height="160" />
                    </a>
                    <div class="mt-2 text-muted small d-block mb-1">
            <span>
              <i class="fa fa-calendar-alt fa-sm"></i>
              13.12.2022
            </span>
                        <a href="#"><h6 class="text-dark">How we handle shipping</h6></a>
                        <p>When you enter into any new area of science, you almost reach</p>
                    </div>
                </article>
            </div>
            <!-- col.// -->
            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
                <article>
                    <a href="#" class="img-fluid">
                        <img class="rounded w-100" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/posts/3.webp" style="object-fit: cover;" height="160" />
                    </a>
                    <div class="mt-2 text-muted small d-block mb-1">
            <span>
              <i class="fa fa-calendar-alt fa-sm"></i>
              25.11.2022
            </span>
                        <a href="#"><h6 class="text-dark">How to promote brands</h6></a>
                        <p>When you enter into any new area of science, you almost reach</p>
                    </div>
                </article>
            </div>
            <!-- col.// -->
            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
                <article>
                    <a href="#" class="img-fluid">
                        <img class="rounded w-100" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/posts/4.webp" style="object-fit: cover;" height="160" />
                    </a>
                    <div class="mt-2 text-muted small d-block mb-1">
            <span>
              <i class="fa fa-calendar-alt fa-sm"></i>
              03.09.2022
            </span>
                        <a href="#"><h6 class="text-dark">Success story of sellers</h6></a>
                        <p>When you enter into any new area of science, you almost reach</p>
                    </div>
                </article>
            </div>
        </div>
    </div>
</section>
<!-- Blog -->

<jsp:include page="templates/footer-home.jsp"/>