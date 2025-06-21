package gymmanagementsystem.controller;

import gymmanagesystem.model.LoginSystemModel;
import gymmanagementsystem.view.LoginSystemView;
import javax.swing.JOptionPane;

public class LoginSystemController {
    private LoginSystemView view;
    private LoginSystemModel model;

    public LoginSystemController(LoginSystemView view) {
        this.view = view;
        this.model = new LoginSystemModel();
        initializeListeners();
    }

    private void initializeListeners() {
        view.getSignInButton().addActionListener(e -> handleSignIn());
        // Add more listeners as needed
    }

    private void handleSignIn() {
        String email = view.getEmailField().getText();
        String password = new String(view.getPasswordField().getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter both email and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (model.authenticate(email, password)) {
            JOptionPane.showMessageDialog(view, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Proceed to next page or dashboard
        } else {
            JOptionPane.showMessageDialog(view, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
} 