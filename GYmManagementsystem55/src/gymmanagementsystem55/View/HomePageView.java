package gymmanagementsystem55.View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.BasicStroke;

public class HomePageView extends JFrame {
    private JButton getStartedButton;
    private JButton signInButton;
    private JLabel pricingLabel, aboutLabel, homeLabel, signInLabel;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public HomePageView() {
        setTitle("TANUVEDA Gym Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // Top Navigation
        JPanel navPanel = new JPanel(new BorderLayout());
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        // Insert gym logo image instead of 'GYM' text
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        logoPanel.setBackground(Color.WHITE);
        try {
            BufferedImage navLogoImg = null;
            java.net.URL navLogoUrl = getClass().getResource("/Imagepicker/image.png");
            if (navLogoUrl != null) {
                navLogoImg = ImageIO.read(navLogoUrl);
            } else {
                java.io.InputStream is = getClass().getResourceAsStream("/Imagepicker/image.png");
                if (is != null) navLogoImg = ImageIO.read(is);
            }
            if (navLogoImg != null) {
                int imgW = 48, imgH = 48;
                Image scaled = navLogoImg.getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
                JLabel imgLabel = new JLabel(new ImageIcon(scaled));
                logoPanel.add(imgLabel);
                logoPanel.add(Box.createHorizontalStrut(10));
            }
        } catch (IOException ex) {
            // fallback: do nothing
        }
        JLabel tanuvedaLabel = new JLabel("TANUVEDA");
        tanuvedaLabel.setFont(new Font("Montserrat", Font.BOLD, 22));
        tanuvedaLabel.setForeground(Color.BLACK);
        logoPanel.add(tanuvedaLabel);
        navPanel.add(logoPanel, BorderLayout.WEST);
        JPanel navLinks = new JPanel();
        navLinks.setBackground(Color.WHITE);
        navLinks.setLayout(new BoxLayout(navLinks, BoxLayout.X_AXIS));
        homeLabel = new JLabel("HOME");
        homeLabel.setFont(new Font("Montserrat", Font.BOLD, 16));
        homeLabel.setForeground(new Color(230, 57, 89));
        homeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navLinks.add(homeLabel);
        navLinks.add(Box.createHorizontalStrut(30));
        pricingLabel = new JLabel("PRICING");
        pricingLabel.setFont(new Font("Montserrat", Font.PLAIN, 16));
        pricingLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navLinks.add(pricingLabel);
        navLinks.add(Box.createHorizontalStrut(30));
        aboutLabel = new JLabel("ABOUT");
        aboutLabel.setFont(new Font("Montserrat", Font.PLAIN, 16));
        aboutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navLinks.add(aboutLabel);
        navLinks.add(Box.createHorizontalStrut(30));
        signInLabel = new JLabel("Sign In");
        signInLabel.setFont(new Font("Montserrat", Font.BOLD, 16));
        signInLabel.setForeground(new Color(230, 57, 89));
        signInLabel.setBorder(BorderFactory.createLineBorder(new Color(230, 57, 89), 2));
        signInLabel.setOpaque(true);
        signInLabel.setBackground(Color.WHITE);
        signInLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signInLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signInLabel.setPreferredSize(new Dimension(90, 36));
        navLinks.add(signInLabel);
        navPanel.add(navLinks, BorderLayout.EAST);
        add(navPanel, BorderLayout.NORTH);

        // Card Layout for main content
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(new Color(62, 74, 99));

        // HERO PANEL (scaled down and with real image)
        JPanel heroSection = new JPanel(new GridBagLayout());
        heroSection.setBackground(new Color(62, 74, 99));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(62, 74, 99));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(30, 60, 30, 15));
        leftPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        JLabel heroTitle = new JLabel("<html><div style='text-align:left;'><span style='font-size:38px;font-weight:bold;'>Train Insane<br>or Remain the Same</span></div></html>");
        heroTitle.setForeground(Color.WHITE);
        heroTitle.setFont(new Font("Montserrat", Font.BOLD, 38));
        heroTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(heroTitle);
        leftPanel.add(Box.createVerticalStrut(12));

        JLabel heroSubtitle = new JLabel("Master Your Body, Elevate Your Life");
        heroSubtitle.setForeground(new Color(160, 170, 190));
        heroSubtitle.setFont(new Font("Montserrat", Font.PLAIN, 17));
        heroSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(heroSubtitle);
        leftPanel.add(Box.createVerticalStrut(12));

