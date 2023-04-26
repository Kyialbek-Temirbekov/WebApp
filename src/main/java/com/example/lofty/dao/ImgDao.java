package com.example.lofty.dao;

import com.example.lofty.models.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImgDao {
    private static final String URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static ImgDao imgDao = null;
    private ImgDao() {}
    public static ImgDao getInstance() {
        if(imgDao == null)
            imgDao = new ImgDao();
        return imgDao;
    }
    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void save(Image image) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO image(_img_title,_img_caption,_image,_email,_status,_date) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1,image.getTitle());
            preparedStatement.setString(2,image.getCaption());
            preparedStatement.setBlob(3,image.getImage());
            preparedStatement.setString(4,image.getUser_email());
            preparedStatement.setString(5,image.getStatus());
            preparedStatement.setString(6,image.getDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Image> show(String email) {
        Connection connection = getConnection();
        List<Image> images = new ArrayList<>();
        Image image;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM image WHERE _email = ?");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                image = new Image();
                image.setId(resultSet.getInt("_img_id"));
                image.setTitle(resultSet.getString("_img_title"));
                image.setCaption(resultSet.getString("_img_caption"));
                image.setStatus(resultSet.getString("_status"));
                image.setDate(resultSet.getString("_date"));
                image.setRate(resultSet.getInt("_rate"));

                images.add(image);
            }

            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }
        public InputStream getImgById(int id) {
        Connection connection = getConnection();
        InputStream imgData = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT _image FROM image WHERE _img_id = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                imgData = resultSet.getBlob("_image").getBinaryStream();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return imgData;
    }
//    public void delete(String email) {
//        Connection connection = getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "DELETE FROM user WHERE _email = ?");
//            preparedStatement.setString(1,email);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            connection.close();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void updateProfile(User user) {
//        Connection connection = getConnection();
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE user SET _name = ?, _birth_day = ?, _gender = ? WHERE _email = ?");
//            preparedStatement.setString(1,user.getName());
//            preparedStatement.setString(2,user.getBirth_day());
//            preparedStatement.setString(3,user.getGender());
//            preparedStatement.setString(4,user.getEmail());
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            connection.close();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void updateImage(String email, InputStream imgData) {
//        Connection connection = getConnection();
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE user SET _image = ? WHERE _email = ?");
//            preparedStatement.setBlob(1,imgData);
//            preparedStatement.setString(2,email);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            connection.close();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void updateEmail(String userEmail, String email) {
//        Connection connection = getConnection();
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE user SET _email = ? WHERE _email = ?");
//            preparedStatement.setString(1,email);
//            preparedStatement.setString(2,userEmail);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            connection.close();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void updatePassword(String userEmail, int password) {
//        Connection connection = getConnection();
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE user SET _password = ? WHERE _email = ?");
//            preparedStatement.setInt(1,password);
//            preparedStatement.setString(2,userEmail);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            connection.close();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public boolean exist(String email, String password) {
//        Connection connection = getConnection();
//        Integer passwordHash = null;
//        boolean exist = false;
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("SELECT _password FROM user WHERE _email = ?");
//            preparedStatement.setString(1,email);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next())
//                passwordHash = resultSet.getInt("_password");
//            if(passwordHash!=null && passwordHash.equals(password.hashCode()))
//                exist = true;
//            preparedStatement.close();
//            connection.close();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return exist;
//    }
//    public boolean identify(String email) {
//        Connection connection = getConnection();
//        boolean identical = false;
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("SELECT * FROM user WHERE _email = ?");
//            preparedStatement.setString(1,email);
//            ResultSet set = preparedStatement.executeQuery();
//            if(set.next())
//                identical = true;
//            preparedStatement.close();
//            connection.close();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return identical;
//    }
}
