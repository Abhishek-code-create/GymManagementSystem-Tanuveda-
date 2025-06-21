package gymmanagementsystem.controller;

import gymmanagementsystem.model.ReviewModel;
import gymmanagementsystem.view.Reviewpage;
import gymmanagementsystem.view.DashBoardView;
import gymmanagementsystem.database.Mysqlconnection;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller for Review management
 */
public class ReviewController {
    private ReviewModel model;
    private Reviewpage view;
    private String currentUserId;
    private String currentUserName;
    private Mysqlconnection dbConnection;

    public ReviewController(Reviewpage view) {
        this.view = view;
        this.model = new ReviewModel();
        this.dbConnection = new Mysqlconnection();
        initializeView();
    }

    public ReviewController(Reviewpage view, String userId, String userName) {
        this.view = view;
        this.model = new ReviewModel();
        this.currentUserId = userId;
        this.currentUserName = userName;
        this.dbConnection = new Mysqlconnection();
        initializeView();
    }

    // Initialize the view
    private void initializeView() {
        // Set up rating combo box if it exists
        setupRatingComboBox();
        
        // Clear form fields
        clearForm();
    }

    // Set up rating selection
    private void setupRatingComboBox() {
        // If you have a rating combo box, populate it
        // For now, we'll use text field validation
    }

