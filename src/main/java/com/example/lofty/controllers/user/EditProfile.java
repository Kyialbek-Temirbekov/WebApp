package com.example.lofty.controllers.user;

import com.example.lofty.util.Cookies;
import com.example.lofty.dao.UserDao;
import com.example.lofty.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/edit-profile")
@MultipartConfig
public class EditProfile extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = Cookies.getValue(request);
        if(userEmail==null)
            response.sendRedirect("login");
        else {
            User user = userDao.show(userEmail);
            request.setAttribute("user",user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/edit_profile.jsp");
            dispatcher.forward(request,response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setEmail(Cookies.getValue(request));
        user.setName(request.getParameter("name"));
        user.setBirth_day(request.getParameter("birth_day"));
        user.setGender(request.getParameter("gender"));

        userDao.updateProfile(user);
        response.sendRedirect("user-page");
    }
}
