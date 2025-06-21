package gymmanagementsystem.controller;

import gymmanagementsystem.model.UserRegistrationModel;
import gymmanagementsystem.view.LoginSystemView;
import gymmanagementsystem.view.UserRegisterView;
import javax.swing.JOptionPane;

public class UserRegistrationController {
    private final UserRegisterView view;
    private final UserRegistrationModel model;
    
    public UserRegistrationController(UserRegisterView view) {
        this.view = view;
        this.model = new UserRegistrationModel();
    }
    
    public void open() {
        view.setVisible(true);
    }
    
    public void close() {
        view.dispose();
    }
    
    public void handleRegister() {
        String fullName = view.getFullNameField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String phone = view.getPhoneField().getText().trim();
        String password = new String(view.getPasswordField().getPassword());
        String confirmPassword = new String(view.getConfirmPasswordField().getPassword());
        
        // Get date safely
        java.util.Date dateOfBirth = view.getDateOfBirthField();
        
        // Check for empty fields first
        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || 
            password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "All fields are required. Please fill in all the information.",
                "Missing Information",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Check if terms are agreed
        if (!view.getTermsCheckBox().isSelected()) {
            JOptionPane.showMessageDialog(view, 
                "Please agree to the Terms and Privacy Policies.",
                "Terms Not Agreed",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Check if email already exists
        if (model.isEmailAlreadyExists(email)) {
            JOptionPane.showMessageDialog(view, 
                "An account with this email already exists. Please use a different email or try logging in.",
                "Email Already Exists",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!model.validateAllFields(fullName, email, password, confirmPassword)) {
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
        
        // Try to register the user
        if (model.registerUser(fullName, email, phone, password, dateOfBirth)) {
            JOptionPane.showMessageDialog(view,
                "Registration successful! You can now login with your credentials.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Navigate back to login
            navigateToLogin();
        } else {
            JOptionPane.showMessageDialog(view,
                "Registration failed. Please try again.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void navigateToLogin() {
        // Clear the form before going back
        view.clearForm();
        
        // Close current view and open new login view
        close();
        LoginSystemController loginController = new LoginSystemController(new LoginSystemView());
        loginController.open();
    }
    
    public void handleShowPassword(javax.swing.JPasswordField passwordField, javax.swing.JButton showButton) {
        if (passwordField.getEchoChar() == '•') {
            passwordField.setEchoChar((char) 0);
            showButton.setText("Hide");
        } else {
            passwordField.setEchoChar('•');
            showButton.setText("Show");
        }
    }
} 