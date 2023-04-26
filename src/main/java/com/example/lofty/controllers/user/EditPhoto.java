package com.example.lofty.controllers.user;

import com.example.lofty.util.Cookies;
import com.example.lofty.dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/edit-photo")
@MultipartConfig
public class EditPhoto extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = Cookies.getValue(request);
        if(userEmail==null)
            response.sendRedirect("login");
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/edit_photo.jsp");
            dispatcher.forward(request,response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream imgData = request.getPart("image").getInputStream();
        String email = Cookies.getValue(request);
        userDao.updateImage(email,imgData);

        response.sendRedirect("user-page");
    }
}
