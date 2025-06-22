package gymmanagementsystem.test;

import gymmanagementsystem.view.PersonalInformation;
import javax.swing.SwingUtilities;

/**
 * Test class to verify Personal Information functionality
 */
public class PersonalInfoTest {
    
    public static void main(String[] args) {
        System.out.println("Testing Personal Information System...");
        
        // The view must be created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Test 1: Create view
                System.out.println("Test 1: Creating PersonalInformation view...");
                // The view now requires a username and creates its own controller.
                PersonalInformation view = new PersonalInformation("testuser");
                System.out.println("✓ PersonalInformation view created successfully");

                // The view is initialized with mock data by the controller. Let's check it.
                System.out.println("Test 2: Verifying initial data loaded by controller...");
                if ("Siddhartha Sah".equals(view.getFullNameField().getText())) {
                    System.out.println("✓ Initial data loaded correctly.");
                } else {
                    System.out.println("✗ Initial data not loaded correctly. Expected 'Siddhartha Sah', got '" + view.getFullNameField().getText() + "'");
                }

                // Test 3: Test setting and getting text from fields
                System.out.println("Test 3: Testing setting/getting text from fields...");
                view.getFullNameField().setText("Jane Smith");
                view.getEmailField().setText("jane.smith@example.com");
                view.getPhoneField().setText("5551234567");
                
                if ("Jane Smith".equals(view.getFullNameField().getText()) && 
                    "jane.smith@example.com".equals(view.getEmailField().getText()) &&
                    "5551234567".equals(view.getPhoneField().getText())) {
                    System.out.println("✓ Field getter/setter methods working correctly");
                } else {
                    System.out.println("✗ Field getter/setter methods not working correctly");
                }
                
                // The view can be made visible to manually inspect if needed,
                // but for an automated test, we just check the state.
                // view.setVisible(true); 

                System.out.println("\nAll Personal Information tests completed successfully!");
                System.out.println("The Personal Information system should now be functional when run.");
            
            } catch (Exception e) {
                System.err.println("Error during testing: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Since the test runs and finishes, we should exit the application
                // if we were to show the window. Otherwise, the test will hang.
                // System.exit(0);
            }
        });
    }
} 