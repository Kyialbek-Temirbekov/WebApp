package com.example.lofty.controllers.image;

import com.example.lofty.dao.ImgDao;
import com.example.lofty.models.Image;
import com.example.lofty.util.Cookies;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/post-img")
@MultipartConfig
public class PostImg extends HttpServlet {
    private final ImgDao imgDao = ImgDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = Cookies.getValue(request);
        if(userEmail==null)
            response.sendRedirect("login");
        else {
            RequestDispatcher dis = request.getRequestDispatcher("views/post_img.jsp");
            dis.forward(request,response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Image image = new Image();
        image.setTitle(request.getParameter("title"));
        image.setCaption(request.getParameter("caption"));
        image.setImage(request.getPart("image").getInputStream());
        image.setUser_email(Cookies.getValue(request));
        image.setStatus(request.getParameter("access"));
        image.setDate(LocalDate.now().toString());

        imgDao.save(image);
        response.sendRedirect("user-page");
    }
}
