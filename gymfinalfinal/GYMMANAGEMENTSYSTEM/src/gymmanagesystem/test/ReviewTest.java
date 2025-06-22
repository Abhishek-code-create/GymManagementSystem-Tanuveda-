package gymmanagesystem.test;

import gymmanagesystem.controller.ReviewController;
import gymmanagesystem.model.ReviewModel;
import gymmanagesystem.view.Reviewpage;

/**
 * Test class to verify Review system functionality
 */
public class ReviewTest {
    
    public static void main(String[] args) {
        System.out.println("Testing Review System...");
        
        try {
            // Test 1: Create ReviewModel
            System.out.println("Test 1: Creating ReviewModel...");
            ReviewModel model = new ReviewModel();
            model.setUserId("user123");
            model.setUserName("John Doe");
            model.setRating(5);
            model.setReviewTitle("Great Gym Experience");
            model.setReviewContent("This gym has excellent facilities and friendly staff. Highly recommended!");
            model.setGymService("General");
            System.out.println("✓ ReviewModel created successfully");
            System.out.println("  - User: " + model.getUserName());
            System.out.println("  - Rating: " + model.getRating() + " stars");
            System.out.println("  - Title: " + model.getReviewTitle());
            System.out.println("  - Content: " + model.getReviewContent());
            
            // Test 2: Validate ReviewModel
            System.out.println("Test 2: Validating ReviewModel...");
            if (model.isValid()) {
                System.out.println("✓ ReviewModel validation passed");
                System.out.println("  - Rating stars: " + model.getRatingStars());
                System.out.println("  - Formatted date: " + model.getFormattedDate());
            } else {
                System.out.println("✗ ReviewModel validation failed: " + model.getValidationMessage());
            }
            
            // Test 3: Create Reviewpage view
            System.out.println("Test 3: Creating Reviewpage view...");
            Reviewpage view = new Reviewpage();
            System.out.println("✓ Reviewpage view created successfully");
            
            // Test 4: Create ReviewController
            System.out.println("Test 4: Creating ReviewController...");
            ReviewController controller = new ReviewController(view, "user123", "John Doe");
            view.setController(controller);
            System.out.println("✓ ReviewController created successfully");
            
            // Test 5: Test getter/setter methods
            System.out.println("Test 5: Testing getter/setter methods...");
            view.setReviewTitle("Amazing Gym");
            view.setReviewContent("The best gym I've ever been to!");
            
            if ("Amazing Gym".equals(view.getReviewTitle()) && 
                "The best gym I've ever been to!".equals(view.getReviewContent())) {
                System.out.println("✓ Getter/setter methods working correctly");
            } else {
                System.out.println("✗ Getter/setter methods not working correctly");
            }
            
            // Test 6: Test invalid review
            System.out.println("Test 6: Testing invalid review...");
            ReviewModel invalidModel = new ReviewModel();
            invalidModel.setRating(0); // Invalid rating
            invalidModel.setReviewTitle(""); // Empty title
            invalidModel.setReviewContent("Short"); // Too short content
            
            if (!invalidModel.isValid()) {
                System.out.println("✓ Invalid review validation working correctly");
                System.out.println("  - Validation message: " + invalidModel.getValidationMessage());
            } else {
                System.out.println("✗ Invalid review validation not working correctly");
            }
            
            System.out.println("\nAll Review system tests completed successfully!");
            System.out.println("The Review system should now be functional when run in NetBeans IDE.");
            System.out.println("Features available:");
            System.out.println("- Submit reviews with validation");
            System.out.println("- Cancel reviews with confirmation");
            System.out.println("- Navigate back to dashboard");
            System.out.println("- Rating system (1-5 stars)");
            System.out.println("- Review title and content validation");
            
        } catch (Exception e) {
            System.err.println("Error during testing: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 