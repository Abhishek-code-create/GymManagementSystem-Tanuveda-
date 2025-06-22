/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagementsystem.controller;

import gymmanagementsystem.model.personaldata;
import gymmanagementsystem.view.DashBoardView;
import gymmanagementsystem.view.PersonalInformation;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Controller for Personal Information management
 */
public class personalcontroller {

    private personaldata model;
    private PersonalInformation view;
    private String username;

    public personalcontroller(PersonalInformation view, String username) {
        this.view = view;
        this.username = username;
        this.model = new personaldata(); // Or load it from DB
        loadAndDisplayUserData();
    }

    private void loadAndDisplayUserData() {
        // In a real app, you'd load this from a database using the username.
        // For now, we'll use the example data.
        model.setFullName("Siddhartha Sah");
        model.setEmail("Sidharthasah@gmail.com");
        model.setPhone("9817811009");
        model.setDob("2000-01-01");
        model.setGender("Male");
        model.setAddress("123 Main St, Anytown");
        model.setEmergencyContact("9876543210");
        
        // Update the view's fields
        view.getFullNameField().setText(model.getFullName());
        view.getEmailField().setText(model.getEmail());
        view.getPhoneField().setText(model.getPhone());
        view.getDobField().setText(model.getDob());
        view.getGenderField().setText(model.getGender());
        view.getAddressField().setText(model.getAddress());
        view.getEmergencyContactField().setText(model.getEmergencyContact());

        // Update the display labels on the left
        view.setDisplayName(model.getFullName());
        view.setDisplayEmail(model.getEmail());
        view.setDisplayPhone(model.getPhone());
    }

    public void saveInformation() {
        // Get updated info from the view
        String newName = view.getFullNameField().getText();
        String newEmail = view.getEmailField().getText();
        String newPhone = view.getPhoneField().getText();
        String newDob = view.getDobField().getText();
        String newGender = view.getGenderField().getText();
        String newAddress = view.getAddressField().getText();
        String newEmergencyContact = view.getEmergencyContactField().getText();

        // Basic validation
        if (newName.trim().isEmpty() || newEmail.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Name and Email are required fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Update the model
        model.setFullName(newName);
        model.setEmail(newEmail);
        model.setPhone(newPhone);
        model.setDob(newDob);
        model.setGender(newGender);
        model.setAddress(newAddress);
        model.setEmergencyContact(newEmergencyContact);

        // Here, you would call a method to save the 'model' to the database
        saveToDatabase();
        
        JOptionPane.showMessageDialog(view, "Information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Update the display-only labels as well
        view.setDisplayName(newName);
        view.setDisplayEmail(newEmail);
        view.setDisplayPhone(newPhone);
    }

    public void changeProfilePicture() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "bmp");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();
            model.setProfilePicturePath(imagePath);

            ImageIcon profileIcon = new ImageIcon(imagePath);
            Image scaledImage = profileIcon.getImage().getScaledInstance(
                view.getProfilePictureLabel().getWidth(), 
                view.getProfilePictureLabel().getHeight(), 
                Image.SCALE_SMOOTH
            );
            view.getProfilePictureLabel().setIcon(new ImageIcon(scaledImage));
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
