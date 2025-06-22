package view;

import java.awt.*;
import java.time.LocalDateTime;
import javax.swing.*;
import model.Notification;

public class NotificationView extends JFrame {
    private JTextField titleField;
    private JTextArea messageArea;
    private JComboBox<String> audienceBox;
    private JTextField recipientField;
    private JCheckBox scheduleCheckBox;
    private JSpinner dateTimeSpinner;
    private JButton previewButton;
    private JButton sendButton;
    private JLabel statusLabel;

    public NotificationView(controller.NotificationController controller) {
        setTitle("Send Notification");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        titleField = new JTextField();
        messageArea = new JTextArea(5, 20);
        audienceBox = new JComboBox<>(new String[]{"ALL", "Specific User"});
        recipientField = new JTextField();
        scheduleCheckBox = new JCheckBox("Schedule");
        dateTimeSpinner = new JSpinner(new SpinnerDateModel());
        previewButton = new JButton("Preview");
        sendButton = new JButton("Send");
        statusLabel = new JLabel();

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Message:"));
        formPanel.add(new JScrollPane(messageArea));
        formPanel.add(new JLabel("Target Audience:"));
        formPanel.add(audienceBox);
        formPanel.add(new JLabel("Recipient (if specific):"));
        formPanel.add(recipientField);
        formPanel.add(scheduleCheckBox);
        formPanel.add(new JLabel("Schedule Time:"));
        formPanel.add(dateTimeSpinner);
        formPanel.add(previewButton);
        formPanel.add(sendButton);
        formPanel.add(statusLabel);

        add(formPanel, BorderLayout.CENTER);

        recipientField.setEnabled(false);
        dateTimeSpinner.setEnabled(false);

        audienceBox.addActionListener(e -> {
            recipientField.setEnabled(audienceBox.getSelectedItem().equals("Specific User"));
        });
        scheduleCheckBox.addActionListener(e -> {
            dateTimeSpinner.setEnabled(scheduleCheckBox.isSelected());
        });

        previewButton.addActionListener(e -> previewNotification());
        sendButton.addActionListener(e -> {
            String title = titleField.getText();
            String message = messageArea.getText();
            String audience = (String) audienceBox.getSelectedItem();
            String recipient = recipientField.getText();
            LocalDateTime scheduledTime = scheduleCheckBox.isSelected() ? 
                LocalDateTime.ofInstant(((java.util.Date)dateTimeSpinner.getValue()).toInstant(), java.time.ZoneId.systemDefault()) : null;
            Notification notification = new Notification(title, "INFO", message, audience, recipient, scheduledTime);
            if (scheduleCheckBox.isSelected()) {
                notification.setDeliveryStatus("PENDING");
            } else {
                notification.setDeliveryStatus("SENT");
            }
            controller.addNotification(notification);
            statusLabel.setText("Notification sent!");
        });
    }

    private void previewNotification() {
        String title = titleField.getText();
        String message = messageArea.getText();
        String audience = (String) audienceBox.getSelectedItem();
        String recipient = recipientField.getText();
        String preview = String.format("Title: %s\nMessage: %s\nAudience: %s\nRecipient: %s", title, message, audience, recipient);
        JOptionPane.showMessageDialog(this, preview, "Preview Notification", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayNotifications(java.util.List<Notification> notifications) {
        // Implementation to display notifications (optional)
    }
}
