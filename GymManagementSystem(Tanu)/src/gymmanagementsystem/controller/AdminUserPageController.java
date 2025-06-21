package gymmanagementsystem.controller;

import gymmanagementsystem.view.AdminRegisterView;
import gymmanagementsystem.view.AdminUserPageView;
import gymmanagementsystem.view.LoginSystemView;
import gymmanagementsystem.view.UserRegisterView;

public class AdminUserPageController {
    private AdminUserPageView view;
    
    public AdminUserPageController(AdminUserPageView view) {
        this.view = view;
    }
    
    public void open() {
        view.setVisible(true);
    }
    
    public void close() {
        view.dispose();
    }
    
    public void handleAdminButton() {
        AdminRegisterView adminRegisterView = new AdminRegisterView();
        AdminRegistrationController adminRegController = new AdminRegistrationController(adminRegisterView);
        adminRegisterView.setController(adminRegController);
        adminRegController.open();
        view.dispose();
    }
    
    public void handleUserButton() {
        UserRegisterView userRegisterView = new UserRegisterView();
        UserRegistrationController userRegController = new UserRegistrationController(userRegisterView);
        userRegisterView.setController(userRegController);
        userRegController.open();
        view.dispose();
    }
    
    public void handleLoginLink() {
        view.dispose();
        LoginSystemView loginView = new LoginSystemView();
        LoginSystemController loginController = new LoginSystemController(loginView);
        loginView.setVisible(true);
    }
} 