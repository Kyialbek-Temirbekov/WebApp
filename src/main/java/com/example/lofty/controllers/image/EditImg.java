package com.example.lofty.controllers.image;

import com.example.lofty.dao.ImgDao;
import com.example.lofty.models.Image;
import com.example.lofty.util.Cookies;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-image")
public class EditImg extends HttpServlet {
    private final ImgDao imgDao = ImgDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = Cookies.getValue(request);
        if(userEmail==null)
            response.sendRedirect("login");
        else {
//            Image image = imgDao.show(userEmail);

            RequestDispatcher dis = request.getRequestDispatcher("views/");
            dis.forward(request,response);
        }
    }
}
