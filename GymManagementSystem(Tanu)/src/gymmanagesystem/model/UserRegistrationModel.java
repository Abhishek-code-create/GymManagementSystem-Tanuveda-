package gymmanagesystem.model;

import java.util.regex.Pattern;

public class UserRegistrationModel {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    
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
    
    public boolean validateAllFields(String name, String email, String password, String confirmPassword) {
        return validateName(name) &&
               validateEmail(email) &&
               validatePassword(password) &&
               validateConfirmPassword(password, confirmPassword);
    }
} 