package view;

import controller.CartController;
import controller.NotificationController;
import controller.ProductController;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import model.Activity;
import model.Attendance;
import model.Product;

public class MainView extends JFrame {
    private JPanel panel;
    private ProductController productController;
    private CartController cartController;
    private JPanel calendarPanel;
    private JLabel monthLabel;
    private YearMonth currentMonth = YearMonth.now();
    private List<Activity> activities = new ArrayList<>();
    private Attendance attendance = new Attendance();
    private JLabel streakLabel;

    public MainView(ProductController controller) {
        this.productController = controller;
        this.cartController = new CartController();

        setTitle("🏋️ Gym Product Purchase");
        setSize(850, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(panel);

        JButton viewCartBtn = new JButton("🛒 View Cart");
        viewCartBtn.setPreferredSize(new Dimension(100, 30));
        viewCartBtn.addActionListener(e -> cartController.showCartView());

        JButton notifyBtn = new JButton("🔔 Send Notification");
        notifyBtn.setPreferredSize(new Dimension(160, 30));
        notifyBtn.addActionListener(e -> {
            NotificationController.getInstance().showNotificationView();
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(viewCartBtn);
        topPanel.add(notifyBtn);

        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Calendar panel setup
        calendarPanel = new JPanel();
        monthLabel = new JLabel();
        JButton prevBtn = new JButton("<");
        JButton nextBtn = new JButton("> ");
        prevBtn.addActionListener(e -> {
            currentMonth = currentMonth.minusMonths(1);
            updateCalendar();
        });
        nextBtn.addActionListener(e -> {
            currentMonth = currentMonth.plusMonths(1);
            updateCalendar();
        });
        JPanel calHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
        calHeader.add(prevBtn);
        calHeader.add(monthLabel);
        calHeader.add(nextBtn);
        add(calHeader, BorderLayout.SOUTH);
        add(calendarPanel, BorderLayout.WEST);

        // Example activities (replace with real data source)
        activities.add(new Activity(LocalDate.of(2025, 4, 10), "Yoga Class", "Morning yoga at 7am"));
        activities.add(new Activity(LocalDate.of(2025, 4, 15), "Trainer Session", "PT with Alex at 5pm"));
        updateCalendar();

        streakLabel = new JLabel();
        JButton checkInBtn = new JButton("Check in Now");
        checkInBtn.addActionListener(e -> {
            if (attendance.hasCheckedInToday()) {
                JOptionPane.showMessageDialog(this, "You have already checked in today!", "Check-in", JOptionPane.WARNING_MESSAGE);
            } else {
                attendance.checkIn(LocalDate.now());
                JOptionPane.showMessageDialog(this, "Check-in successful!", "Check-in", JOptionPane.INFORMATION_MESSAGE);
                updateStreak();
            }
        });
        updateStreak();
        topPanel.add(checkInBtn);
        topPanel.add(streakLabel);
    }

    public void displayProducts(List<Product> products) {
        panel.removeAll();
        for (Product p : products) {
            JPanel prodPanel = new JPanel(new BorderLayout());
            prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            prodPanel.setPreferredSize(new Dimension(600, 60));

            JLabel label = new JLabel(p.getName() + " - NPR " + p.getPrice());
            label.setFont(new Font("SansSerif", Font.BOLD, 14));
            label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

            JPanel rightPanel = new JPanel(new FlowLayout());

            SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner spinner = new JSpinner(model);

            JButton btn = new JButton(p.getStock() > 0 ? "Add to Cart" : "Out of Stock");
            btn.setEnabled(p.getStock() > 0);
            btn.addActionListener(e -> {
                int quantity = (int) spinner.getValue();
                cartController.addToCart(p, quantity);
                JOptionPane.showMessageDialog(this,
                        quantity + " x " + p.getName() + " added to cart!",
                        "Added", JOptionPane.INFORMATION_MESSAGE);
            });

            rightPanel.add(new JLabel("Qty:"));
            rightPanel.add(spinner);
            rightPanel.add(btn);

            prodPanel.add(label, BorderLayout.WEST);
            prodPanel.add(rightPanel, BorderLayout.EAST);
            panel.add(prodPanel);
        }
        panel.revalidate();
        panel.repaint();
    }

    private void updateCalendar() {
        calendarPanel.removeAll();
        calendarPanel.setLayout(new GridLayout(0, 7));
        monthLabel.setText(currentMonth.getMonth().toString() + " " + currentMonth.getYear());
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String d : days) calendarPanel.add(new JLabel(d, SwingConstants.CENTER));
        LocalDate first = currentMonth.atDay(1);
        int start = first.getDayOfWeek().getValue() % 7;
        int length = currentMonth.lengthOfMonth();
        for (int i = 0; i < start; i++) calendarPanel.add(new JLabel(""));
        for (int day = 1; day <= length; day++) {
            LocalDate date = currentMonth.atDay(day);
            JButton dayBtn = new JButton(String.valueOf(day));
            Activity act = activities.stream().filter(a -> a.getDate().equals(date)).findFirst().orElse(null);
            if (act != null) {
                dayBtn.setBackground(Color.YELLOW);
                dayBtn.setToolTipText("Activity: " + act.getTitle() + "\n" + act.getDetails());
            }
            calendarPanel.add(dayBtn);
        }
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private void updateStreak() {
        int streak = attendance.getCurrentStreak();
        streakLabel.setText("Current Streak: " + streak + " days");
    }
}
