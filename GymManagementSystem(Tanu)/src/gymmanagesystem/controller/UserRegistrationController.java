package gymmanagesystem.controller;

import gymmanagesystem.model.UserRegistrationModel;
import gymmanagesystem.view.UserRegisterView;
import gymmanagesystem.view.LoginSystemView;
import javax.swing.JOptionPane;

public class UserRegistrationController {
    private final UserRegisterView view;
    private final UserRegistrationModel model;
    
    public UserRegistrationController(UserRegisterView view) {
        this.view = view;
        this.model = new UserRegistrationModel();
    }
    
    public void handleSaveButton(String name, String email, String password, String confirmPassword) {
        // Check for empty fields first
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "All fields are required. Please fill in all the information.",
                "Missing Information",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!model.validateAllFields(name, email, password, confirmPassword)) {
            JOptionPane.showMessageDialog(view, 
                "Please check all fields:\n" +
                "- Name should not be empty\n" +
                "- Email should be valid\n" +
                "- Password should be at least 8 characters\n" +
                "- Passwords should match",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Show success message
        JOptionPane.showMessageDialog(view,
            "Registration successful!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
        
        // Clear the form
        view.clearForm();
    }
    
    public void handleBackButton() {
        // Clear the form before going back
        view.clearForm();
        
        LoginSystemView loginView = new LoginSystemView();
        loginView.setVisible(true);
        view.dispose();
    }
} 