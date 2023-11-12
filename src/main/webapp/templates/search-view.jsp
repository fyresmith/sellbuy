<%@ page import="java.util.LinkedList" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.Product" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Util" %>
<%@ page import="com.peach.sellbuy_ecommerce.business.Access" %>
<%@ page import="com.peach.sellbuy_ecommerce.util.Pager" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/2/23
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    LinkedList<Product> results = (LinkedList<Product>) session.getAttribute("searchResults");

    Pager<Product> pager = new Pager<>(results, 5);

    int pageNum;

    if (session.getAttribute("pageNum") == null) {
        pageNum = 1;

        session.setAttribute("pageNum", pageNum);
    } else {
        pageNum = (int) session.getAttribute("pageNum");
    }

    session.setAttribute("pageTotal", pager.getNumberOfPages());

//    int totalPages = (int) Math.ceil((double) results.size() / 5);  // Calculate the total number of pages
//
//    if (pageNum < 1) {
//        pageNum = 1;
//    } else if (pageNum > totalPages) {
//        pageNum = totalPages;
//    }
//
//    if (results == null) {
//        results = new LinkedList<>();
//    }
//
//    int startIndex = (pageNum - 1) * 5;  // Calculate the start index for the current page
//    int endIndex = Math.min(startIndex + 5, results.size());  // Calculate the end index for the current page

    Access<Product> access = new Access<>("product", "productID", Product.class);
    LinkedList<String> categories = Util.styleCategories(access.getProductCategories());

    int maxCats = Math.min(categories.size(), 10);

    String minString = (String) session.getAttribute("minString");
    String maxString = (String) session.getAttribute("maxString");

    minString = (minString == null) ? "0" : minString;

    maxString = (maxString == null) ? "100000" : maxString;
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
                                    Popular Categories
                                </button>
                            </h2>
                            <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne">
                                <div class="accordion-body">
                                    <ul class="list-unstyled">
                                        <% for (int i = 0; i < maxCats; i++) { %>
                                            <li><a href="<%= Util.webRoot("search?searchBar=" + Util.rawCategory(categories.get(i))) %>" class="text-dark"><%= categories.get(i) %></a></li>
                                        <% } %>
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
                                <form class="accordion-body" action="<%= Util.webRoot("sort-min-max") %>">
<%--                                    <div class="range">--%>
<%--                                        <input type="range" class="form-range" id="customRange1" />--%>
<%--                                    </div>--%>
                                    <div class="row mb-3">
                                        <div class="col-6">
                                            <p class="mb-0">
                                                Min
                                            </p>
                                            <div class="form-outline">
                                                <input type="number" placeholder="<%= minString %>" name="min" id="min" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-6">
                                            <p class="mb-0">
                                                Max
                                            </p>
                                            <div class="form-outline">
                                                <input type="number" placeholder="<%= maxString %>" name="max" id="max" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-white w-100 border border-secondary">apply</button>
                                </form>
                            </div>
                        </div>
