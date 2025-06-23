/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagesystem.controller;

import gymmanagesystem.Dao.personalDao;
import gymmanagesystem.model.personaldata;
import gymmanagesystem.view.PersonalInformation;
import gymmanagesystem.view.DashBoardView;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.*;
import java.awt.*;
/**
 * Controller for Personal Information management
 */
public class personalcontroller {
    private personaldata model;
    private PersonalInformation view;
    private personalDao dao;

    public personalcontroller(personaldata model, PersonalInformation view) {
        this.model = model;
        this.view = view;
        this.dao = new personalDao();
        this.view.setController(this);
    }

    public void loadFromDatabase(int id) {
        personaldata data = dao.getPersonalDataById(id);
        if (data != null) {
            view.setFullName(data.getFullName());
            view.setEmail(data.getEmail());
            view.setPhone(data.getPhone());
            view.setDob(data.getDob());
            view.setGender(data.getGender());
            view.setAddress(data.getAddress());
            view.setEmergencyContact(data.getEmergencyContact());
            view.setProfilePicture(data.getProfilePicturePath());
        }
    }

    public void updatePersonalInformation() {
        model.setFullName(view.getFullName());
        model.setEmail(view.getEmail());
        model.setPhone(view.getPhone());
        model.setDob(view.getDob());
        model.setGender(view.getGender());
        model.setAddress(view.getAddress());
        model.setEmergencyContact(view.getEmergencyContact());

        boolean success = dao.updatePersonalData(model);
        if (success) {
            javax.swing.JOptionPane.showMessageDialog(view, "Personal information updated successfully.");
        } else {
            javax.swing.JOptionPane.showMessageDialog(view, "Failed to update personal information.");
        }
    }

    public void navigateToDashboard() {
        gymmanagesystem.view.DashBoardView dashboardView = new gymmanagesystem.view.DashBoardView();
        dashboardView.setVisible(true);
        view.dispose();
    }

    public void changeProfilePicture(Component parent, JLabel profileLabel) {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "bmp");
    fileChooser.setFileFilter(filter);

    int result = fileChooser.showOpenDialog(parent);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        ImageIcon profileIcon = new ImageIcon(selectedFile.getAbsolutePath());

        // Resize the image to fit the label
        Image scaledImage = profileIcon.getImage().getScaledInstance(
            profileLabel.getWidth(), profileLabel.getHeight(), Image.SCALE_SMOOTH);
        profileLabel.setIcon(new ImageIcon(scaledImage));
    }
}

    
    public void insertPersonalInformation() {
    model.setFullName(view.getFullName());
    model.setEmail(view.getEmail());
    model.setPhone(view.getPhone());
    model.setDob(view.getDob());
    model.setGender(view.getGender());
    model.setAddress(view.getAddress());
    model.setEmergencyContact(view.getEmergencyContact());

    boolean success = dao.insertPersonalData(model);
    if (success) {
        JOptionPane.showMessageDialog(view, "Personal information inserted successfully.");
    } else {
        JOptionPane.showMessageDialog(view, "Failed to insert personal information.");
    }
}

}
