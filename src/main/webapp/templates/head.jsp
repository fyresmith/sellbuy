<%@ page import="com.peach.sellbuy.business.*" %>
<%@ page import="com.peach.sellbuy.util.Util" %><%--
  Created by IntelliJ IDEA.
  User: calebsmith
  Date: 9/28/23
  Time: 6:41 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>SellBuy</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css" />
    <!-- Google Fonts Roboto -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" />
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/lightslider/1.1.6/css/lightslider.min.css'>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0.5/dist/fancybox.css'>
    <link rel="stylesheet" href="../css/style.css" />
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- MDB -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.css"
            rel="stylesheet"
    />
<%--    <link rel="stylesheet" href="css/mdb.min.css" />--%>
<%--    <!-- Custom styles -->--%>
<%--    <link rel="stylesheet" href="css/style.css" />--%>

    <style>
        .icon-hover:hover {
            border-color: #3b71ca !important;
            background-color: white !important;
        }

        .icon-hover:hover i {
            color: #3b71ca !important;
        }

        @media (min-width: 768px) {
            .sign-in-card {
                width: 35rem !important;
            }
        }

        @media (max-width: 768px) {
            .empty-cart-card {
                width: 100% !important;
            }
        }

        @media (min-width: 768px) {
            .empty-cart-card {
                width: 35rem !important;
            }
        }

        @media (max-width: 768px) {
            .sign-in-card {
                width: 100% !important;
            }
        }

        @media (min-width: 768px) {
            .reset-card {
                width: 400px;
            }
        }

        @media (max-width: 768px) {
            .reset-card {
                width: 85% !important;
            }
        }

        /* Custom styling for the dark overlay */
        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1050; /* Adjust the z-index to make sure it's above the modal */
            display: none; /* Hide the overlay initially */
        }
    </style>
</head>