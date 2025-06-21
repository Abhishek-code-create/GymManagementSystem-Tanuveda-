package gymmanagementsystem.controller;

import gymmanagementsystem.view.HomePageView;
import gymmanagementsystem.view.LoginSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class HomePageController {
    private HomePageView view;

    public HomePageController(HomePageView view) {
        this.view = view;
        this.view.signInLabelNav(new LoginNavigation());
            }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    class LoginNavigation implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
             LoginSystemView loginView= new LoginSystemView();
            LoginSystemController loginC= new LoginSystemController(loginView);
            loginC.open();
            close();
            }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        
        
    }
   

    private void onGetStarted() {
        LoginSystemView loginView = new LoginSystemView();
        loginView.setLocationRelativeTo(null);
        loginView.setVisible(true);
        view.dispose();
    }
    
    private void onSignIn() {
        LoginSystemView loginView = new LoginSystemView();
        loginView.setLocationRelativeTo(null);
        loginView.setVisible(true);
        view.dispose();
    }
} 