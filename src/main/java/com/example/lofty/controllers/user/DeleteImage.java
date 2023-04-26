package com.example.lofty.controllers.user;

import com.example.lofty.util.Cookies;
import com.example.lofty.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/delete-photo")
@MultipartConfig
public class DeleteImage extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = Cookies.getValue(request);
        File initialFile = new File("C:/Users/Stranger/Projects/lofty/src/main/webapp/DefImages/anonymous_3.png");
        InputStream imgData = new FileInputStream(initialFile);
        userDao.updateImage(email,imgData);
        response.sendRedirect("user-page");
    }

}
