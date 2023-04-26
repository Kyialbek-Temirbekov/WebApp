package com.example.lofty.controllers.user;

import com.example.lofty.util.Cookies;
import com.example.lofty.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

@WebServlet("/show-img")
public class ShowImg extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = Cookies.getValue(request);

        InputStream imgData = userDao.getImage(email);
        String mimeType = URLConnection.guessContentTypeFromStream(imgData);
        byte[] imageBytes = imgData.readAllBytes();
        System.out.println(imageBytes);
        response.setContentType(mimeType);
        System.out.println(mimeType);
        response.getOutputStream().write(imageBytes);
        response.getOutputStream().close();
    }
}