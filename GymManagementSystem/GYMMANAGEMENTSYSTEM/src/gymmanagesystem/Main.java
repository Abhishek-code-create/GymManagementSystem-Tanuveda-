package gymmanagesystem;

import gymmanagesystem.controller.HomePageController;
import gymmanagesystem.view.HomePageView;
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