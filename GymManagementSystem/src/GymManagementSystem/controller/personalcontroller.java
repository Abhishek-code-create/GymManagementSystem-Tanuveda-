/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymManagementSystem.controller;
import GymManagementSystem.model.personaldata;
import GymManagementSystem.view.PersonalInformation;
import javax.swing.JOptionPane;
/**
 *
 * @author ACER
 */
public class personalcontroller {

    private personaldata model;
    private PersonalInformation view;

    public personalcontroller(personaldata model, PersonalInformation view) {
        this.model = model;
        this.view = view;
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

        // Basic validation
        if (fullName.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Full Name and Email are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update model
        model.setFullName(fullName);
        model.setEmail(email);
        model.setPhone(phone);
        model.setDob(dob);
        model.setGender(gender);
        model.setAddress(address);
        model.setEmergencyContact(emergencyContact);

        // Feedback to user
        JOptionPane.showMessageDialog(view, "Changes saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
