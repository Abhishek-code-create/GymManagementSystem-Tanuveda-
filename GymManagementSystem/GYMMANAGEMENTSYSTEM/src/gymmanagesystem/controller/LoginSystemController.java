package gymmanagesystem.controller;

import gymmanagesystem.Dao.Admindao;
import gymmanagesystem.Dao.Userdao;
import gymmanagesystem.view.AdminUserPageView;
import gymmanagesystem.view.DashBoardView;
import gymmanagesystem.view.ForgotPasswordView;
import gymmanagesystem.view.LoginSystemView;
import gymmanagesystem.view.members;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import gymmanagesystem.model.CurrentUser;
import gymmanagesystem.model.User;

public class LoginSystemController {
    private final LoginSystemView view;
    private final Userdao userdao;
    private final Admindao admindao;
    private LoginSystemView LoginSystemView;

    public LoginSystemController(LoginSystemView view) {
        this.view = view;
        this.userdao = new Userdao();
        this.admindao = new Admindao();
        this.view.setController(this); // Set controller to view
        this.view.addLoginListener(new LoginListener()); // Attach login listener
    }

    public void open() {
         view.setVisible(true);
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getUsername(); // assuming username is actually email
            String password = view.getPassword();

            if (admindao.loginAdmin(email, password)) {
                JOptionPane.showMessageDialog(view, "Admin login successful!");
                view.dispose();
                new members().setVisible(true);
            } else if (userdao.login(email, password)) {
                // Fetch user info and set CurrentUser
                User user = userdao.getUserByEmail(email);
                if (user != null) {
                    CurrentUser.setUser(user.getFullName(), user.getEmail(), user.getPhone());
                }
                JOptionPane.showMessageDialog(view, "User login successful!");
                view.dispose();
                new DashBoardView().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    // Optional: Navigation handling
    public void handleForgotPassword() {
        ForgotPasswordView forgotView = new ForgotPasswordView();
        ForgotPasswordController forgotController = new ForgotPasswordController(forgotView, LoginSystemView);
        forgotView.setVisible(true);
        LoginSystemView.setVisible(false);

    }

    public void handleBackToHome() {
        JOptionPane.showMessageDialog(view, "Going back to Home Page...");
        // Add logic to open HomePageView if needed
    }
    
    public void handleSignUp() {
    view.dispose(); // Close Login View
    AdminUserPageView adminUserPageView = new AdminUserPageView(); // Open AdminUserPageView
    AdminUserPageController adminUserPageController = new AdminUserPageController(adminUserPageView); // Attach Controller
    adminUserPageView.setController(adminUserPageController);
    adminUserPageView.setVisible(true);
    }



    public void handleSignIn() {
    String email = view.getUsername();
    String password = view.getPassword();

    if (admindao.loginAdmin(email, password)) {
        JOptionPane.showMessageDialog(view, "Admin login successful!");
        view.dispose();
        new members().setVisible(true);
    } else if (userdao.login(email, password)) {
        JOptionPane.showMessageDialog(view, "User login successful!");
        view.dispose();
        new DashBoardView().setVisible(true);
    } else {
        JOptionPane.showMessageDialog(view, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
}


}
