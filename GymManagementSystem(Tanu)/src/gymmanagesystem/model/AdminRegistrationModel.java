package gymmanagesystem.model;

public class AdminRegistrationModel {
    
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
} 