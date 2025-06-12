/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gymmanagementsystem55;

import java.awt.image.BufferedImage;

/**
 *
 * @author acer
 */
public class GYmManagementsystem55 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            gymmanagementsystem55.View.HomePageView view = new gymmanagementsystem55.View.HomePageView();
            view.setVisible(true);
        });
    }
    
    BufferedImage heroImg = null;
    java.net.URL imgUrl = getClass().getResource("/gymmanagementsystem55/View/hero_tablet.png");
}