        JLabel heroDesc = new JLabel("<html><div style='text-align:left;width:280px;'>Success in the gym isn't given—it's earned through sweat, discipline, and the relentless pursuit of becoming your strongest self</div></html>");
        heroDesc.setForeground(Color.WHITE);
        heroDesc.setFont(new Font("Montserrat", Font.PLAIN, 14));
        heroDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(heroDesc);
        leftPanel.add(Box.createVerticalStrut(20));

        // Insert gym logo image below hero text and above Get Started button
        try {
            BufferedImage heroLogoImg = null;
            java.net.URL heroLogoUrl = getClass().getResource("/gymmanagementsystem55/View/gym_logo_small.png");
            if (heroLogoUrl != null) {
                heroLogoImg = ImageIO.read(heroLogoUrl);
            } else {
                java.io.InputStream is = getClass().getResourceAsStream("/gymmanagementsystem55/View/gym_logo_small.png");
                if (is != null) heroLogoImg = ImageIO.read(is);
            }
            if (heroLogoImg != null) {
                int imgW = 80, imgH = 80;
                Image scaled = heroLogoImg.getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
                JLabel imgLabel = new JLabel(new ImageIcon(scaled));
                imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                leftPanel.add(Box.createVerticalStrut(18));
                leftPanel.add(imgLabel);
                leftPanel.add(Box.createVerticalStrut(18));
            }
        } catch (IOException ex) {
            // fallback: do nothing
        }

        getStartedButton = new JButton("Get Started  →");
        getStartedButton.setBackground(new Color(230, 57, 89));
        getStartedButton.setForeground(Color.WHITE);
        getStartedButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        getStartedButton.setFocusPainted(false);
        getStartedButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        getStartedButton.setBorder(BorderFactory.createEmptyBorder(10, 28, 10, 28));
        leftPanel.add(getStartedButton);

