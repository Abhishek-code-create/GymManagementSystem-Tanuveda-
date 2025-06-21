package gymmanagesystem;

import gymmanagementsystem.controller.HomePageController;
import gymmanagementsystem.view.HomePageView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePageView homePageView = new HomePageView();
            new HomePageController(homePageView);
            homePageView.setVisible(true);
        });
    }
} 