    // Handle submit review
    public void submitReview() {
        try {
            // Get data from view
            String ratingText = view.getRating();
            String reviewTitle = view.getReviewTitle();
            String reviewContent = view.getReviewContent();

            // Validate rating
            int rating = 0;
            try {
                rating = Integer.parseInt(ratingText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, 
                    "Please enter a valid rating (1-5).", 
                    "Invalid Rating", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Set model data
            model.setRating(rating);
            model.setReviewTitle(reviewTitle);
            model.setReviewContent(reviewContent);
            model.setReviewDate(new Date());
            
            if (currentUserId != null) {
                model.setUserId(currentUserId);
            }
            if (currentUserName != null) {
                model.setUserName(currentUserName);
            }

            // Validate the review
            if (!model.isValid()) {
                JOptionPane.showMessageDialog(view, 
                    model.getValidationMessage(), 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Save to database
            if (saveReviewToDatabase()) {
                JOptionPane.showMessageDialog(view,
                    "Review submitted successfully! Thank you for your feedback.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Clear form and go back to dashboard
                clearForm();
                navigateToDashboard();
            } else {
                JOptionPane.showMessageDialog(view,
                    "Failed to submit review. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                "Error submitting review: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Handle cancel review
    public void cancelReview() {
        int response = JOptionPane.showConfirmDialog(
            view,
            "Are you sure you want to cancel? All entered data will be lost.",
            "Confirm Cancel",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            clearForm();
            navigateToDashboard();
        }
    }

    // Handle back to dashboard
    public void backToDashboard() {
        int response = JOptionPane.showConfirmDialog(
            view,
            "Are you sure you want to go back to dashboard?",
            "Confirm Navigation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            navigateToDashboard();
        }
    }

    // Clear the form
    public void clearForm() {
        view.setRating("");
        view.setReviewTitle("");
        view.setReviewContent("");
    }

    // Navigate to dashboard
    private void navigateToDashboard() {
        view.dispose();
        DashBoardView dashboardView = new DashBoardView();
        DashboardController dashboardController = new DashboardController(dashboardView);
        dashboardView.setVisible(true);
    }

    // Save review to database
    private boolean saveReviewToDatabase() {
        try {
            // Create reviews table if it doesn't exist
            createReviewsTableIfNotExists();
            
            String sql = "INSERT INTO reviews (user_id, user_name, rating, review_title, review_content, review_date, gym_service, is_approved) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (Connection conn = dbConnection.openConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
                ps.setString(1, model.getUserId());
                ps.setString(2, model.getUserName());
                ps.setInt(3, model.getRating());
                ps.setString(4, model.getReviewTitle());
                ps.setString(5, model.getReviewContent());
                ps.setDate(6, new java.sql.Date(model.getReviewDate().getTime()));
                ps.setString(7, model.getGymService());
                ps.setBoolean(8, model.isApproved());
                
                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;
                
            } catch (SQLException e) {
                System.err.println("Error saving review: " + e.getMessage());
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("Error in saveReviewToDatabase: " + e.getMessage());
            return false;
        }
    }
    
    // Create reviews table if it doesn't exist
    private void createReviewsTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS reviews (" +
                "review_id INT AUTO_INCREMENT PRIMARY KEY," +
                "user_id VARCHAR(100)," +
                "user_name VARCHAR(100)," +
                "rating INT NOT NULL," +
                "review_title VARCHAR(200) NOT NULL," +
                "review_content TEXT NOT NULL," +
                "review_date DATE NOT NULL," +
                "gym_service VARCHAR(100) DEFAULT 'General'," +
                "is_approved BOOLEAN DEFAULT FALSE," +
                "admin_response TEXT," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
        
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(createTableSQL)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating reviews table: " + e.getMessage());
        }
    }

    // Load review from database
    public void loadReviewFromDatabase(int reviewId) {
        try {
            String sql = "SELECT * FROM reviews WHERE review_id = ?";
            
            try (Connection conn = dbConnection.openConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
                ps.setInt(1, reviewId);
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    model.setReviewId(rs.getInt("review_id"));
                    model.setUserId(rs.getString("user_id"));
                    model.setUserName(rs.getString("user_name"));
                    model.setRating(rs.getInt("rating"));
                    model.setReviewTitle(rs.getString("review_title"));
                    model.setReviewContent(rs.getString("review_content"));
                    model.setReviewDate(rs.getDate("review_date"));
                    model.setGymService(rs.getString("gym_service"));
                    model.setApproved(rs.getBoolean("is_approved"));
                    model.setAdminResponse(rs.getString("admin_response"));
                    
                    // Update view
                    view.setRating(String.valueOf(model.getRating()));
                    view.setReviewTitle(model.getReviewTitle());
                    view.setReviewContent(model.getReviewContent());
                }
                
            } catch (SQLException e) {
                System.err.println("Error loading review: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error in loadReviewFromDatabase: " + e.getMessage());
        }
    }

    // Get all reviews
    public java.util.List<ReviewModel> getAllReviews() {
        java.util.List<ReviewModel> reviews = new java.util.ArrayList<>();
        
        try {
            String sql = "SELECT * FROM reviews ORDER BY review_date DESC";
            
            try (Connection conn = dbConnection.openConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                
                while (rs.next()) {
                    ReviewModel review = new ReviewModel();
                    review.setReviewId(rs.getInt("review_id"));
                    review.setUserId(rs.getString("user_id"));
                    review.setUserName(rs.getString("user_name"));
                    review.setRating(rs.getInt("rating"));
                    review.setReviewTitle(rs.getString("review_title"));
                    review.setReviewContent(rs.getString("review_content"));
                    review.setReviewDate(rs.getDate("review_date"));
                    review.setGymService(rs.getString("gym_service"));
                    review.setApproved(rs.getBoolean("is_approved"));
                    review.setAdminResponse(rs.getString("admin_response"));
                    
                    reviews.add(review);
                }
                
            } catch (SQLException e) {
                System.err.println("Error getting all reviews: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error in getAllReviews: " + e.getMessage());
        }
        
        return reviews;
    }

    // Get reviews by user
    public java.util.List<ReviewModel> getReviewsByUser(String userId) {
        java.util.List<ReviewModel> reviews = new java.util.ArrayList<>();
        
        try {
            String sql = "SELECT * FROM reviews WHERE user_id = ? ORDER BY review_date DESC";
            
            try (Connection conn = dbConnection.openConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
                ps.setString(1, userId);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    ReviewModel review = new ReviewModel();
                    review.setReviewId(rs.getInt("review_id"));
                    review.setUserId(rs.getString("user_id"));
                    review.setUserName(rs.getString("user_name"));
                    review.setRating(rs.getInt("rating"));
                    review.setReviewTitle(rs.getString("review_title"));
                    review.setReviewContent(rs.getString("review_content"));
                    review.setReviewDate(rs.getDate("review_date"));
                    review.setGymService(rs.getString("gym_service"));
                    review.setApproved(rs.getBoolean("is_approved"));
                    review.setAdminResponse(rs.getString("admin_response"));
                    
                    reviews.add(review);
                }
                
            } catch (SQLException e) {
                System.err.println("Error getting reviews by user: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error in getReviewsByUser: " + e.getMessage());
        }
        
        return reviews;
    }

    // Approve/Reject review (Admin functionality)
    public void approveReview(int reviewId, boolean approved, String adminResponse) {
        try {
            String sql = "UPDATE reviews SET is_approved = ?, admin_response = ? WHERE review_id = ?";
            
            try (Connection conn = dbConnection.openConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
                ps.setBoolean(1, approved);
                ps.setString(2, adminResponse);
                ps.setInt(3, reviewId);
                
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Review " + (approved ? "approved" : "rejected") + " successfully");
                }
                
            } catch (SQLException e) {
                System.err.println("Error approving review: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error in approveReview: " + e.getMessage());
        }
    }

    // Get average rating
    public double getAverageRating() {
        try {
            String sql = "SELECT AVG(rating) as avg_rating FROM reviews WHERE is_approved = TRUE";
            
            try (Connection conn = dbConnection.openConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                
                if (rs.next()) {
                    return rs.getDouble("avg_rating");
                }
                
            } catch (SQLException e) {
                System.err.println("Error getting average rating: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error in getAverageRating: " + e.getMessage());
        }
        
        return 0.0;
    }

    // Get total reviews count
    public int getTotalReviews() {
        try {
            String sql = "SELECT COUNT(*) as total FROM reviews WHERE is_approved = TRUE";
            
            try (Connection conn = dbConnection.openConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                
                if (rs.next()) {
                    return rs.getInt("total");
                }
                
            } catch (SQLException e) {
                System.err.println("Error getting total reviews: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error in getTotalReviews: " + e.getMessage());
        }
        
        return 0;
    }
} 