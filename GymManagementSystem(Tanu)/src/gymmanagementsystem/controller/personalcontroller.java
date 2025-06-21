/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagementsystem.controller;

import gymmanagementsystem.model.personaldata;
import gymmanagementsystem.view.PersonalInformation;
import gymmanagementsystem.view.DashBoardView;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

/**
 * Controller for Personal Information management
 */
public class personalcontroller {

    private personaldata model;
    private PersonalInformation view;

    public personalcontroller(personaldata model, PersonalInformation view) {
        this.model = model;
        this.view = view;
        initializeView();
    }

    // Initialize the view with current data
    private void initializeView() {
        if (model != null) {
            view.setFullName(model.getFullName());
            view.setEmail(model.getEmail());
            view.setPhone(model.getPhone());
            view.setDob(model.getDob());
            view.setGender(model.getGender());
            view.setAddress(model.getAddress());
            view.setEmergencyContact(model.getEmergencyContact());
            
            // Set profile picture if exists
            if (model.getProfilePicturePath() != null && !model.getProfilePicturePath().isEmpty()) {
                view.setProfilePicture(model.getProfilePicturePath());
            }
        }
    }

    // Method to update personal information from the form
    public void updatePersonalInformation() {
        String fullName = view.getFullName();
        String email = view.getEmail();
        String phone = view.getPhone();
        String dob = view.getDob();
        String gender = view.getGender();
        String address = view.getAddress();
        String emergencyContact = view.getEmergencyContact();

        // Enhanced validation
        if (fullName == null || fullName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Full Name is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Email is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Email validation
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(view, "Please enter a valid email address.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update model
        model.setFullName(fullName.trim());
        model.setEmail(email.trim());
        model.setPhone(phone != null ? phone.trim() : "");
        model.setDob(dob != null ? dob.trim() : "");
        model.setGender(gender != null ? gender.trim() : "");
        model.setAddress(address != null ? address.trim() : "");
        model.setEmergencyContact(emergencyContact != null ? emergencyContact.trim() : "");

        // Save to database (you can implement this later)
        saveToDatabase();

        // Feedback to user
        JOptionPane.showMessageDialog(view, "Personal information updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to handle profile picture change
    public void changeProfilePicture() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "bmp", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            try {
                ImageIcon profileIcon = new ImageIcon(selectedFile.getAbsolutePath());
                
                // Resize the image to fit the label
                Image scaledImage = profileIcon.getImage().getScaledInstance(
                    view.getProfileLabelWidth(), 
                    view.getProfileLabelHeight(), 
                    Image.SCALE_SMOOTH
                );
                
                view.setProfilePicture(selectedFile.getAbsolutePath());
                model.setProfilePicturePath(selectedFile.getAbsolutePath());
                
                JOptionPane.showMessageDialog(view, "Profile picture updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to navigate back to dashboard
    public void navigateToDashboard() {
        int response = JOptionPane.showConfirmDialog(
            view,
            "Are you sure you want to go back to the dashboard?",
            "Confirm Navigation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            view.dispose();
            DashBoardView dashboardView = new DashBoardView();
            DashboardController dashboardController = new DashboardController(dashboardView);
            dashboardView.setVisible(true);
        }
    }

    // Email validation helper method
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Save to database (placeholder for future implementation)
    private void saveToDatabase() {
        // TODO: Implement database save functionality
        // This can be connected to your existing DatabaseManager
        System.out.println("Saving personal data to database: " + model.toString());
    }

    // Load from database (placeholder for future implementation)
    public void loadFromDatabase(String userId) {
        // TODO: Implement database load functionality
        System.out.println("Loading personal data for user: " + userId);
    }
}
