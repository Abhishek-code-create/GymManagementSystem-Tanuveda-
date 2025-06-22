package view;

import controller.CartController;
import model.CartItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CartView extends JFrame {
    private CartController controller;
    private JPanel panel;

    public CartView(CartController controller) {
        this.controller = controller;
        setTitle("🛒 Your Cart");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(new JScrollPane(panel), BorderLayout.CENTER);

        JButton checkoutBtn = new JButton("✔ Checkout");
        checkoutBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        checkoutBtn.addActionListener(e -> controller.checkout());

        JPanel bottom = new JPanel();
        bottom.add(checkoutBtn);
        add(bottom, BorderLayout.SOUTH);
    }

    public void displayCart(List<CartItem> items) {
        panel.removeAll();
        for (CartItem item : items) {
            JPanel row = new JPanel(new BorderLayout());
            JLabel label = new JLabel(item.getProduct().getName() +
                    " x" + item.getQuantity() +
                    " = NPR " + item.getTotalPrice());
            JButton removeBtn = new JButton("❌ Remove");
            removeBtn.addActionListener(e -> controller.removeFromCart(item.getProduct()));
            row.add(label, BorderLayout.CENTER);
            row.add(removeBtn, BorderLayout.EAST);
            row.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            panel.add(row);
        }
        panel.add(new JLabel("Total: NPR " + controller.getTotalPrice()));
        panel.revalidate();
        panel.repaint();
    }
}
