package gymmanagementsystem.controller;

import gymmanagementsystem.model.ForgotPasswordModel;
import gymmanagementsystem.view.ForgotPasswordView;
import gymmanagementsystem.view.LoginSystemView;

public class ForgotPasswordController {
    private ForgotPasswordView view;
    private ForgotPasswordModel model;
    private LoginSystemView loginView;
    
    public ForgotPasswordController(ForgotPasswordView view, LoginSystemView loginView) {
        this.view = view;
        this.model = new ForgotPasswordModel();
        this.loginView = loginView;
        initializeListeners();
    }
    
    private void initializeListeners() {
        // Save button listener
        view.getSaveButton().addActionListener(e -> handleSave());
        
        // Exit button listener
        view.getExitButton().addActionListener(e -> handleExit());
    }
    
    private void handleSave() {
        String username = view.getUsernameField().getText();
        String email = view.getEmailField().getText();
        String newPassword = new String(view.getNewPasswordField().getPassword());
        String confirmPassword = new String(view.getConfirmPasswordField().getPassword());
        
        if (username == null || username.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(view, "Username is required.", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (email == null || email.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(view, "Email is required.", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!model.validateEmail(email)) {
            javax.swing.JOptionPane.showMessageDialog(view, "Email format is invalid.", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(view, "New password is required.", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(view, "Confirm password is required.", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!model.validatePassword(newPassword, confirmPassword)) {
            javax.swing.JOptionPane.showMessageDialog(view, "Passwords do not match.", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (model.updatePassword(username, email, newPassword)) {
            javax.swing.JOptionPane.showMessageDialog(view,
                "Password updated successfully!",
                "Success",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            handleExit();
        } else {
            javax.swing.JOptionPane.showMessageDialog(view,
                "Failed to update password. Please try again.",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleExit() {
        view.dispose();
        if (loginView != null) {
            loginView.setVisible(true);
        }
    }
} 