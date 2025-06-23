package model;

public class Review {
    private int rating; // 1-5
    private String feedback;
    private String user;

    public Review(int rating, String feedback, String user) {
        this.rating = rating;
        this.feedback = feedback;
        this.user = user;
    }

    public int getRating() { return rating; }
    public String getFeedback() { return feedback; }
    public String getUser() { return user; }
}
