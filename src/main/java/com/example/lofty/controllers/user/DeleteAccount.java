package com.example.lofty.controllers.user;

import com.example.lofty.dao.UserDao;
import com.example.lofty.util.Cookies;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-account")
public class DeleteAccount extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = Cookies.getValue(request);
        String password = request.getParameter("password");
        if(userDao.exist(email,password)) {
            userDao.delete(email);
            Cookies.removeCookie(request,response);
            response.sendRedirect("signup");
        }
        else
            response.sendRedirect("edit-account");
    }
}
