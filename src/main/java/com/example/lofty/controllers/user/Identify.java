package com.example.lofty.controllers.user;

import com.example.lofty.dao.UserDao;
import com.example.lofty.util.Cookies;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/identify")
public class Identify extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = Cookies.getValue(request);
        if(email!=null && userDao.identify(email))
            response.sendRedirect("home");
        else
            response.sendRedirect("signup");
    }
}
