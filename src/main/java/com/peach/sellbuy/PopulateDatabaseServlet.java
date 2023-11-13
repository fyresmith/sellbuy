package com.peach.sellbuy;

// PopulateDatabaseServlet.java


import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.peach.sellbuy.util.Seeder.populateDatabase;

@WebServlet("/PopulateDatabaseServlet")
public class PopulateDatabaseServlet extends HttpServlet {
    public static void test(HttpServletRequest request) {
        int progress = 0;
        HttpSession session = request.getSession();

        for (int i = 0; i < 100; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            progress += 1;

            session.setAttribute("progress", progress);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the content type of the response
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Start asynchronous processing
        AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(0); // Infinite timeout

        // Create a new thread to run the populateDatabase method
        Thread populateThread = new Thread(() -> {
            try {
                int totalItems = 100; // Adjust as needed
                for (int i = 0; i < totalItems; i++) {
                    // Simulate processing
                    Thread.sleep(1000); // Simulate work (adjust as needed)

                    // Update progress
                    int progress = (i + 1) * 100 / totalItems;

                    // Send progress update to the client
                    response.getWriter().write(String.valueOf(progress));
                    response.getWriter().flush();
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                // Complete the asynchronous context when the thread is done
                asyncContext.complete();
            }
        });

        // Set up a listener for completion of asynchronous processing
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                // Database population is complete, send a completion message
                response.getWriter().write("DONE");
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                // Handle timeout if needed
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                // Handle errors if needed
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                // Not needed for this example
            }
        });

        // Start the thread
        populateThread.start();
    }
}
