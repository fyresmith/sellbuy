package com.peach.sellbuy;

// Import required java libraries
import java.io.*;

import com.peach.sellbuy.util.Data;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.*;

@WebServlet(name = "refresh", value = "/refresh")
public class Refresh extends HttpServlet {

    // Method to handle GET method request.
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set refresh, autoload time as 5 seconds
        response.setIntHeader("Refresh", 5);

        // Set response content type
        response.setContentType("text/html");

        // Get current time
        Calendar calendar = new GregorianCalendar();
        String am_pm;
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        if(calendar.get(Calendar.AM_PM) == 0)
            am_pm = "AM";
        else
            am_pm = "PM";

        String CT = hour+":"+ minute +":"+ second +" "+ am_pm;

        PrintWriter out = response.getWriter();
        String title = "Auto Page Refresh using Servlet";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        // Get the ServletContext
        ServletContext context = getServletContext();

        // Get the real path of the root folder
        String rootPath = context.getRealPath("/");

        response.getWriter().println("Root folder path: " + rootPath);

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n"+
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<p>Current Time is: " + CT + "</p>\n" +
                "" + Data.TEST
        );
    }

    // Method to handle POST method request.
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}