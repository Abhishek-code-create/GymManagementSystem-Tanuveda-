package view;

import model.Review;
import controller.NotificationController;
import javax.swing.*;
import java.awt.*;

public class ReviewView extends JFrame {
    private JComboBox<Integer> starBox;
    private JTextArea feedbackArea;
    private JButton submitBtn;

    public ReviewView(String user) {
        setTitle("Submit Review");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(0, 1, 5, 5));
        form.add(new JLabel("Star Rating (1-5):"));
        starBox = new JComboBox<>(new Integer[]{1,2,3,4,5});
        form.add(starBox);
        form.add(new JLabel("Feedback:"));
        feedbackArea = new JTextArea(4, 20);
        form.add(new JScrollPane(feedbackArea));
        submitBtn = new JButton("Submit Review");
        form.add(submitBtn);
        add(form, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> {
            int rating = (int) starBox.getSelectedItem();
            String feedback = feedbackArea.getText().trim();
            if (feedback.isEmpty() || containsOffensive(feedback)) {
                JOptionPane.showMessageDialog(this, "Please enter valid, non-offensive feedback.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Review review = new Review(rating, feedback, user);
            // Notify admin (send notification)
            NotificationController.getInstance().addNotification("INFO", "New review submitted by " + user + ": " + feedback);
            JOptionPane.showMessageDialog(this, "Thank you for your review!", "Submitted", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
    }

    private boolean containsOffensive(String text) {
        String[] badWords = {"badword1", "badword2", "offensive"}; // Add more as needed
        for (String w : badWords) {
            if (text.toLowerCase().contains(w)) return true;
        }
        return false;
    }
}
