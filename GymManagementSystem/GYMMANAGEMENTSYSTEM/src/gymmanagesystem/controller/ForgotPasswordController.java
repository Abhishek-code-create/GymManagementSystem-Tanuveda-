package gymmanagesystem.controller;

import gymmanagesystem.model.ForgotPasswordModel;
import gymmanagesystem.view.ForgotPasswordView;
import gymmanagesystem.view.LoginSystemView;

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
        
        // Show/hide password listeners
        view.getShowPasswordButton1().addActionListener(e -> togglePasswordField(view.getNewPasswordField(), view.getShowPasswordButton1()));
        view.getShowPasswordButton2().addActionListener(e -> togglePasswordField(view.getConfirmPasswordField(), view.getShowPasswordButton2()));
    }
    
    private void togglePasswordField(javax.swing.JPasswordField field, javax.swing.JToggleButton button) {
        if (button.isSelected()) {
            field.setEchoChar((char)0);
            button.setText("Hide");
        } else {
            field.setEchoChar('•');
            button.setText("Show");
        }
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