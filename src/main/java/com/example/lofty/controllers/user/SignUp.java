package com.example.lofty.controllers.user;

import com.example.lofty.dao.UserDao;
import com.example.lofty.models.User;
import com.example.lofty.util.Cookies;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/signup")
@MultipartConfig
public class SignUp extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dis = request.getRequestDispatcher("views/join.jsp");
        dis.forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        User user = new User();
        user.setEmail(email);
        user.setPassword(request.getParameter("password").hashCode());
        user.setName(request.getParameter("name"));
        user.setBirth_day(request.getParameter("birth_day"));
        user.setGender(request.getParameter("gender"));
        Part part = request.getPart("image");
        InputStream imgData = part.getInputStream();
        if(part.getSubmittedFileName().equals("")) {
            File initialFile = new File("C:/Users/Stranger/Projects/lofty/src/main/webapp/DefImages/anonymous_3.png");
            imgData = new FileInputStream(initialFile);
            user.setImage(imgData);
        }
        else
            user.setImage(imgData);

        userDao.save(user);
        Cookies.add(email,response);
        response.sendRedirect("home");
    }
}