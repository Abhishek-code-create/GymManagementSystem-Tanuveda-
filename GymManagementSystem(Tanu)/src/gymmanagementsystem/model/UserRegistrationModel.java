package gymmanagementsystem.model;

import gymmanagementsystem.database.Mysqlconnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Pattern;

public class UserRegistrationModel {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private final Mysqlconnection dbConnection;
    
    public UserRegistrationModel() {
        this.dbConnection = new Mysqlconnection();
    }
    
    public boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }
    
    public boolean validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }
    
    public boolean validatePassword(String password) {
        // Password should be at least 8 characters long
        return password != null && password.length() >= 8;
    }
    
    public boolean validateConfirmPassword(String password, String confirmPassword) {
        return password != null && confirmPassword != null && 
               password.equals(confirmPassword);
    }
    
    public boolean validateAllFields(String fullName, String email, String password, String confirmPassword) {
        return validateName(fullName) && 
               validateEmail(email) && 
               validatePassword(password) && 
               validateConfirmPassword(password, confirmPassword);
    }
    
    public boolean registerUser(String fullName, String email, String phone, String password, java.util.Date dateOfBirth) {
        // Check if email already exists
        if (isEmailAlreadyExists(email)) {
            System.err.println("Email already exists: " + email);
            return false;
        }
        
        String sql = "INSERT INTO user (fullname, email, phone, password, dob, unique_code) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, password);
            
            // Convert java.util.Date to java.sql.Date
            if (dateOfBirth != null) {
                pstmt.setDate(5, new Date(dateOfBirth.getTime()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }
            
            // Generate unique code
            String uniqueCode = generateUniqueCode();
            pstmt.setString(6, uniqueCode);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error registering user: " + e.getMessage());
            return false;
        }
    }
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    
    public boolean isEmailAlreadyExists(String email) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
        
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
    
    // Generate a unique 4-digit code
    private String generateUniqueCode() {
        Random rand = new Random();
        int code = 1000 + rand.nextInt(9000);
        return String.valueOf(code);
    }
} 