package com.example.lofty.controllers.user;

import com.example.lofty.dao.UserDao;
import com.example.lofty.util.Cookies;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dis = request.getRequestDispatcher("views/login.jsp");
        dis.forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(userDao.exist(email,password)) {
            Cookies.add(email,response);
            response.sendRedirect("home");
        }
        else
            request.getRequestDispatcher("/views/login.jsp").forward(request,response);
    }
}
