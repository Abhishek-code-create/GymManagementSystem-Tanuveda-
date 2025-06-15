package gymmanagesystem.model;

public class LoginSystemModel {
    // Dummy authentication logic
    public boolean authenticate(String email, String password) {
        // Replace with real authentication logic (e.g., database check)
        return "user@example.com".equals(email) && "password123".equals(password);
    }
} 