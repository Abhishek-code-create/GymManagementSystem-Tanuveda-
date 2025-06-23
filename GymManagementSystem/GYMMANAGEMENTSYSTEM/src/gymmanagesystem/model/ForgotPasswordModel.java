package gymmanagesystem.model;

import java.util.regex.Pattern;

public class ForgotPasswordModel {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    
    public boolean validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }
    
    public boolean validatePassword(String password, String confirmPassword) {
        if (password == null || confirmPassword == null || 
            password.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
            return false;
        }
        return password.equals(confirmPassword);
    }
    
    public boolean validateFields(String username, String email, String password, String confirmPassword) {
        return username != null && !username.trim().isEmpty() &&
               validateEmail(email) &&
               validatePassword(password, confirmPassword);
    }
    
    public boolean updatePassword(String username, String email, String newPassword) {
        // TODO: Implement actual database update logic
        // For now, we'll just return true to simulate a successful update
        return true;
    }
} 