package gymmanagesystem.controller;

import gymmanagesystem.model.DashboardModel;
import gymmanagesystem.view.DashBoardView;
import gymmanagesystem.view.gymproducts;


public class DashboardController {
    private DashBoardView view;
    private DashboardModel model;
    private GymProductsController productsController;

    public DashboardController(DashBoardView view) {
        this.view = view;
        this.model = new DashboardModel();
        initializeListeners();
        updateWelcomeMessage();
    }

    private void initializeListeners() {
        view.getLogoutButton().addActionListener(e -> handleLogout());
        
        // Add more listeners for other dashboard actions as needed
    }

    private void updateWelcomeMessage() {
        String username = getCurrentUsername(); // Correct logic to fetch current user
        String message = model.getWelcomeMessage(username);
        view.setWelcomeLabel(message);
    }

    private String getCurrentUsername() {
        // TODO: Replace with actual user session management
        return "User"; // Temporary placeholder
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
            new gymmanagesystem.view.LoginSystemView().setVisible(true);
        }
    }

    private void showGymProductsScreen() {
        view.setVisible(false); // Hide dashboard instead of disposing
        gymproducts productsView = new gymproducts();
        productsController = new GymProductsController(productsView);

        // If you want a return mechanism, you need to add a callback method in gymproducts
        productsView.setCloseCallback(() -> {
            productsView.dispose();
            view.setVisible(true); // Show dashboard again
        });

        productsView.setVisible(true);
    }
    
}