<%--                        <form class="accordion-item" action="<%= Util.webRoot("sort-by-rating") %>">--%>
<%--                            <h2 class="accordion-header" id="headingFour">--%>
<%--                                <button--%>
<%--                                        class="accordion-button text-dark bg-light"--%>
<%--                                        type="button"--%>
<%--                                        data-mdb-toggle="collapse"--%>
<%--                                        data-mdb-target="#panelsStayOpen-collapseFive"--%>
<%--                                        aria-expanded="false"--%>
<%--                                        aria-controls="panelsStayOpen-collapseFive"--%>
<%--                                >--%>
<%--                                    Ratings--%>
<%--                                </button>--%>
<%--                            </h2>--%>
<%--                            <div id="panelsStayOpen-collapseFive" class="accordion-collapse collapse show" aria-labelledby="headingThree">--%>
<%--                                <div class="accordion-body">--%>
<%--                                    <!-- Default checkbox -->--%>
<%--                                    <div class="form-check">--%>
<%--                                        <input class="form-check-input" name="ratings" type="checkbox" value="5" id="flexCheckDefault1" checked />--%>
<%--                                        <label class="form-check-label" for="flexCheckDefault1">--%>
<%--                                            <i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                    <!-- Default checkbox -->--%>
<%--                                    <div class="form-check">--%>
<%--                                        <input class="form-check-input" name="ratings" type="checkbox" value="4" id="flexCheckDefault2" checked />--%>
<%--                                        <label class="form-check-label" for="flexCheckDefault2">--%>
<%--                                            <i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-secondary"></i>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                    <!-- Default checkbox -->--%>
<%--                                    <div class="form-check">--%>
<%--                                        <input class="form-check-input" name="ratings" type="checkbox" value="3" id="flexCheckDefault3" checked />--%>
<%--                                        <label class="form-check-label" for="flexCheckDefault3">--%>
<%--                                            <i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-secondary"></i><i class="fas fa-star text-secondary"></i>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                    <!-- Default checkbox -->--%>
<%--                                    <div class="form-check">--%>
<%--                                        <input class="form-check-input" name="ratings" type="checkbox" value="2" id="flexCheckDefault4" checked />--%>
<%--                                        <label class="form-check-label" for="flexCheckDefault4">--%>
<%--                                            <i class="fas fa-star text-warning"></i><i class="fas fa-star text-warning"></i><i class="fas fa-star text-secondary"></i><i class="fas fa-star text-secondary"></i><i class="fas fa-star text-secondary"></i>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                    <!-- Default checkbox -->--%>
<%--                                    <div class="form-check">--%>
<%--                                        <input class="form-check-input" name="ratings" type="checkbox" value="1" id="flexCheckDefault5" checked />--%>
<%--                                        <label class="form-check-label" for="flexCheckDefault5">--%>
<%--                                            <i class="fas fa-star text-warning"></i><i class="fas fa-star text-secondary"></i><i class="fas fa-star text-secondary"></i><i class="fas fa-star text-secondary"></i><i class="fas fa-star text-secondary"></i>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                    <br />--%>
<%--                                    <button type="submit" class="btn btn-white w-100 border border-secondary">apply</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </form>--%>
                    </div>
                </div>
            </div>
            <!-- sidebar -->
            <!-- content -->
            <div class="col-lg-9">
                <header class="d-sm-flex align-items-center border-bottom mb-4 pb-3">
                    <strong class="d-block py-2"><%= results.size() %> Items found </strong>
                    <div class="ms-auto d-inline-flex w-auto">
                        <form id="sortSelect" action="<%= Util.webRoot("sort-result") %>">
                            <select id="formSelect" name="option" class="form-select d-inline-block w-auto border pt-1">
                                <option value="best">Best match</option>
                                <option value="rated">High rated</option>
                                <option value="highest">Highest Price</option>
                                <option value="lowest">Lowest Price</option>
                                <option value="random">Randomly</option>
                            </select>
                        </form>
                    </div>

                </header>

                <% if (results.isEmpty()) { %>
                    <div class="card shadow-0 border rounded-3">
                        <div class="card-body">
                            <div class="text-center">
                                <strong>No results found!</strong>
                            </div>
                        </div>
                    </div>
                <% } else { %>
                    <% for (Product product : pager.getPage(pageNum)) { %>
                        <%= Product.searchCard(product) %>
                    <% } %>
                <% } %>

                <hr />

                <!-- Pagination -->
                <nav aria-label="Page navigation example" class="d-flex justify-content-center mt-3">
                    <ul class="pagination">
                        <% if (pageNum <= 1) { %>
                            <form class="page-item disabled" action="<%= Util.webRoot("page-previous") %>">
                        <% } else { %>
                            <form class="page-item" action="<%= Util.webRoot("page-previous") %>">
                        <% } %>
                            <button class="page-link" aria-label="Previous">
                                <span aria-hidden="true">&laquo; Previous</span>
                            </button>
                        </form>
<%--                        <li class="page-item active"><a class="page-link" href="#">1</a></li>--%>
                        <li class="page-item"><span class="page-link disabled fw-bold text-dark">Page <%= pageNum %> of <%= pager.getNumberOfPages() %></span></li>
<%--                        <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
<%--                        <li class="page-item"><a class="page-link" href="#">4</a></li>--%>
<%--                        <li class="page-item"><a class="page-link" href="#">5</a></li>--%>
<%--                        <li class="page-item">--%>
                            <% if (pageNum >= pager.getNumberOfPages()) { %>
                                <form class="page-item disabled" action="<%= Util.webRoot("page-next") %>">
                            <% } else { %>
                                <form class="page-item" action="<%= Util.webRoot("page-next") %>">
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

<script>
    // Listen for changes in the select field
    document.getElementById('formSelect').addEventListener('change', function () {
        // Submit the form when an option is selected
        document.getElementById('sortSelect').submit();
    });
</script>