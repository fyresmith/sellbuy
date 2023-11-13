package com.peach.sellbuy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.peach.sellbuy.util.Data;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the requested image name from the URL
        String imageName = request.getPathInfo().substring(1); // remove the leading '/'

        // Specify the directory where images are stored
        String imageDirectory = Data.IMAGE;

        // Construct the complete path to the image file
        File imageFile = new File(imageDirectory, imageName);

        // Set the content type based on the image file type (e.g., "image/jpeg")
        response.setContentType(getServletContext().getMimeType(imageName));

        // Copy image data to the response output stream
        try (FileInputStream in = new FileInputStream(imageFile);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}