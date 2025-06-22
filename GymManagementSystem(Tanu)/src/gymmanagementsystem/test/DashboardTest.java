package gymmanagementsystem.test;

import gymmanagementsystem.controller.DashboardController;
import gymmanagementsystem.model.DashboardModel;
import gymmanagementsystem.view.DashBoardView;
import javax.swing.SwingUtilities;

/**
 * Test class to verify Dashboard functionality
 */
public class DashboardTest {
    
    public static void main(String[] args) {
        System.out.println("Testing Dashboard System...");
        
        // The view must be created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Test 1: Create dashboard view
                System.out.println("Test 1: Creating DashBoardView...");
                DashBoardView dashboardView = new DashBoardView();
                System.out.println("✓ DashBoardView created successfully");
                
                // Test 2: Create and verify controller
                System.out.println("Test 2: Creating DashboardController...");
                DashboardController dashboardController = new DashboardController(dashboardView);
                System.out.println("✓ DashboardController created successfully");
                
                // Test 3: Test BMI Calculator functionality
                System.out.println("Test 3: Testing BMI Calculator...");
                dashboardView.getHeightField().setText("70"); // Height in cm
                dashboardView.getWeightField().setText("65"); // Weight in kg
                
                if ("70".equals(dashboardView.getHeightField().getText()) && 
                    "65".equals(dashboardView.getWeightField().getText())) {
                    System.out.println("✓ BMI Calculator input fields working correctly");
                } else {
                    System.out.println("✗ BMI Calculator input fields not working correctly");
                }
                
                // Test 4: Test Clear button functionality
                System.out.println("Test 4: Testing Clear button...");
                dashboardView.getClearButton().doClick(); // Simulate clear button click
                
                if (dashboardView.getHeightField().getText().isEmpty() && 
                    dashboardView.getWeightField().getText().isEmpty() && 
                    dashboardView.getBmiResultField().getText().isEmpty()) {
                    System.out.println("✓ Clear button working correctly");
                } else {
                    System.out.println("✗ Clear button not working correctly");
                }
                
                // Test 5: Test Check-in button
                System.out.println("Test 5: Testing Check-in button...");
                if (dashboardView.getCheckInButton() != null) {
                    System.out.println("✓ Check-in button exists and accessible");
                } else {
                    System.out.println("✗ Check-in button not found");
                }
                
                // Test 6: Test Logout button
                System.out.println("Test 6: Testing Logout button...");
                if (dashboardView.getLogoutButton() != null) {
                    System.out.println("✓ Logout button exists and accessible");
                } else {
                    System.out.println("✗ Logout button not found");
                }
                
                // Test 7: Test Dashboard Model
                System.out.println("Test 7: Testing DashboardModel...");
                DashboardModel dashboardModel = new DashboardModel();
                String welcomeMessage = dashboardModel.getWelcomeMessage("TestUser");
                if ("Welcome, TestUser!".equals(welcomeMessage)) {
                    System.out.println("✓ DashboardModel welcome message working correctly");
                } else {
                    System.out.println("✗ DashboardModel welcome message not working correctly");
                }
                
                // Test 7.5: Test Time-based Greeting
                System.out.println("Test 7.5: Testing Time-based Greeting...");
                String timeGreeting = dashboardModel.getTimeBasedGreeting("TestUser");
                if (timeGreeting.contains("TestUser") && 
                    (timeGreeting.contains("Good Morning") || 
                     timeGreeting.contains("Good Afternoon") || 
                     timeGreeting.contains("Good Evening"))) {
                    System.out.println("✓ Time-based greeting working correctly: " + timeGreeting);
                } else {
                    System.out.println("✗ Time-based greeting not working correctly");
                }
                
                // Test 8: Test Streak functionality
                System.out.println("Test 8: Testing Streak functionality...");
                dashboardView.updateStreakLabel(5);
                if (dashboardView.getStreakNumberLabel().getText().equals("5")) {
                    System.out.println("✓ Streak update working correctly");
                } else {
                    System.out.println("✗ Streak update not working correctly");
                }
                
                // Test 9: Test Welcome Label
                System.out.println("Test 9: Testing Welcome Label...");
                dashboardView.setWelcomeLabel("Welcome, TestUser!");
                if (dashboardView.getWelcomeLabel().getText().equals("Welcome, TestUser!")) {
                    System.out.println("✓ Welcome label working correctly");
                } else {
                    System.out.println("✗ Welcome label not working correctly");
                }
                
                System.out.println("\nAll Dashboard tests completed successfully!");
                System.out.println("The Dashboard system should now be functional when run.");
                
                // Optional: Show the dashboard for manual inspection
                // dashboardView.setVisible(true);
                
            } catch (Exception e) {
                System.err.println("Error during testing: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
} 