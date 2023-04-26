package com.example.lofty.controllers.user;

import com.example.lofty.util.Cookies;
import com.example.lofty.dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class HomePage extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = Cookies.getValue(request);
        if(userEmail==null)
            response.sendRedirect("login");
        else {
            request.setAttribute("email", userEmail);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/main.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
