package gymmanagementsystem.model;

import gymmanagementsystem.database.Mysqlconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class AdminRegistrationModel {
    
    private Mysqlconnection dbConnection;
    
    public AdminRegistrationModel() {
        this.dbConnection = new Mysqlconnection();
    }
    
    public boolean validateAllFields(String name, String email, String password, String confirmPassword) {
        return !name.isEmpty() && 
               validateEmail(email) && 
               validatePassword(password) && 
               password.equals(confirmPassword);
    }
    
    private boolean validateEmail(String email) {
        // Basic email validation
        return email != null && 
               email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    private boolean validatePassword(String password) {
        // Password must be at least 8 characters
        return password != null && password.length() >= 8;
    }
    
    public boolean isEmailAlreadyExists(String email) {
        String sql = "SELECT COUNT(*) FROM admin WHERE username = ?";
        
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking email existence: " + e.getMessage());
        }
        
        return false;
    }
    
    public boolean registerAdmin(String fullName, String email, String phone, String adminKeyword, String password, java.util.Date dateOfBirth) {
        try {
            String uniqueCode = generateUniqueCode();
            String sql = "INSERT INTO admin (username, password, admin_keyword, unique_code) VALUES (?, ?, ?, ?)";
            
            try (Connection conn = dbConnection.openConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email); // Using email as username
                ps.setString(2, password);
                ps.setString(3, adminKeyword);
                ps.setString(4, uniqueCode);
                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Generate a unique 4-digit code
    private String generateUniqueCode() {
        Random rand = new Random();
        int code = 1000 + rand.nextInt(9000);
        return String.valueOf(code);
    }
} 