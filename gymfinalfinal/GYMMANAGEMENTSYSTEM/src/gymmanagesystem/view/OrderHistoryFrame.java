package gymmanagesystem.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OrderHistoryFrame extends JFrame {
    public OrderHistoryFrame(List<Object[]> purchases) {
        setTitle("Order History");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columns = {"Order ID", "Product ID", "Product Name", "Price", "Purchase Time"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Object[] row : purchases) {
            model.addRow(row);
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener((ActionEvent e) -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
} 