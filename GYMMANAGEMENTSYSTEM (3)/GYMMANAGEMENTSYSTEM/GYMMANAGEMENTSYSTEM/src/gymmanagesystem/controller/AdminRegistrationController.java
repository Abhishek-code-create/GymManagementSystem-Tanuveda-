package gymmanagesystem.controller;

import gymmanagesystem.Dao.Admindao;
import gymmanagesystem.model.AdminRegistrationModel;
import gymmanagesystem.view.AdminRegisterView;
import javax.swing.*;
import java.util.Date;

public class AdminRegistrationController {

    private final AdminRegisterView view;
    private final Admindao dao;

    public AdminRegistrationController(AdminRegisterView view) {
        this.view = view;
        this.dao = new Admindao();
        this.view.setController(this);
    }

    public void handleRegister() {
        String fullName = view.getFullNameField().getText();
        String email = view.getEmailField().getText();
        String phone = view.getPhoneField().getText();
        String password = new String(view.getPasswordField().getPassword());
        String confirmPassword = new String(view.getConfirmPasswordField().getPassword());
        Date dateOfBirth = view.getDateOfBirthField().getDate();
        boolean termsAccepted = view.getTermsCheckBox().isSelected();

        // Basic validation
        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || dateOfBirth == null) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(view, "Passwords do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!termsAccepted) {
            JOptionPane.showMessageDialog(view, "Please accept the terms and policies.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create AdminRegistrationModel object
        AdminRegistrationModel admin = new AdminRegistrationModel();
        admin.setFullName(fullName);
        admin.setEmail(email);
        admin.setPhone(phone);
        admin.setPassword(password);
        admin.setDateOfBirth(dateOfBirth);
        
        
        
        

        // Insert into database
        boolean success = dao.registerAdmin(admin);

        if (success) {
            JOptionPane.showMessageDialog(view, "Admin registered successfully.");
            view.clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to register admin. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleShowPassword(JPasswordField passwordField, JButton button) {
        if (passwordField.getEchoChar() == (char) 0) {
            passwordField.setEchoChar('●');
            button.setText("Show");
        } else {
            passwordField.setEchoChar((char) 0);
            button.setText("Hide");
        }
    }

    public void handleBackToLogin() {
        view.dispose();
        // You can open your LoginView here
        // Example:
        // LoginSystemView loginView = new LoginSystemView();
        // new LoginSystemController(loginView);
        // loginView.setVisible(true);
    }

    void open() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
