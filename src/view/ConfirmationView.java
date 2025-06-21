package view;

import model.CartItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ConfirmationView extends JFrame {
    public ConfirmationView(List<CartItem> items, double totalPrice) {
        setTitle("✅ Order Confirmation");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        for (CartItem item : items) {
            textArea.append(item.getProduct().getName() + " x" + item.getQuantity() + "\n");
        }

        textArea.append("\nTotal Amount: NPR " + totalPrice + "\n");
        textArea.append("📦 Estimated Delivery: 3-5 Business Days\n");

        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
}
