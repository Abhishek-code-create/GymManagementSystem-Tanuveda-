package gymmanagesystem.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import gymmanagesystem.Dao.NotificationDAO;

public class NotificationShower extends JDialog {
    private JPanel notificationPanel;

    public NotificationShower(JFrame parent) {
        super(parent, false);
        setUndecorated(true);
        setSize(400, 540);
        setLayout(new BorderLayout());
        int x = parent.getX() + parent.getWidth() - 440;
        int y = parent.getY() + 150;
        setLocation(x, y);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Drop shadow
                g2.setColor(new Color(0,0,0,40));
                g2.fillRoundRect(6, 6, getWidth()-12, getHeight()-12, 44, 44);
                // Main background
                g2.setColor(new Color(153, 163, 175));
                g2.fillRoundRect(0, 0, getWidth()-12, getHeight()-12, 40, 40);
                // Border
                g2.setColor(new Color(120, 130, 145));
                g2.setStroke(new BasicStroke(2f));
                g2.drawRoundRect(0, 0, getWidth()-12, getHeight()-12, 40, 40);
            }
        };
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mainPanel.setPreferredSize(new Dimension(400, 540));

        // Title bar with close button
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setOpaque(false);
        JLabel title = new JLabel("Notifications");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 0));
        JButton closeBtn = new JButton("✕");
        closeBtn.setFont(new Font("Arial", Font.BOLD, 18));
        closeBtn.setBackground(new Color(153, 163, 175));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        closeBtn.setFocusPainted(false);
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> dispose());
        titleBar.add(title, BorderLayout.WEST);
        titleBar.add(closeBtn, BorderLayout.EAST);
        mainPanel.add(titleBar, BorderLayout.NORTH);

        // Top toggle
        JPanel togglePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        togglePanel.setOpaque(false);
        JButton allBtn = new JButton("ALL | RECENT");
        allBtn.setFocusPainted(false);
        allBtn.setBackground(new Color(180, 190, 200));
        allBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        allBtn.setBorder(BorderFactory.createEmptyBorder(8, 30, 8, 30));
        allBtn.setForeground(Color.WHITE);
        togglePanel.add(allBtn);
        mainPanel.add(togglePanel, BorderLayout.BEFORE_FIRST_LINE);

        // Notification cards
        notificationPanel = new JPanel();
        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
        notificationPanel.setOpaque(false);
        JScrollPane scroll = new JScrollPane(notificationPanel);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scroll, BorderLayout.CENTER);

        // Bottom panel with CLEAR ALL and Back to Dashboard
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 16));
        bottomPanel.setOpaque(false);
        JButton clearAll = new JButton("CLEAR ALL");
        clearAll.setFont(new Font("Segoe UI", Font.BOLD, 18));
        clearAll.setBackground(Color.BLACK);
        clearAll.setForeground(Color.WHITE);
        clearAll.setFocusPainted(false);
        clearAll.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        clearAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearAll.addActionListener((ActionEvent e) -> {
            NotificationDAO dao = new NotificationDAO();
            dao.clearAllNotifications();
            updateNotifications();
        });
        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backBtn.setBackground(new Color(51, 153, 255));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener((ActionEvent e) -> {
            setVisible(false);
            dispose();
        });
        bottomPanel.add(clearAll);
        bottomPanel.add(backBtn);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        updateNotifications();
    }

    private void updateNotifications() {
        notificationPanel.removeAll();
        NotificationDAO dao = new NotificationDAO();
        List<Object[]> notifs = dao.getAllNotifications();
        for (Object[] n : notifs) {
            JPanel card = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(Color.WHITE);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                }
            };
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setOpaque(false);
            card.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
            card.setMaximumSize(new Dimension(340, 80));
            card.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel title = new JLabel(n[1].toString());
            title.setFont(new Font("Segoe UI", Font.BOLD, 15));
            title.setForeground(Color.BLACK);
            JLabel msg = new JLabel("<html><body style='width:300px'>" + n[2].toString() + "</body></html>");
            msg.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            msg.setForeground(Color.DARK_GRAY);
            card.add(title);
            card.add(Box.createVerticalStrut(4));
            card.add(msg);

            notificationPanel.add(card);
            notificationPanel.add(Box.createVerticalStrut(16));
        }
        notificationPanel.revalidate();
        notificationPanel.repaint();
    }
} 