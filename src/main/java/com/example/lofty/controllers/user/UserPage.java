package com.example.lofty.controllers.user;

import com.example.lofty.dao.ImgDao;
import com.example.lofty.models.Image;
import com.example.lofty.util.Cookies;
import com.example.lofty.dao.UserDao;
import com.example.lofty.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/user-page")
public class UserPage extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();
    private final ImgDao imgDao = ImgDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = Cookies.getValue(request);
        if(userEmail==null)
            response.sendRedirect("login");
        else {
            User user = userDao.show(userEmail);
            request.setAttribute("user",user);
            List<Image> images = imgDao.show(userEmail);
            request.setAttribute("images",images);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user_page.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}