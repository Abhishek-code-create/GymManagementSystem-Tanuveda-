/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagesystem.Dao;

import gymmanagesystem.database.Mysqlconnection;
import gymmanagesystem.model.AdminRegistrationModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author ACER
 */


public class Admindao {
    private final Mysqlconnection db = new Mysqlconnection();

    // Registration method
    public boolean registerAdmin(AdminRegistrationModel admin) {

        String sql = "INSERT INTO admins (full_name, email, phone, password, date_of_birth) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, admin.getFullName());
            pst.setString(2, admin.getEmail());
            pst.setString(3, admin.getPhone());
            pst.setString(4, admin.getPassword());

            pst.setDate(5, new Date(admin.getDateOfBirth().getTime()));

            int affected = pst.executeUpdate();
            return affected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login method
    public boolean loginAdmin(String email, String password) {
        String sql = "SELECT * FROM admins WHERE email = ? AND password = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            return rs.next(); // If found, login successful

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Password hashing method (MD5 example, you can use stronger algorithms like SHA-256)
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return password; // Fallback (not recommended)
        }
    }
}
