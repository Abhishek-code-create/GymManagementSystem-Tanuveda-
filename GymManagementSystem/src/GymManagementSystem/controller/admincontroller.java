/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymManagementSystem.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import GymManagementSystem.Admin;
import GymManagementSystem.model.Userdata;
import GymManagementSystem.Dao.AdminDao;

/**
 *
 * @author ACER
 */
public class admincontroller {

    private Admin view;

    public admincontroller(GymManagementSystem.controller.admincontroller view) {
        this.view = view;

        this.view.jregisterbutton.addActionListener(new RegisterAction());
        this.view.jButton4.addActionListener(e -> view.dispose()); // Exit
        this.view.jButton2.addActionListener(e -> saveData());     // Save
        // Comment this out if button doesn't exist:
        // this.view.jButtonForgetPassword.addActionListener(e -> showForgetPasswordDialog());
    }

    public boolean registerUser(String fullname, String email, String password, String phone) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    class RegisterAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.txtfullname.getText();
            String email = view.textEmail.getText();
            String password = new String(view.textpassword.getPassword());
            String confirmPassword = new String(view.textconpass.getPassword());
            String phone = view.textnum.getText();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill all fields");
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(view, "Passwords do not match");
                return;
            }

            Userdata user = new Userdata(name, email, password, phone);
            AdminDao dao = new AdminDao();
            boolean success = dao.register(user);

            if (success) {
                JOptionPane.showMessageDialog(view, "Registration Successful");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Registration Failed");
            }
        }
    }

    private void clearForm() {
        view.txtfullname.setText("");
        view.textEmail.setText("");
        view.textpassword.setText("");
        view.textconpass.setText("");
        view.textnum.setText("");
    }

    private void saveData() {
        JOptionPane.showMessageDialog(view, "Data saved locally (implement logic)");
    }

    

    public void show() {
        view.setVisible(true);
    }
}
