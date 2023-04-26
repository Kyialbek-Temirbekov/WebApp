package com.example.lofty.dao;

import com.example.lofty.models.User;

import java.io.InputStream;
import java.sql.*;

public class UserDao {
    private static final String URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static UserDao userDao = null;
    private UserDao() {}
    public static UserDao getInstance() {
        if(userDao == null)
            userDao = new UserDao();
        return userDao;
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
    public void save(User user) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO user(_email,_password,_name,_birth_day,_gender,_image) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setInt(2,user.getPassword());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setString(4,user.getBirth_day());
            preparedStatement.setString(5,user.getGender());
            preparedStatement.setBlob(6,user.getImage());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User show(String requestEmail) {
        Connection connection = getConnection();
        User user = new User();
        InputStream imgData = null;
        Blob blob;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM user WHERE _email = ?");
            preparedStatement.setString(1,requestEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setEmail(resultSet.getString("_email"));
            user.setPassword(resultSet.getInt("_password"));
            user.setName(resultSet.getString("_name"));
            user.setBirth_day(resultSet.getString("_birth_day"));
            user.setGender(resultSet.getString("_gender"));
            blob = resultSet.getBlob("_image");
            if(blob!=null)
                imgData = blob.getBinaryStream();
            user.setImage(imgData);
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public void delete(String email) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM user WHERE _email = ?");
            preparedStatement.setString(1,email);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateProfile(User user) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE user SET _name = ?, _birth_day = ?, _gender = ? WHERE _email = ?");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getBirth_day());
            preparedStatement.setString(3,user.getGender());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public InputStream getImage(String email) {
        Connection connection = getConnection();
        InputStream imgData = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT _image FROM user WHERE _email = ?");
            preparedStatement.setString(1,email);
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
    public void updateImage(String email, InputStream imgData) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE user SET _image = ? WHERE _email = ?");
            preparedStatement.setBlob(1,imgData);
            preparedStatement.setString(2,email);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateEmail(String userEmail, String email) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE user SET _email = ? WHERE _email = ?");
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,userEmail);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updatePassword(String userEmail, int password) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE user SET _password = ? WHERE _email = ?");
            preparedStatement.setInt(1,password);
            preparedStatement.setString(2,userEmail);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean exist(String email, String password) {
        Connection connection = getConnection();
        Integer passwordHash = null;
        boolean exist = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT _password FROM user WHERE _email = ?");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                passwordHash = resultSet.getInt("_password");
            if(passwordHash!=null && passwordHash.equals(password.hashCode()))
                exist = true;
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }
    public boolean identify(String email) {
        Connection connection = getConnection();
        boolean identical = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM user WHERE _email = ?");
            preparedStatement.setString(1,email);
            ResultSet set = preparedStatement.executeQuery();
            if(set.next())
                identical = true;
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return identical;
    }
}
