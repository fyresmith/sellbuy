<%@ page import="com.peach.sellbuy.business.Access" %>
<%@ page import="com.peach.sellbuy.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 11/13/23
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="templates/head.jsp"/>

<!-- Font Awesome -->
<link
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
        rel="stylesheet"
/>
<!-- Google Fonts -->
<link
        href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
        rel="stylesheet"
/>
<!-- MDB -->
<link
        href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.css"
        rel="stylesheet"
/>

<head>
  <title>Test Database Generator</title>
</head>

<body style="background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAMAAAAp4XiDAAAAUVBMVEWFhYWDg4N3d3dtbW17e3t1dXWBgYGHh4d5eXlzc3OLi4ubm5uVlZWPj4+NjY19fX2JiYl/f39ra2uRkZGZmZlpaWmXl5dvb29xcXGTk5NnZ2c8TV1mAAAAG3RSTlNAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEAvEOwtAAAFVklEQVR4XpWWB67c2BUFb3g557T/hRo9/WUMZHlgr4Bg8Z4qQgQJlHI4A8SzFVrapvmTF9O7dmYRFZ60YiBhJRCgh1FYhiLAmdvX0CzTOpNE77ME0Zty/nWWzchDtiqrmQDeuv3powQ5ta2eN0FY0InkqDD73lT9c9lEzwUNqgFHs9VQce3TVClFCQrSTfOiYkVJQBmpbq2L6iZavPnAPcoU0dSw0SUTqz/GtrGuXfbyyBniKykOWQWGqwwMA7QiYAxi+IlPdqo+hYHnUt5ZPfnsHJyNiDtnpJyayNBkF6cWoYGAMY92U2hXHF/C1M8uP/ZtYdiuj26UdAdQQSXQErwSOMzt/XWRWAz5GuSBIkwG1H3FabJ2OsUOUhGC6tK4EMtJO0ttC6IBD3kM0ve0tJwMdSfjZo+EEISaeTr9P3wYrGjXqyC1krcKdhMpxEnt5JetoulscpyzhXN5FRpuPHvbeQaKxFAEB6EN+cYN6xD7RYGpXpNndMmZgM5Dcs3YSNFDHUo2LGfZuukSWyUYirJAdYbF3MfqEKmjM+I2EfhA94iG3L7uKrR+GdWD73ydlIB+6hgref1QTlmgmbM3/LeX5GI1Ux1RWpgxpLuZ2+I+IjzZ8wqE4nilvQdkUdfhzI5QDWy+kw5Wgg2pGpeEVeCCA7b85BO3F9DzxB3cdqvBzWcmzbyMiqhzuYqtHRVG2y4x+KOlnyqla8AoWWpuBoYRxzXrfKuILl6SfiWCbjxoZJUaCBj1CjH7GIaDbc9kqBY3W/Rgjda1iqQcOJu2WW+76pZC9QG7M00dffe9hNnseupFL53r8F7YHSwJWUKP2q+k7RdsxyOB11n0xtOvnW4irMMFNV4H0uqwS5ExsmP9AxbDTc9JwgneAT5vTiUSm1E7BSflSt3bfa1tv8Di3R8n3Af7MNWzs49hmauE2wP+ttrq+AsWpFG2awvsuOqbipWHgtuvuaAE+A1Z/7gC9hesnr+7wqCwG8c5yAg3AL1fm8T9AZtp/bbJGwl1pNrE7RuOX7PeMRUERVaPpEs+yqeoSmuOlokqw49pgomjLeh7icHNlG19yjs6XXOMedYm5xH2YxpV2tc0Ro2jJfxC50ApuxGob7lMsxfTbeUv07TyYxpeLucEH1gNd4IKH2LAg5TdVhlCafZvpskfncCfx8pOhJzd76bJWeYFnFciwcYfubRc12Ip/ppIhA1/mSZ/RxjFDrJC5xifFjJpY2Xl5zXdguFqYyTR1zSp1Y9p+tktDYYSNflcxI0iyO4TPBdlRcpeqjK/piF5bklq77VSEaA+z8qmJTFzIWiitbnzR794USKBUaT0NTEsVjZqLaFVqJoPN9ODG70IPbfBHKK+/q/AWR0tJzYHRULOa4MP+W/HfGadZUbfw177G7j/OGbIs8TahLyynl4X4RinF793Oz+BU0saXtUHrVBFT/DnA3ctNPoGbs4hRIjTok8i+algT1lTHi4SxFvONKNrgQFAq2/gFnWMXgwffgYMJpiKYkmW3tTg3ZQ9Jq+f8XN+A5eeUKHWvJWJ2sgJ1Sop+wwhqFVijqWaJhwtD8MNlSBeWNNWTa5Z5kPZw5+LbVT99wqTdx29lMUH4OIG/D86ruKEauBjvH5xy6um/Sfj7ei6UUVk4AIl3MyD4MSSTOFgSwsH/QJWaQ5as7ZcmgBZkzjjU1UrQ74ci1gWBCSGHtuV1H2mhSnO3Wp/3fEV5a+4wz//6qy8JxjZsmxxy5+4w9CDNJY09T072iKG0EnOS0arEYgXqYnXcYHwjTtUNAcMelOd4xpkoqiTYICWFq0JSiPfPDQdnt+4/wuqcXY47QILbgAAAABJRU5ErkJggg==);
             background-color: #203a69;">
