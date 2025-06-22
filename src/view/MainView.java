package view;

import controller.CartController;
import controller.NotificationController;
import controller.ProductController;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Product;

public class MainView extends JFrame {
    private JPanel panel;
    private ProductController productController;
    private CartController cartController;

    public MainView(ProductController controller) {
        this.productController = controller;
        this.cartController = new CartController();

        setTitle("🏋️ Gym Product Purchase");
        setSize(650, 500);
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
}
