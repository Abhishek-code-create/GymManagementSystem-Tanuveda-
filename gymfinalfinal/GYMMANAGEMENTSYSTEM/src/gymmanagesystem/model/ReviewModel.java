package gymmanagesystem.model;

import java.util.Date;

/**
 * Model class for gym reviews
 */
public class ReviewModel {
    private int reviewId;
    private String userId;
    private String userName;
    private int rating;
    private String reviewTitle;
    private String reviewContent;
    private Date reviewDate;
    private String gymService;
    private boolean isApproved;
    private String adminResponse;

    // Default constructor
    public ReviewModel() {
        this.reviewId = 0;
        this.userId = "";
        this.userName = "";
        this.rating = 0;
        this.reviewTitle = "";
        this.reviewContent = "";
        this.reviewDate = new Date();
        this.gymService = "General";
        this.isApproved = false;
        this.adminResponse = "";
    }

    // Parameterized constructor
    public ReviewModel(String userId, String userName, int rating, String reviewTitle, 
                      String reviewContent, String gymService) {
        this.userId = userId;
        this.userName = userName;
        this.rating = rating;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewDate = new Date();
        this.gymService = gymService;
        this.isApproved = false;
        this.adminResponse = "";
    }

    // Getters
    public int getReviewId() {
        return reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public String getGymService() {
        return gymService;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public String getAdminResponse() {
        return adminResponse;
    }

    // Setters
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public void setGymService(String gymService) {
        this.gymService = gymService;
    }

    public void setApproved(boolean approved) {
        this.isApproved = approved;
    }

    public void setAdminResponse(String adminResponse) {
        this.adminResponse = adminResponse;
    }

    // Validation methods
    public boolean isValid() {
        return rating >= 1 && rating <= 5 &&
               reviewTitle != null && !reviewTitle.trim().isEmpty() &&
               reviewContent != null && !reviewContent.trim().isEmpty() &&
               reviewContent.length() >= 10; // Minimum 10 characters
    }

    public String getValidationMessage() {
        if (rating < 1 || rating > 5) {
            return "Rating must be between 1 and 5 stars.";
        }
        if (reviewTitle == null || reviewTitle.trim().isEmpty()) {
            return "Review title is required.";
        }
        if (reviewContent == null || reviewContent.trim().isEmpty()) {
            return "Review content is required.";
        }
        if (reviewContent.length() < 10) {
            return "Review content must be at least 10 characters long.";
        }
        return "Valid";
    }

    // Helper methods
    public String getRatingStars() {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < rating; i++) {
            stars.append("★");
        }
        for (int i = rating; i < 5; i++) {
            stars.append("☆");
        }
        return stars.toString();
    }

    public String getFormattedDate() {
        return String.format("%tB %te, %tY", reviewDate, reviewDate, reviewDate);
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", rating=" + rating +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", reviewDate=" + reviewDate +
                ", gymService='" + gymService + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }
} 