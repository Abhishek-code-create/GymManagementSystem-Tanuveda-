package gymmanagesystem.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import gymmanagesystem.Dao.NotificationDAO;

public class NotificationPopupFrame extends JDialog {
    // Static storage for notifications
    private static final List<Notification> notifications = new ArrayList<>();

    public static List<Notification> getAllNotifications() {
        return new ArrayList<>(notifications);
    }

    public NotificationPopupFrame(JFrame parent) {
        super(parent, "Create Notification", true);
        setSize(400, 350);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Top bar with close button
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        JLabel title = new JLabel("Create Notification");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        topBar.add(title, BorderLayout.WEST);
        JLabel close = new JLabel("\u2716");
        close.setFont(new Font("Arial", Font.BOLD, 16));
        close.setForeground(Color.GRAY);
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        close.setToolTipText("Close");
        close.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { dispose(); }
        });
        topBar.add(close, BorderLayout.EAST);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(topBar, gbc);

        gbc.gridy++;
        JLabel subtitle = new JLabel("Create a new notification to send to your members.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        panel.add(subtitle, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Notification Title"), gbc);
        gbc.gridx = 1;
        JTextField titleField = new JTextField();
        panel.add(titleField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Message"), gbc);
        gbc.gridx = 1;
        JTextArea messageArea = new JTextArea(3, 18);
        JScrollPane messageScroll = new JScrollPane(messageArea);
        panel.add(messageScroll, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Notification Type"), gbc);
        gbc.gridx = 1;
        JComboBox<String> typeBox = new JComboBox<>(new String[] {"Announcement", "Reminder", "Offer"});
        panel.add(typeBox, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Target Audience"), gbc);
        gbc.gridx = 1;
        JComboBox<String> audienceBox = new JComboBox<>(new String[] {"All Members", "Active Members", "Inactive Members"});
        panel.add(audienceBox, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JCheckBox scheduleBox = new JCheckBox("Schedule for later");
        panel.add(scheduleBox, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save as Draft");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(cancel);
        buttonPanel.add(save);
        panel.add(buttonPanel, gbc);

        add(panel, BorderLayout.CENTER);

        cancel.addActionListener((ActionEvent e) -> dispose());
        save.addActionListener((ActionEvent e) -> {
            // Save notification to database
            NotificationDAO dao = new NotificationDAO();
            boolean success = dao.insertNotification(
                titleField.getText(),
                messageArea.getText(),
                (String) typeBox.getSelectedItem(),
                (String) audienceBox.getSelectedItem(),
                scheduleBox.isSelected()
            );
            if (success) {
                JOptionPane.showMessageDialog(this, "Notification saved as draft!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save notification.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        });
    }

    // Notification data class
    public static class Notification {
        public final String title;
        public final String message;
        public final String type;
        public final String audience;
        public final boolean scheduled;
        public Notification(String title, String message, String type, String audience, boolean scheduled) {
            this.title = title;
            this.message = message;
            this.type = type;
            this.audience = audience;
            this.scheduled = scheduled;
        }
    }
} 