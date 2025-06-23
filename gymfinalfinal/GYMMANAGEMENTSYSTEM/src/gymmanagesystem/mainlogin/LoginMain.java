/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagesystem.mainlogin;

import gymmanagesystem.view.LoginSystemView;
import gymmanagesystem.controller.LoginSystemController;
/**
 *
 * @author ACER
 */
public class LoginMain {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginSystemView view = new LoginSystemView();
            new LoginSystemController(view);
            view.setVisible(true); // Show login window
        });
    }
    
    
}