        // Right Panel: Real image with abstract background
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(230, 140, 120));
                g2.fillRoundRect(80, 40, 140, 70, 50, 50);
                g2.setColor(new Color(120, 170, 220));
                g2.fillRoundRect(120, 80, 200, 50, 30, 30);
                g2.setColor(new Color(255, 215, 0));
                g2.fillRoundRect(240, 170, 30, 10, 10, 10);
                g2.fillRoundRect(220, 200, 20, 8, 8, 8);
            }
        };
        rightPanel.setOpaque(false);
        rightPanel.setLayout(null);
        rightPanel.setPreferredSize(new Dimension(600, 420));

        // Load and scale the home image
        try {
            BufferedImage homeImg = null;
            java.net.URL homeImgUrl = getClass().getResource("/Imagepicker/homeimg.png");
            if (homeImgUrl != null) {
                homeImg = ImageIO.read(homeImgUrl);
            } else {
                java.io.InputStream is = getClass().getResourceAsStream("/Imagepicker/homeimg.png");
                if (is != null) homeImg = ImageIO.read(is);
            }
            if (homeImg != null) {
                int imgW = (int)(350 * 1.55), imgH = (int)(270 * 1.55);
                int shiftX = (int)(-0.10 * imgW);
                Image scaled = homeImg.getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
                JLabel imgLabel = new JLabel(new ImageIcon(scaled));
                imgLabel.setBounds(shiftX, 0, imgW, imgH);
                rightPanel.add(imgLabel);
            }
        } catch (IOException ex) {
            JLabel imgLabel = new JLabel("[Image not found]");
            imgLabel.setForeground(Color.WHITE);
            imgLabel.setBounds(30, 40, 350, 270);
            rightPanel.add(imgLabel);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        heroSection.add(leftPanel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.4;
        heroSection.add(rightPanel, gbc);

        // PRICING CARD
        JPanel pricingCard = new JPanel();
        pricingCard.setBackground(new Color(245, 247, 251));
        pricingCard.setLayout(new BoxLayout(pricingCard, BoxLayout.Y_AXIS));

        JLabel pricingHeader = new JLabel("SIMPLE, TRANSPARENT PRICING");
        pricingHeader.setFont(new Font("Montserrat", Font.BOLD, 36));
        pricingHeader.setForeground(new Color(40, 50, 70));
        pricingHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        pricingCard.add(Box.createVerticalStrut(20));
        pricingCard.add(pricingHeader);

        JLabel pricingSub = new JLabel("<html><div style='text-align:center;'>Choose a plan that fits your GYM needs<br>all plan include core features with no hidden fees</div></html>");
        pricingSub.setFont(new Font("Montserrat", Font.PLAIN, 20));
        pricingSub.setForeground(new Color(160, 170, 190));
        pricingSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        pricingSub.setHorizontalAlignment(SwingConstants.CENTER);
        pricingCard.add(Box.createVerticalStrut(10));
        pricingCard.add(pricingSub);
        pricingCard.add(Box.createVerticalStrut(30));

        JPanel plansPanel = new JPanel();
        plansPanel.setBackground(new Color(245, 247, 251));
        plansPanel.setLayout(new java.awt.GridLayout(1, 3, 30, 0));
        plansPanel.setBorder(new EmptyBorder(20, 40, 40, 40));

        // BASIC PLAN
        JPanel basicPanel = new JPanel();
        basicPanel.setBackground(new Color(252, 254, 255));
        basicPanel.setBorder(BorderFactory.createDashedBorder(new Color(120, 140, 170), 2, 6));
        basicPanel.setLayout(new BoxLayout(basicPanel, BoxLayout.Y_AXIS));
        basicPanel.setPreferredSize(new Dimension(300, 420));
        JLabel basicTitle = new JLabel("BASIC");
        basicTitle.setFont(new Font("Montserrat", Font.BOLD, 28));
        basicTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel basicDesc = new JLabel("Perfect for newcomers and beginers!!");
        basicDesc.setFont(new Font("Montserrat", Font.PLAIN, 15));
        basicDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel basicPrice = new JLabel("NPR 3500");
        basicPrice.setFont(new Font("Montserrat", Font.BOLD, 32));
        basicPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel basicPer = new JLabel("/PER MONTH");
        basicPer.setFont(new Font("Montserrat", Font.PLAIN, 14));
        basicPer.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel basicList = new JPanel();
        basicList.setBackground(new Color(252, 254, 255));
        basicList.setLayout(new BoxLayout(basicList, BoxLayout.Y_AXIS));
        basicList.add(new JLabel("• Full GYM access"));
        basicList.add(new JLabel("• Free induction session"));
        basicList.add(new JLabel("• Locker facility"));
        for (Component c : basicList.getComponents()) {
            c.setFont(new Font("Montserrat", Font.PLAIN, 15));
            ((JLabel)c).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        JButton basicBtn = new JButton("Get Started");
        basicBtn.setBackground(new Color(30, 38, 54));
        basicBtn.setForeground(Color.WHITE);
        basicBtn.setFont(new Font("Montserrat", Font.BOLD, 18));
        basicBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        basicBtn.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        basicBtn.addActionListener(e -> showEsewaPopup());
        basicPanel.add(Box.createVerticalStrut(18));
        basicPanel.add(basicTitle);
        basicPanel.add(Box.createVerticalStrut(8));
        basicPanel.add(basicDesc);
        basicPanel.add(Box.createVerticalStrut(18));
        basicPanel.add(basicPrice);
        basicPanel.add(basicPer);
        basicPanel.add(Box.createVerticalStrut(18));
        basicPanel.add(basicList);
        basicPanel.add(Box.createVerticalStrut(18));
        basicPanel.add(basicBtn);

        // STANDARD PLAN
        JPanel standardPanel = new JPanel();
        standardPanel.setBackground(new Color(255, 245, 247));
        standardPanel.setBorder(BorderFactory.createDashedBorder(new Color(230, 57, 89), 2, 6));
        standardPanel.setLayout(new BoxLayout(standardPanel, BoxLayout.Y_AXIS));
        standardPanel.setPreferredSize(new Dimension(300, 420));
        JPanel mostPopular = new JPanel();
        mostPopular.setBackground(new Color(230, 57, 89));
        JLabel mostPopularLabel = new JLabel("Most Popular");
        mostPopularLabel.setFont(new Font("Montserrat", Font.BOLD, 15));
        mostPopularLabel.setForeground(Color.WHITE);
        mostPopular.add(mostPopularLabel);
        mostPopular.setAlignmentX(Component.CENTER_ALIGNMENT);
        mostPopular.setMaximumSize(new Dimension(140, 28));
        standardPanel.add(Box.createVerticalStrut(8));
        standardPanel.add(mostPopular);
        JLabel standardTitle = new JLabel("STANDARD");
        standardTitle.setFont(new Font("Montserrat", Font.BOLD, 28));
        standardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel standardDesc = new JLabel("Ideal for  regular fitness enthusiasts");
        standardDesc.setFont(new Font("Montserrat", Font.PLAIN, 15));
        standardDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel standardPrice = new JLabel("NPR 5000");
        standardPrice.setFont(new Font("Montserrat", Font.BOLD, 32));
        standardPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel standardPer = new JLabel("/PER MONTH");
        standardPer.setFont(new Font("Montserrat", Font.PLAIN, 14));
        standardPer.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel standardList = new JPanel();
        standardList.setBackground(new Color(255, 245, 247));
        standardList.setLayout(new BoxLayout(standardList, BoxLayout.Y_AXIS));
        standardList.add(new JLabel("• Everything in BASIC"));
        standardList.add(new JLabel("• 2 Group Classes / week"));
        standardList.add(new JLabel("• Diet Consultation"));
        for (Component c : standardList.getComponents()) {
            c.setFont(new Font("Montserrat", Font.PLAIN, 15));
            ((JLabel)c).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        JButton standardBtn = new JButton("Get Started");
        standardBtn.setBackground(new Color(230, 57, 89));
        standardBtn.setForeground(Color.WHITE);
        standardBtn.setFont(new Font("Montserrat", Font.BOLD, 18));
        standardBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        standardBtn.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        standardBtn.addActionListener(e -> showEsewaPopup());
        standardPanel.add(Box.createVerticalStrut(8));
        standardPanel.add(standardTitle);
        standardPanel.add(Box.createVerticalStrut(8));
        standardPanel.add(standardDesc);
        standardPanel.add(Box.createVerticalStrut(18));
        standardPanel.add(standardPrice);
        standardPanel.add(standardPer);
        standardPanel.add(Box.createVerticalStrut(18));
        standardPanel.add(standardList);
        standardPanel.add(Box.createVerticalStrut(18));
        standardPanel.add(standardBtn);

        // PREMIUM PLAN
        JPanel premiumPanel = new JPanel();
        premiumPanel.setBackground(new Color(252, 254, 255));
        premiumPanel.setBorder(BorderFactory.createDashedBorder(new Color(120, 140, 170), 2, 6));
        premiumPanel.setLayout(new BoxLayout(premiumPanel, BoxLayout.Y_AXIS));
        premiumPanel.setPreferredSize(new Dimension(300, 420));
        JLabel premiumTitle = new JLabel("PREMIUM");
        premiumTitle.setFont(new Font("Montserrat", Font.BOLD, 28));
        premiumTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel premiumDesc = new JLabel("Ideal for  regular fitness enthusiasts");
        premiumDesc.setFont(new Font("Montserrat", Font.PLAIN, 15));
        premiumDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel premiumPrice = new JLabel("NPR 7000");
        premiumPrice.setFont(new Font("Montserrat", Font.BOLD, 32));
        premiumPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel premiumPer = new JLabel("/PER MONTH");
        premiumPer.setFont(new Font("Montserrat", Font.PLAIN, 14));
        premiumPer.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel premiumList = new JPanel();
        premiumList.setBackground(new Color(252, 254, 255));
        premiumList.setLayout(new BoxLayout(premiumList, BoxLayout.Y_AXIS));
        premiumList.add(new JLabel("• Everything in STANDARD"));
        premiumList.add(new JLabel("• Unlimited Classes"));
        premiumList.add(new JLabel("• Personal Trainer/Sauna"));
        for (Component c : premiumList.getComponents()) {
            c.setFont(new Font("Montserrat", Font.PLAIN, 15));
            ((JLabel)c).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        JButton premiumBtn = new JButton("Get Started");
        premiumBtn.setBackground(new Color(30, 38, 54));
        premiumBtn.setForeground(Color.WHITE);
        premiumBtn.setFont(new Font("Montserrat", Font.BOLD, 18));
        premiumBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        premiumBtn.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        premiumBtn.addActionListener(e -> showEsewaPopup());
        premiumPanel.add(Box.createVerticalStrut(18));
        premiumPanel.add(premiumTitle);
        premiumPanel.add(Box.createVerticalStrut(8));
        premiumPanel.add(premiumDesc);
        premiumPanel.add(Box.createVerticalStrut(18));
        premiumPanel.add(premiumPrice);
        premiumPanel.add(premiumPer);
        premiumPanel.add(Box.createVerticalStrut(18));
        premiumPanel.add(premiumList);
        premiumPanel.add(Box.createVerticalStrut(18));
        premiumPanel.add(premiumBtn);

        plansPanel.add(basicPanel);
        plansPanel.add(standardPanel);
        plansPanel.add(premiumPanel);
        pricingCard.add(plansPanel);

        // ABOUT CARD
        JPanel aboutCard = new JPanel();
        aboutCard.setBackground(new Color(245, 247, 251));
        aboutCard.setLayout(new BoxLayout(aboutCard, BoxLayout.Y_AXIS));

        JLabel aboutHeader = new JLabel("About Tanuveda");
        aboutHeader.setFont(new Font("Montserrat", Font.BOLD, 36));
        aboutHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutCard.add(Box.createVerticalStrut(20));
        aboutCard.add(aboutHeader);

        JLabel aboutSub = new JLabel("Master Your Body, Elevate Your Life");
        aboutSub.setFont(new Font("Montserrat", Font.PLAIN, 24));
        aboutSub.setForeground(new Color(90, 110, 140));
        aboutSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutCard.add(Box.createVerticalStrut(10));
        aboutCard.add(aboutSub);
        aboutCard.add(Box.createVerticalStrut(30));

        JPanel aboutMainPanel = new JPanel(new java.awt.GridBagLayout());
        aboutMainPanel.setBackground(Color.WHITE);
        aboutMainPanel.setBorder(new EmptyBorder(40, 100, 40, 60));
        java.awt.GridBagConstraints gbcAbout = new java.awt.GridBagConstraints();
        gbcAbout.insets = new Insets(0, 0, 0, 0);
        gbcAbout.fill = java.awt.GridBagConstraints.BOTH;
        gbcAbout.weighty = 1;

        // Left: Story
        JPanel storyPanel = new JPanel();
        storyPanel.setBackground(Color.WHITE);
        storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.Y_AXIS));
        JLabel storyTitle = new JLabel("Our Story");
        storyTitle.setFont(new Font("Montserrat", Font.BOLD, 32));
        storyTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        storyPanel.add(Box.createVerticalStrut(30));
        storyPanel.add(storyTitle);
        storyPanel.add(Box.createVerticalStrut(20));
        JLabel storyText = new JLabel("<html><div style='width:420px;font-size:20px;text-align:left;'>I started this gym to give others what fitness gave me—a second chance to grow, focus, and level up in life. This isn't just a gym; it's a place to push limits and find your strength.<br><br>— Khatiwada and team, Founder</div></html>");
        storyText.setFont(new Font("Montserrat", Font.PLAIN, 20));
        storyText.setAlignmentX(Component.LEFT_ALIGNMENT);
        storyPanel.add(storyText);

        // Right: Illustration on abstract shape (centered and improved proportions)
        JPanel aboutLogoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Abstract shape
                g2.setColor(new Color(196, 128, 110));
                g2.fillRoundRect(0, 0, 340, 340, 140, 140);
                // Man in suit (centered)
                int cx = 170, cy = 170;
                // Head
                g2.setColor(new Color(220, 190, 160));
                g2.fillOval(cx-38, cy-90, 76, 76);
                // Body (suit)
                g2.setColor(new Color(40, 40, 60));
                int[] xBody = {cx-50, cx, cx+50};
                int[] yBody = {cy-10, cy+90, cy-10};
                g2.fillPolygon(xBody, yBody, 3);
                // Shirt
                g2.setColor(new Color(240, 240, 245));
                int[] xShirt = {cx-16, cx, cx+16};
                int[] yShirt = {cy+20, cy+80, cy+20};
                g2.fillPolygon(xShirt, yShirt, 3);
                // Tie
                g2.setColor(new Color(230, 57, 89));
                int[] xTie = {cx-3, cx, cx+3};
                int[] yTie = {cy+30, cy+75, cy+30};
                g2.fillPolygon(xTie, yTie, 3);
                // Arms (suit sleeves)
                g2.setStroke(new BasicStroke(14f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.setColor(new Color(40, 40, 60));
                g2.drawLine(cx-50, cy+10, cx-110, cy+60);
                g2.drawLine(cx+50, cy+10, cx+110, cy+60);
                // Hands
                g2.setStroke(new BasicStroke(16f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.setColor(new Color(220, 190, 160));
                g2.drawLine(cx-110, cy+60, cx-110, cy+60);
                g2.drawLine(cx+110, cy+60, cx+110, cy+60);
                // Simple face (eyes, mouth)
                g2.setColor(new Color(80, 60, 40));
                g2.fillOval(cx-16, cy-68, 8, 8);
                g2.fillOval(cx+8, cy-68, 8, 8);
                g2.setStroke(new BasicStroke(2.5f));
                g2.drawArc(cx-8, cy-52, 16, 8, 190, 160);
            }
        };
        aboutLogoPanel.setOpaque(false);
        aboutLogoPanel.setLayout(null);
        aboutLogoPanel.setPreferredSize(new Dimension(340, 340));

        gbcAbout.gridx = 0;
        gbcAbout.gridy = 0;
        gbcAbout.weightx = 0.6;
        gbcAbout.anchor = java.awt.GridBagConstraints.NORTHWEST;
        aboutMainPanel.add(storyPanel, gbcAbout);
        gbcAbout.gridx = 1;
        gbcAbout.weightx = 0.4;
        gbcAbout.anchor = java.awt.GridBagConstraints.CENTER;
        aboutMainPanel.add(aboutLogoPanel, gbcAbout);
        aboutCard.add(aboutMainPanel);

        // SIGN IN CARD
        JPanel signInCard = new JPanel();
        signInCard.setBackground(new Color(245, 245, 250));
        signInCard.setBorder(new EmptyBorder(60, 60, 60, 60));
        signInCard.setLayout(new BoxLayout(signInCard, BoxLayout.Y_AXIS));
        JLabel signInTitle = new JLabel("Sign In");
        signInTitle.setFont(new Font("Montserrat", Font.BOLD, 32));
        signInTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInCard.add(signInTitle);
        signInCard.add(Box.createVerticalStrut(20));
        JLabel signInDesc = new JLabel("<html><div style='text-align:center;width:400px;'>Sign in to access your account. (Placeholder)</div></html>");
        signInDesc.setFont(new Font("Montserrat", Font.PLAIN, 18));
        signInDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInCard.add(signInDesc);

        cardPanel.add(heroSection, "HOME");
        cardPanel.add(pricingCard, "PRICING");
        cardPanel.add(aboutCard, "ABOUT");
        cardPanel.add(signInCard, "SIGNIN");
        add(cardPanel, BorderLayout.CENTER);

        // Navigation click listeners
        homeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "HOME");
                homeLabel.setForeground(new Color(230, 57, 89));
                pricingLabel.setForeground(Color.BLACK);
                aboutLabel.setForeground(Color.BLACK);
                signInLabel.setForeground(new Color(230, 57, 89));
            }
        });
        pricingLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "PRICING");
                homeLabel.setForeground(Color.BLACK);
                pricingLabel.setForeground(new Color(230, 57, 89));
                aboutLabel.setForeground(Color.BLACK);
                signInLabel.setForeground(new Color(230, 57, 89));
            }
        });
        aboutLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "ABOUT");
                homeLabel.setForeground(Color.BLACK);
                pricingLabel.setForeground(Color.BLACK);
                aboutLabel.setForeground(new Color(230, 57, 89));
                signInLabel.setForeground(new Color(230, 57, 89));
            }
        });
        signInLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "SIGNIN");
                homeLabel.setForeground(Color.BLACK);
                pricingLabel.setForeground(Color.BLACK);
                aboutLabel.setForeground(Color.BLACK);
                signInLabel.setForeground(new Color(230, 57, 89));
            }
        });

        // Make Get Started button open Sign In card
        getStartedButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "SIGNIN");
            homeLabel.setForeground(Color.BLACK);
            pricingLabel.setForeground(Color.BLACK);
            aboutLabel.setForeground(Color.BLACK);
            signInLabel.setForeground(new Color(230, 57, 89));
        });
    }

    public JButton getGetStartedButton() { return getStartedButton; }
    public JButton getSignInButton() { return signInButton; }

    // Helper method to show Esewa.jpg in a popup
    private void showEsewaPopup() {
        try {
            BufferedImage esewaImg = null;
            java.net.URL esewaUrl = getClass().getResource("/Imagepicker/Esewa.jpg");
            if (esewaUrl != null) {
                esewaImg = ImageIO.read(esewaUrl);
            } else {
                java.io.InputStream is = getClass().getResourceAsStream("/Imagepicker/Esewa.jpg");
                if (is != null) esewaImg = ImageIO.read(is);
            }
            if (esewaImg != null) {
                int width = (int)(751 * 0.65); // 488
                int height = (int)(1363 * 0.65); // 886
                Image scaled = esewaImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                JLabel imgLabel = new JLabel(new ImageIcon(scaled));
                JOptionPane.showMessageDialog(this, imgLabel, "Esewa Payment", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Esewa.jpg not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load Esewa.jpg!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
} 