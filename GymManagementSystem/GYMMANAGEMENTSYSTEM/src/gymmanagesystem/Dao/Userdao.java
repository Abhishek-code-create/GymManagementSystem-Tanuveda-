/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagesystem.Dao;
import gymmanagesystem.database.Mysqlconnection;
import gymmanagesystem.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author ACER
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class Userdao {
    private final Mysqlconnection db;

    public Userdao() {
        this.db = new Mysqlconnection();
    }

    public boolean isEmailAlreadyExists(String email) {
        String sql = "SELECT email FROM users WHERE email = ?";
        try (Connection conn = db.openConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateAllFields(String fullName, String email, String password, String confirmPassword) {
        if (fullName == null || fullName.trim().isEmpty()) return false;

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null || !java.util.regex.Pattern.matches(emailRegex, email)) return false;

        if (password == null || password.length() < 8) return false;

        if (!password.equals(confirmPassword)) return false;

        return true;
    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (fullName, email, phone, password, dateOfBirth) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, user.getFullName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPhone());
            pst.setString(4, user.getPassword()); // Consider hashing
            pst.setDate(5, new Date(user.getDateOfBirth().getTime()));

            int affected = pst.executeUpdate();
            return affected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ LOGIN method
    public boolean login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = db.openConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, email);
            pst.setString(2, password); // Use hashing in production!
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean isEmailExists(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT fullName, email, phone, password, dateOfBirth FROM users WHERE email = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String userEmail = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                java.util.Date dateOfBirth = rs.getDate("dateOfBirth");
                return new gymmanagesystem.model.User(fullName, userEmail, phone, password, dateOfBirth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
