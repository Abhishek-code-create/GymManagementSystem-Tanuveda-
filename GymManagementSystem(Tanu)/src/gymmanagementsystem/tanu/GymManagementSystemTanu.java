/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gymmanagementsystem.tanu;

import gymmanagementsystem.controller.HomePageController;
import gymmanagementsystem.view.HomePageView;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author DELL
 */
public class GymManagementSystemTanu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Set the look and feel to Nimbus
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            HomePageView homePageView = new HomePageView();
            HomePageController homePageController = new HomePageController(homePageView);
            homePageView.setVisible(true);
        });
    }
    
}