<div class="container mt-5 d-flex justify-content-center">
  <div class="card border" style="width: 40rem">
    <div class="card-body d-flex flex-column align-items-center">
      <h3 class="card-title text-center fw-bold">Generate Test Database</h3>
      <p>Enter the number of users and products to generate.</p>

      <div class="container mt-2">
        <h5 class="text-center text-decoration-underline">Recommended Settings</h5>

        <ul class="list-group list-group-light">
          <li class="list-group-item d-flex justify-content-between align-items-center">
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Products:</div>
              <span class="text-muted">100</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Users:</div>
              <span class="text-muted">100</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Reviews:</div>
              <span class="text-muted">500-8K</span>
            </div>
            <span class="badge rounded-pill badge-dark">Tiny</span>
          </li>

          <li class="list-group-item d-flex justify-content-between align-items-center">
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Products:</div>
              <span class="text-muted">2000</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Users:</div>
              <span class="text-muted">500</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Reviews:</div>
              <span class="text-muted">10K-160K</span>
            </div>
            <span class="badge rounded-pill badge-success">Light</span>
          </li>

          <li class="list-group-item d-flex justify-content-between align-items-center">
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Products:</div>
              <span class="text-muted">5000</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Users:</div>
              <span class="text-muted">1000</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Reviews:</div>
              <span class="text-muted">25K-400K</span>
            </div>
            <span class="badge rounded-pill badge-success">Small</span>
          </li>

          <li class="list-group-item d-flex justify-content-between align-items-center">
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Products:</div>
              <span class="text-muted">8000</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Users:</div>
              <span class="text-muted">2000</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Reviews:</div>
              <span class="text-muted">40K-640K</span>
            </div>
            <span class="badge rounded-pill badge-warning">Medium</span>
          </li>

          <li class="list-group-item d-flex justify-content-between align-items-center">
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Products:</div>
              <span class="text-muted">10000</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Users:</div>
              <span class="text-muted">4000</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Reviews:</div>
              <span class="text-muted">50K-800K</span>
            </div>
            <span class="badge rounded-pill badge-warning">Large</span>
          </li>

          <li class="list-group-item d-flex justify-content-between align-items-center">
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Products:</div>
              <span class="text-muted">15000</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Users:</div>
              <span class="text-muted">8000</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Reviews:</div>
              <span class="text-muted">75K-1.2M</span>
            </div>
            <span class="badge rounded-pill badge-danger">Massive</span>
          </li>

          <li class="list-group-item d-flex justify-content-between align-items-center">
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Products:</div>
              <span class="text-muted">20000</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Users:</div>
              <span class="text-muted">12000</span>
            </div>
            <div class="d-flex align-items-center">
              <div class="fw-bold me-2">Reviews:</div>
              <span class="text-muted">100K-1.6M</span>
            </div>
            <span class="badge rounded-pill badge-danger">Excessive</span>
          </li>

        </ul>
      </div>

      <form action="<%= Util.webPage("init-loader") %>">
        <div class="row my-4">
          <div class="col-md-6">
            <div class="form-outline">
              <input type="number" name="Users" id="Users" class="form-control" value="100">
              <label class="form-label" for="Users">Number of Users</label>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-outline">
              <input type="number" name="Products" id="Products" class="form-control" value="100">
              <label class="form-label" for="Products">Number of Products</label>
            </div>
          </div>
        </div>

        <div class="container d-flex justify-content-center">
          <button type="submit" onclick="disableButton()" class="btn btn-primary">Generate Database</button>
        </div>
      </form>
    </div>
  </div>
</div>

</body>

</html>

<!-- Add this script section at the end of your HTML file, after the MDB and jQuery script tags -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.js'></script>

<script src='https://cdnjs.cloudflare.com/ajax/libs/lightslider/1.1.6/js/lightslider.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0.5/dist/fancybox.umd.js'></script>
<script>
  function disableButton() {
    var button = document.getElementById('myButton');
    button.disabled = true;
  }
</script>