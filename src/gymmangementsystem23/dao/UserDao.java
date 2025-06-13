package gymmangementsystem23.dao;

/*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public boolean registerUser(String Username, String password,String Email,String confirmpassword) {
        String sql = "INSERT INTO users (Username, password,Email,confirmpassword) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, Username);
            stmt.setString(2, password);
            stmt.setString(3,Email);
            stmt.setString(4,confirmpassword);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateUser(String Username, String password,String  Email,String confirmpassword) {
        String sql = "SELECT * FROM users WHERE Username = ?,password = ?,Email=?,confirmpassword=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, Username);
            stmt.setString(2, password);
            stmt.setString(3, Email);
            stmt.setString(4,confirmpassword);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Registerview(String Username, String password, String Email, String confirmpassword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    } 
}






