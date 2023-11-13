<%@ page import="com.peach.sellbuy.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 10/20/23
  Time: 12:49 AM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="mt-3">
    <div class="container">
        <main class="card p-3 shadow-2-strong">
            <div class="row">
                <div class="col-lg-3">
                    <nav class="nav flex-column nav-pills mb-md-2">
                        <a class="pager nav-link active py-2 ps-3 my-0" aria-current="page" data-category="Electronics" href="#">Electronics</a>
                        <a class="pager nav-link my-0 py-2 ps-3 bg-white" data-category="Clothes" href="#">Fashion and Clothing</a>
                        <a class="pager nav-link my-0 py-2 ps-3 bg-white" data-category="Home" href="#">Home interiors</a>
                        <a class="pager nav-link my-0 py-2 ps-3 bg-white" data-category="Tech" href="#">Computer and Technology</a>
                        <a class="pager nav-link my-0 py-2 ps-3 bg-white" data-category="Tools" href="#">Tools and equipment</a>
                        <a class="pager nav-link my-0 py-2 ps-3 bg-white" data-category="Sports" href="#">Sports and outdoor</a>
                        <a class="pager nav-link my-0 py-2 ps-3 bg-white" data-category="Animal" href="#">Animal and pets</a>
                        <a class="pager nav-link my-0 py-2 ps-3 bg-white" data-category="Machine" href="#">Machinery Tools</a>
                        <a class="pager nav-link my-0 py-2 ps-3 bg-white" data-category="Other" href="#">Other products</a>
                    </nav>
                </div>
                <div class="col-lg-9">
                    <div class="card-banner h-100 p-5 bg-primary rounded-5" style="height: 350px;">
                        <div>
                            <h2 class="text-white" id="banner-title">
                                Electronics
                            </h2>
                            <p class="text-white" id="banner-description">Dive into the world of cutting-edge electronics and technology, where innovation and functionality combine to enhance your daily life with a wide array of devices and gadgets designed to simplify, entertain, and connect.</p>
                            <a href="<%= Util.webPage("search?searchBar=electronic") %>" class="view-more btn btn-light shadow-0 text-primary"> View more </a>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <!-- container end.// -->
</section>
</header>


<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Get references to the banner elements
        const bannerTitle = document.getElementById("banner-title");
        const bannerDescription = document.getElementById("banner-description");
        const viewMoreLink = document.querySelector(".view-more");


        // Get references to all the navigation links
        const navLinks = document.querySelectorAll(".pager");

        // Function to update the banner content
        function updateBannerContent(category, link) {
            navLinks.forEach((navLink) => {
                navLink.className = "nav-link my-0 py-2 ps-3 bg-white";
                // navLink.classList.remove("active");
            });

            // Add the active class to the clicked link
            // link.classList.add("active");
            link.className = "nav-link active py-2 ps-3 my-0";

            // You can customize this part to change the banner content based on the category.
            if (category === "Electronics") {
                bannerTitle.textContent = "Electronics";
                bannerDescription.textContent = "Dive into the world of cutting-edge electronics and technology, where innovation and functionality combine to enhance your daily life with a wide array of devices and gadgets designed to simplify, entertain, and connect.";
                viewMoreLink.setAttribute("href", "<%= Util.webPage("search?searchBar=electronic") %>");
            } else if (category === "Clothes") {
                bannerTitle.textContent = "Fashion and Clothing";
                bannerDescription.textContent = "Explore a wide range of stylish and comfortable clothing, along with an extensive selection of accessories, to express your unique fashion sense and dress appropriately for any occasion.";
                viewMoreLink.setAttribute("href", "<%= Util.webPage("search?searchBar=cloth") %>");
            } else if (category === "Home") {
                bannerTitle.textContent = "Home Interiors";
                bannerDescription.textContent = "Transform your living spaces into inviting and stylish sanctuaries with interior decor solutions, including furniture, lighting, textiles, and decorative items that reflect your personal taste and create a cozy and welcoming atmosphere.";
                viewMoreLink.setAttribute("href", "<%= Util.webPage("search?searchBar=home") %>");
            } else if (category === "Tech") {
                bannerTitle.textContent = "Computer and Technology";
                bannerDescription.textContent = "Stay on the technological forefront with a vast array of computer hardware, software, and tech accessories, providing the tools you need to work, play, and stay connected in our digital age.";
                viewMoreLink.setAttribute("href", "<%= Util.webPage("search?searchBar=computer") %>");
            } else if (category === "Tools") {
                bannerTitle.textContent = "Tools and Equipment";
                bannerDescription.textContent = "Whether you're a DIY enthusiast or a professional tradesperson, find the right tools and equipment to tackle a variety of projects, from construction to home repairs, with efficiency and precision.";
                viewMoreLink.setAttribute("href", "<%= Util.webPage("search?searchBar=tool") %>");
            } else if (category === "Sports") {
                bannerTitle.textContent = "Sports and Outdoor";
                bannerDescription.textContent = "Fuel your passion for an active lifestyle with high-quality gear and equipment for sports, fitness, and outdoor adventures, helping you embrace the great outdoors and stay physically active.";
                viewMoreLink.setAttribute("href", "<%= Util.webPage("search?searchBar=sport") %>");
            } else if (category === "Animal") {
                bannerTitle.textContent = "Animal and Pets";
                bannerDescription.textContent = "Ensure the well-being of your beloved pets with a comprehensive selection of pet care products, including food, grooming essentials, toys, and more, providing everything your furry companions need for a happy and healthy life.";
                viewMoreLink.setAttribute("href", "<%= Util.webPage("search?searchBar=animal") %>");
            } else if (category === "Machine") {
                bannerTitle.textContent = "Machinery Tools";
                bannerDescription.textContent = "Power up your industrial or construction projects with a wide range of reliable machinery and tools, offering the strength and precision required for heavy-duty tasks and specialized applications.";
                viewMoreLink.setAttribute("href", "<%= Util.webPage("search?searchBar=machine") %>");
            } else if (category === "Other") {
                bannerTitle.textContent = "And So Much More!";
                bannerDescription.textContent = "Explore a diverse range of  products that cater to unique needs and interests, from rare collectibles to niche items that might just be the perfect solution you've been looking for.";
                viewMoreLink.setAttribute("href", "<%= Util.webPage("search?searchBar=a") %>");
            }
            // Add more cases for other categories
        }

        // Add click event listeners to the navigation links
        navLinks.forEach(function (link) {
            link.addEventListener("click", function (event) {
                event.preventDefault(); // Prevent the default link behavior
                const category = link.getAttribute("data-category");
                updateBannerContent(category, link);
            });
        });
    });
</script>