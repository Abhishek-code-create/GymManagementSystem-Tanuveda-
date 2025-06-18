/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmangementsystem23.Controller;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ASUS
 */

    
import javax.swing.*;
import java.awt.*;

public class NotificationController extends JPanel {
    public NotificationController(String Username, String message, String time, ImageIcon avatarIcon) {
        setLayout(new BorderLayout(10, 0));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        // Profile Image
        JLabel avatar = new JLabel();
        if (avatarIcon != null) {
            Image img = avatarIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            avatar.setIcon(new ImageIcon(img));
        }
        avatar.setPreferredSize(new Dimension(50, 50));

        // Name & Message
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));
        textPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(Username);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 13));

        JLabel msgLabel = new JLabel(message);
        msgLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        msgLabel.setForeground(Color.GRAY);

        textPanel.add(nameLabel);
        textPanel.add(msgLabel);

        // Time Label
        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        timeLabel.setForeground(Color.GRAY);

        // Add to main panel
        add(avatar, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
        add(timeLabel, BorderLayout.EAST);
    }
}

