package gymmanagementsystem.controller;

import gymmanagesystem.model.DashboardModel;
import gymmanagementsystem.view.DashBoardView;

public class DashboardController {
    private DashBoardView view;
    private DashboardModel model;

    public DashboardController(DashBoardView view) {
        this.view = view;
        this.model = new DashboardModel();
        initializeListeners();
        updateWelcomeMessage();
    }

    private void initializeListeners() {
        view.getLogoutButton().addActionListener(e -> handleLogout());
        // Add more listeners for dashboard actions
    }

    private void updateWelcomeMessage() {
        String username = "User"; // Replace with actual username logic
        String message = model.getWelcomeMessage(username);
        view.setWelcomeLabel(message); // You need to add this method in your view
    }

    private void handleLogout() {
        int result = javax.swing.JOptionPane.showConfirmDialog(
            view,
            "Are you sure you want to logout?",
            "Logout Confirmation",
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (result == javax.swing.JOptionPane.YES_OPTION) {
            view.dispose();
            new gymmanagementsystem.view.LoginSystemView().setVisible(true);
        }
    }
} 