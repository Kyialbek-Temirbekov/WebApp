package com.example.lofty.controllers.user;

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

@WebServlet("/edit-account")
public class EditAccount extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = Cookies.getValue(request);
        if(userEmail==null)
            response.sendRedirect("login");
        else  {
            User user = userDao.show(userEmail);
            request.setAttribute("user",user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/edit_account.jsp");
            dispatcher.forward(request,response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = Cookies.getValue(request);
        String email = request.getParameter("email");
        userDao.updateEmail(userEmail,email);
        Cookies.add(email,response);

        response.sendRedirect("user-page");
    }
}
