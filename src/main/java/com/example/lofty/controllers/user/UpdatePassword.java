package com.example.lofty.controllers.user;

import com.example.lofty.util.Cookies;
import com.example.lofty.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/update-password")
public class UpdatePassword extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPassword = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String email = Cookies.getValue(request);
        if(userDao.exist(email, currentPassword))
            userDao.updatePassword(email,newPassword.hashCode());
        response.sendRedirect("user-page");
    }
}
