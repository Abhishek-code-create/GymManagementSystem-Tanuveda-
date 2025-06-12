package gymmanagementsystem55.controller;

import gymmanagementsystem55.View.HomePageView;
import javax.swing.*;
import java.awt.event.*;

public class HomePageController {
    private HomePageView view;

    public HomePageController(HomePageView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getGetStartedButton().addActionListener(e -> onGetStarted());
        view.getSignInButton().addActionListener(e -> onSignIn());
    }

    private void onGetStarted() {
        JOptionPane.showMessageDialog(view, "Get Started Clicked!");
    }

    private void onSignIn() {
        JOptionPane.showMessageDialog(view, "Sign In Clicked!");
    }
} 