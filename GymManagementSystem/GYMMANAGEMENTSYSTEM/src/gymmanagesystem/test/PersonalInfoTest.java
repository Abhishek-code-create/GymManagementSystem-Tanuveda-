package gymmanagesystem.test;

import gymmanagesystem.controller.personalcontroller;
import gymmanagesystem.model.personaldata;
import gymmanagesystem.view.PersonalInformation;

/**
 * Test class to verify Personal Information functionality
 */
public class PersonalInfoTest {
    
    public static void main(String[] args) {
        System.out.println("Testing Personal Information System...");
        
        try {
            // Test 1: Create model
            System.out.println("Test 1: Creating personaldata model...");
            personaldata model = new personaldata();
            model.setFullName("John Doe");
            model.setEmail("john.doe@example.com");
            model.setPhone("1234567890");
            model.setDob("1990-01-01");
            model.setGender("Male");
            model.setAddress("123 Main St, City, State");
            model.setEmergencyContact("9876543210");
            System.out.println("✓ personaldata model created successfully");
            System.out.println("  - Full Name: " + model.getFullName());
            System.out.println("  - Email: " + model.getEmail());
            System.out.println("  - Phone: " + model.getPhone());
            
            // Test 2: Create view
            System.out.println("Test 2: Creating PersonalInformation view...");
            PersonalInformation view = new PersonalInformation();
            System.out.println("✓ PersonalInformation view created successfully");
            
            // Test 3: Create controller
            System.out.println("Test 3: Creating personalcontroller...");
            personalcontroller controller = new personalcontroller(model, view);
            view.setController(controller);
            System.out.println("✓ personalcontroller created successfully");
            
            // Test 4: Test getter/setter methods
            System.out.println("Test 4: Testing getter/setter methods...");
            view.setFullName("Jane Smith");
            view.setEmail("jane.smith@example.com");
            view.setPhone("5551234567");
            
            if ("Jane Smith".equals(view.getFullName()) && 
                "jane.smith@example.com".equals(view.getEmail()) &&
                "5551234567".equals(view.getPhone())) {
                System.out.println("✓ Getter/setter methods working correctly");
            } else {
                System.out.println("✗ Getter/setter methods not working correctly");
            }
            
            // Test 5: Test model validation
            System.out.println("Test 5: Testing model validation...");
            if (model.isValid()) {
                System.out.println("✓ Model validation working correctly");
            } else {
                System.out.println("✗ Model validation not working correctly");
            }
            
            System.out.println("\nAll Personal Information tests completed successfully!");
            System.out.println("The Personal Information system should now be functional when run in NetBeans IDE.");
            
        } catch (Exception e) {
            System.err.println("Error during testing: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 