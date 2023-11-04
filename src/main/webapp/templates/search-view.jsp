<%@ page import="java.util.LinkedList" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.Product" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/2/23
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    LinkedList<Product> results = (LinkedList<Product>) session.getAttribute("searchResults");

    int pageNum;

    if (session.getAttribute("pageNum") == null) {
        pageNum = 1;

        session.setAttribute("pageNum", pageNum);
    } else {
        pageNum = (int) session.getAttribute("pageNum");
    }

    int totalPages = (int) Math.ceil((double) results.size() / 5);  // Calculate the total number of pages

    if (pageNum < 1) {
        pageNum = 1;
    } else if (pageNum > totalPages) {
        pageNum = totalPages;
    }

    if (results == null) {
        results = new LinkedList<>();
    }

    int startIndex = (pageNum - 1) * 5;  // Calculate the start index for the current page
    int endIndex = Math.min(startIndex + 5, results.size());  // Calculate the end index for the current page

%>

<!-- sidebar + content -->
<section class="">
    <div class="container">
        <div class="row">
            <!-- sidebar -->
            <div class="col-lg-3">
                <!-- Toggle button -->
                <button
                        class="btn btn-outline-secondary mb-3 w-100 d-lg-none"
                        type="button"
                        data-mdb-toggle="collapse"
                        data-mdb-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                >
                    <span>Show filter</span>
                </button>
                <!-- Collapsible wrapper -->
                <div class="collapse card d-lg-block mb-5" id="navbarSupportedContent">
                    <div class="accordion" id="accordionPanelsStayOpenExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingOne">
                                <button
                                        class="accordion-button text-dark bg-light"
                                        type="button"
                                        data-mdb-toggle="collapse"
                                        data-mdb-target="#panelsStayOpen-collapseOne"
                                        aria-expanded="true"
                                        aria-controls="panelsStayOpen-collapseOne"
                                >
                                    Related Items
                                </button>
                            </h2>
                            <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne">
                                <div class="accordion-body">
                                    <ul class="list-unstyled">
                                        <li><a href="#" class="text-dark">Electronics </a></li>
                                        <li><a href="#" class="text-dark">Home items </a></li>
                                        <li><a href="#" class="text-dark">Books, Magazines </a></li>
                                        <li><a href="#" class="text-dark">Men's clothing </a></li>
                                        <li><a href="#" class="text-dark">Interiors items </a></li>
                                        <li><a href="#" class="text-dark">Underwears </a></li>
                                        <li><a href="#" class="text-dark">Shoes for men </a></li>
                                        <li><a href="#" class="text-dark">Accessories </a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingThree">
                                <button
                                        class="accordion-button text-dark bg-light"
                                        type="button"
                                        data-mdb-toggle="collapse"
                                        data-mdb-target="#panelsStayOpen-collapseThree"
                                        aria-expanded="false"
                                        aria-controls="panelsStayOpen-collapseThree"
                                >
                                    Price
                                </button>
                            </h2>
                            <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse show" aria-labelledby="headingThree">
                                <div class="accordion-body">
                                    <div class="range">
                                        <input type="range" class="form-range" id="customRange1" />
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-6">
                                            <p class="mb-0">
                                                Min
                                            </p>
                                            <div class="form-outline">
                                                <input type="number" id="typeNumber" class="form-control" />
                                                <label class="form-label" for="typeNumber">$0</label>
                                            </div>
                                        </div>
                                        <div class="col-6">
                                            <p class="mb-0">
                                                Max
                                            </p>
                                            <div class="form-outline">
                                                <input type="number" id="typeNumber" class="form-control" />
                                                <label class="form-label" for="typeNumber">$1,0000</label>
                                            </div>
                                        </div>
                                    </div>
                                    <button type="button" class="btn btn-white w-100 border border-secondary">apply</button>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingThree">
                                <button
                                        class="accordion-button text-dark bg-light"
                                        type="button"
                                        data-mdb-toggle="collapse"
                                        data-mdb-target="#panelsStayOpen-collapseFive"
                                        aria-expanded="false"
                                        aria-controls="panelsStayOpen-collapseFive"
                                >
                                    Ratings
                                </button>
                            </h2>
                            <div id="panelsStayOpen-collapseFive" class="accordion-collapse collapse show" aria-labelledby="headingThree">
                                <div class="accordion-body">
                                    <!-- Default checkbox -->
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" checked />
                                        <label class="form-check-label" for="flexCheckDefault">
                                            <i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                        </label>
                                    </div>
                                    <!-- Default checkbox -->
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" checked />
                                        <label class="form-check-label" for="flexCheckDefault">
                                            <i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-secondary"></i>
                                        </label>
                                    </div>
                                    <!-- Default checkbox -->
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" checked />
                                        <label class="form-check-label" for="flexCheckDefault">
                                            <i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-secondary"></i>
                                            <i class="fas fa-star text-secondary"></i>
                                        </label>
                                    </div>
                                    <!-- Default checkbox -->
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" checked />
                                        <label class="form-check-label" for="flexCheckDefault">
                                            <i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-secondary"></i><i class="fas fa-star text-secondary"></i>
                                            <i class="fas fa-star text-secondary"></i>
                                        </label>
                                    </div>
                                    <br />
                                    <button type="button" class="btn btn-white w-100 border border-secondary">apply</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- sidebar -->
            <!-- content -->
            <div class="col-lg-9">
                <header class="d-sm-flex align-items-center border-bottom mb-4 pb-3">
                    <strong class="d-block py-2"><%= results.size() %> Items found </strong>
                    <div class="ms-auto d-inline-flex w-auto">
                        <strong class="d-block py-1">Sort By:</strong>
                        <form action="<%= Util.webRoot("sort-result-servlet") %>" class="mx-3 mb-0">
                            <button class="btn btn-white" type="submit">
                                Best Match
                                <input type="hidden" name="option" value="best">
                            </button>
                        </form>

                        <form action="<%= Util.webRoot("sort-result-servlet") %>" class="mx-3 mb-0">
                            <button class="btn btn-white" type="submit">
                                Highest Rating
                                <input type="hidden" name="option" value="rated">
                            </button>
                        </form>

                        <form action="<%= Util.webRoot("sort-result-servlet") %>" class="mx-3 mb-0">
                            <button class="btn btn-white" type="submit">
                                Random
                                <input type="hidden" name="option" value="random">
                            </button>
                        </form>
                    </div>

                </header>

                <% for (int i = startIndex; i < endIndex; i++) { %>
                    <%= Product.searchCard(results.get(i)) %>
                <% } %>

                <hr />

                <!-- Pagination -->
                <nav aria-label="Page navigation example" class="d-flex justify-content-center mt-3">
                    <ul class="pagination">
                        <% if (pageNum <= 1) { %>
                            <form class="page-item disabled" action="<%= Util.webRoot("page-previous-servlet") %>">
                        <% } else { %>
                            <form class="page-item" action="<%= Util.webRoot("page-previous-servlet") %>">
                        <% } %>
                            <button class="page-link" aria-label="Previous">
                                <span aria-hidden="true">&laquo; Previous</span>
                            </button>
                        </form>
<%--                        <li class="page-item active"><a class="page-link" href="#">1</a></li>--%>
                        <li class="page-item"><span class="page-link disabled fw-bold text-dark">Page <%= pageNum %></span></li>
<%--                        <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
<%--                        <li class="page-item"><a class="page-link" href="#">4</a></li>--%>
<%--                        <li class="page-item"><a class="page-link" href="#">5</a></li>--%>
<%--                        <li class="page-item">--%>
                            <% if (pageNum >= totalPages) { %>
                                <form class="page-item disabled" action="<%= Util.webRoot("page-next-servlet") %>">
                            <% } else { %>
                                <form class="page-item" action="<%= Util.webRoot("page-next-servlet") %>">
                            <% } %>
                            <button class="page-link" aria-label="Next">
                                <span aria-hidden="true">Next &raquo;</span>
                            </button>
                        </form>
                    </ul>
                </nav>
                <!-- Pagination -->
            </div>
        </div>
    </div>
</section>