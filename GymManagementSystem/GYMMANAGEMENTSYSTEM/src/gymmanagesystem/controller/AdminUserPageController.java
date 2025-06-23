package gymmanagesystem.controller;

import gymmanagesystem.view.AdminRegisterView;
import gymmanagesystem.view.AdminUserPageView;
import gymmanagesystem.view.LoginSystemView;
import gymmanagesystem.view.UserRegisterView;

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
    view.dispose();
    AdminRegisterView adminRegisterView = new AdminRegisterView();
    new AdminRegistrationController(adminRegisterView);
    adminRegisterView.setVisible(true);
}

public void handleUserButton() {
    view.dispose();
    UserRegisterView userRegisterView = new UserRegisterView();
    UserRegistrationController controller = new UserRegistrationController(userRegisterView);
    userRegisterView.setController(controller);
    userRegisterView.setVisible(true);
}

public void handleLoginLink() {
    view.dispose();
    LoginSystemView loginView = new LoginSystemView();
    loginView.setVisible(true);
}
}