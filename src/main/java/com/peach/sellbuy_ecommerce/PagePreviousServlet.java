package com.peach.sellbuy_ecommerce;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "pagePreviousServlet", value = "/page-previous-servlet")
public class PagePreviousServlet extends HttpServlet {

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        int pageNum = (int) session.getAttribute("pageNum");

        if (pageNum > 0) {
            pageNum -= 1;
        }

        session.setAttribute("pageNum", pageNum);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("search.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}