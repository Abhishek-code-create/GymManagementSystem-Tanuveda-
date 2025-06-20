package view;

import model.Product;
import model.Cart;
import model.Order;
import controller.ProductController;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductView extends JFrame {
    JPanel panel = new JPanel();
    JTextArea cartArea = new JTextArea(5, 40);
    ProductController controller;

    public ProductView(ProductController controller) {
        this.controller = controller;
        setTitle("Gym Product Store");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(new JScrollPane(panel), BorderLayout.CENTER);

        JButton checkoutBtn = new JButton("Checkout");
        checkoutBtn.addActionListener(e -> showConfirmation());
        add(checkoutBtn, BorderLayout.SOUTH);

        add(cartArea, BorderLayout.NORTH);
        setVisible(true);
    }

    public void displayProducts(List<Product> products) {
        panel.removeAll();
        for (Product p : products) {
            JPanel row = new JPanel(new FlowLayout());
            row.add(new JLabel(p.getName() + " (NPR " + p.getPrice() + ")"));

            if (p.isInStock()) {
                JButton buyBtn = new JButton("Buy Now");
                JButton addToCartBtn = new JButton("Add to Cart");

                buyBtn.addActionListener(e -> buyNow(p));
                addToCartBtn.addActionListener(e -> addToCart(p));

                row.add(buyBtn);
                row.add(addToCartBtn);
            } else {
                row.add(new JLabel("Out of Stock"));
            }
            panel.add(row);
        }
        panel.revalidate();
        panel.repaint();
    }

    private void addToCart(Product product) {
        controller.addToCart(product);
        updateCartArea();
    }

    private void buyNow(Product product) {
        controller.addToCart(product);
        Order order = controller.checkout();
        showOrderConfirmation(order);
        updateCartArea();
        controller.showProducts();
    }

    private void showConfirmation() {
        Order order = controller.checkout();
        showOrderConfirmation(order);
        updateCartArea();
        controller.showProducts();
    }

    private void showOrderConfirmation(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Confirmed!\n");
        sb.append("Items:\n");
        for (var entry : order.getItems().entrySet()) {
            sb.append(entry.getKey().getName())
              .append(" x ")
              .append(entry.getValue())
              .append("\n");
        }
        sb.append("Total: NPR ").append(order.getTotal()).append("\n");
        sb.append("Estimated Delivery: ").append(order.getDeliveryDate());
        JOptionPane.showMessageDialog(this, sb.toString(), "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateCartArea() {
        Cart cart = controller.getCart();
        StringBuilder sb = new StringBuilder();
        sb.append("Cart:\n");
        for (var entry : cart.getItems().entrySet()) {
            sb.append(entry.getKey().getName())
              .append(" x ")
              .append(entry.getValue())
              .append("\n");
        }
        sb.append("Total: NPR ").append(cart.getTotal());
        cartArea.setText(sb.toString());
    }
}
