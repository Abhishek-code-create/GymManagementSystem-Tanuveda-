package gymmanagementsystem.controller;

import gymmanagementsystem.model.LoginSystemModel;
import gymmanagementsystem.view.AdminUserPageView;
import gymmanagementsystem.view.DashBoardView;
import gymmanagementsystem.view.LoginSystemView;
import gymmanagementsystem.view.UserRegisterView;
import javax.swing.JOptionPane;

public class LoginSystemController {
    private LoginSystemView view;
    private LoginSystemModel model;

    public LoginSystemController(LoginSystemView view) {
        this.view = view;
        this.model = new LoginSystemModel();
        initializeListeners();
    }
    
    public void open(){
        view.setVisible(true);
    }
    
    public void close(){
        view.dispose();
    }
   
    private void initializeListeners() {
        view.getSignInButton().addActionListener(e -> handleSignIn());
        view.getRegisterButton().addActionListener(e -> handleRegister());
    }

    public void handleSignIn() {
        String email = view.getEmailField().getText();
        String password = new String(view.getPasswordField().getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter both email and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String userType = model.authenticate(email, password);
        
        if (userType != null) {
            if ("admin".equals(userType)) {
                // Navigate to admin dashboard
                DashBoardView dashboardView = new DashBoardView();
                new DashboardController(dashboardView);
                dashboardView.setVisible(true);
                view.dispose();
            } else if ("user".equals(userType)) {
                // Navigate to user dashboard (you might want to create a separate user dashboard)
                DashBoardView dashboardView = new DashBoardView();
                new DashboardController(dashboardView);
                dashboardView.setVisible(true);
                view.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegister() {
        // Navigate to admin/user selection page with proper controller setup
        AdminUserPageView adminUserPageView = new AdminUserPageView(view);
        AdminUserPageController adminUserController = new AdminUserPageController(adminUserPageView);
        adminUserPageView.setController(adminUserController);
        adminUserController.open();
        view.setVisible(false);
    }

    public void handleBackToHome() {
        view.dispose();
        gymmanagementsystem.view.HomePageView homePageView = new gymmanagementsystem.view.HomePageView();
        new gymmanagementsystem.controller.HomePageController(homePageView);
        homePageView.setVisible(true);
    }

    public void handleForgotPassword() {
        gymmanagementsystem.view.ForgotPasswordView forgotPasswordView = new gymmanagementsystem.view.ForgotPasswordView(view);
        forgotPasswordView.setLocationRelativeTo(null);
        forgotPasswordView.setVisible(true);
        view.setVisible(false);
    }

    public void handleSignUp() {
        AdminUserPageView adminUserPageView = new AdminUserPageView(view);
        AdminUserPageController adminUserController = new AdminUserPageController(adminUserPageView);
        adminUserPageView.setController(adminUserController);
        adminUserController.open();
        view.setVisible(false);
    }

    public void navigateToUserRegistration() {
        close();
        UserRegisterView userRegisterView = new UserRegisterView();
        UserRegistrationController userRegController = new UserRegistrationController(userRegisterView);
        userRegisterView.setController(userRegController);
        userRegController.open();
    }
} 