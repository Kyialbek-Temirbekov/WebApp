package com.example.lofty.controllers.image;

import com.example.lofty.dao.ImgDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

@WebServlet("/img-by-id")
public class GetImgById extends HttpServlet {
    private final ImgDao imgDao = ImgDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        InputStream imgData = imgDao.getImgById(id);
        String mimeType = URLConnection.guessContentTypeFromStream(imgData);
        byte[] imageBytes = imgData.readAllBytes();
        response.setContentType(mimeType);
        response.getOutputStream().write(imageBytes);
        response.getOutputStream().close();
    }
}
