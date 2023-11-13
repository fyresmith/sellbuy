//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.peach.sellbuy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(
        name = "pageFinalServlet",
        value = {"/page-final"}
)
public class PageFinalServlet extends HttpServlet {
    public PageFinalServlet() {
    }

    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int pageNum = (Integer)session.getAttribute("pageTotal");

        session.setAttribute("pageNum", pageNum);

        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    public void destroy() {
    }
}
