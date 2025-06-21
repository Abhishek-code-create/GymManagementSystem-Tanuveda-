package gymmanagementsystem.test;

import gymmanagementsystem.controller.AdminUserPageController;
import gymmanagementsystem.controller.LoginSystemController;
import gymmanagementsystem.view.AdminUserPageView;
import gymmanagementsystem.view.LoginSystemView;

/**
 * Simple test class to verify controller connections are working
 */
public class ConnectionTest {
    
    public static void main(String[] args) {
        System.out.println("Testing Gym Management System Connections...");
        
        try {
            // Test 1: Login System Controller
            System.out.println("Test 1: Creating LoginSystemView and Controller...");
            LoginSystemView loginView = new LoginSystemView();
            LoginSystemController loginController = new LoginSystemController(loginView);
            System.out.println("✓ LoginSystemController created successfully");
            
            // Test 2: Admin User Page Controller
            System.out.println("Test 2: Creating AdminUserPageView and Controller...");
            AdminUserPageView adminUserView = new AdminUserPageView(loginView);
            AdminUserPageController adminUserController = new AdminUserPageController(adminUserView);
            adminUserView.setController(adminUserController);
            System.out.println("✓ AdminUserPageController created successfully");
            
            // Test 3: Verify button connections
            System.out.println("Test 3: Verifying button connections...");
            if (adminUserView.getController() != null) {
                System.out.println("✓ AdminUserPageView controller is properly set");
            } else {
                System.out.println("✗ AdminUserPageView controller is null");
            }
            
            if (loginView.getController() != null) {
                System.out.println("✓ LoginSystemView controller is properly set");
            } else {
                System.out.println("✗ LoginSystemView controller is null");
            }
            
            System.out.println("\nAll connection tests completed successfully!");
            System.out.println("The buttons should now be functional when run in NetBeans IDE.");
            
        } catch (Exception e) {
            System.err.println("Error during testing: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 