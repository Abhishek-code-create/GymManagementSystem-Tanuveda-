package gymmanagesystem.controller;

import gymmanagesystem.model.MembersModel;
import gymmanagesystem.view.members;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MembersController {
    
    private MembersModel model;
    private members view;
    
    public MembersController(members view) {
        this.view = view;
        this.model = new MembersModel();
    }
    
    public void addMember(String name, String contact, String plan) {
        // Validate input
        if (!model.validateMemberData(name, contact, plan)) {
            JOptionPane.showMessageDialog(
                view, 
                "Please fill in all fields correctly.", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Add member to database
        if (model.addMember(name, contact, plan)) {
            JOptionPane.showMessageDialog(
                view, 
                "Member added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE
            );
            
            // Clear form fields
            clearFormFields();
            
            // Refresh table and total count
            refreshMembersTable();
            updateTotalMembers();
        } else {
            JOptionPane.showMessageDialog(
                view, 
                "Failed to add member. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    
    
    public void refreshMembersTable() {
        DefaultTableModel tableModel = model.getMembersTableModel();
        view.getMembersTable().setModel(tableModel);
    }
    
    public void updateTotalMembers() {
        int totalMembers = model.getTotalMembers();
        view.updateTotalMembersLabel(totalMembers);
    }
    
    public void deleteMember(int memberId) {
        int confirm = JOptionPane.showConfirmDialog(
            view, 
            "Are you sure you want to delete this member?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (model.deleteMember(memberId)) {
                JOptionPane.showMessageDialog(
                    view, 
                    "Member deleted successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                refreshMembersTable();
                updateTotalMembers();
            } else {
                JOptionPane.showMessageDialog(
                    view, 
                    "Failed to delete member. Please try again.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    public void editMember(int memberId, String name, String contact, String plan, String status) {
        if (!model.validateMemberData(name, contact, plan)) {
            JOptionPane.showMessageDialog(
                view, 
                "Please fill in all fields correctly.", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        if (model.updateMember(memberId, name, contact, plan, status)) {
            JOptionPane.showMessageDialog(
                view, 
                "Member updated successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE
            );
            refreshMembersTable();
        } else {
            JOptionPane.showMessageDialog(
                view, 
                "Failed to update member. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    public void setupTableListeners() {
    view.getMembersTable().getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = view.getMembersTable().getSelectedRow();
            if (selectedRow >= 0) {
                DefaultTableModel model = (DefaultTableModel) view.getMembersTable().getModel();
                int memberId = (int) model.getValueAt(selectedRow, 0);
                String name = (String) model.getValueAt(selectedRow, 1);
                String contact = (String) model.getValueAt(selectedRow, 2);
                String plan = (String) model.getValueAt(selectedRow, 3);
                String status = (String) model.getValueAt(selectedRow, 4);
                
                // Update fields with selected member data
                view.getNameField().setText(name);
                view.getContactField().setText(contact);
                view.getPlanField().setText(plan);
            }
        }
    });
}
    public void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(
            view, 
            "Are you sure you want to log out?", 
            "Confirm Logout", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                view, 
                "Logged out successfully!", 
                "Logout", 
                JOptionPane.INFORMATION_MESSAGE
            );
            System.exit(0);
        }
    }
    
    public void showNotifications() {
        JOptionPane.showMessageDialog(
            view, 
            "You have new notifications!", 
            "Notifications", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    public void showOrders() {
        JOptionPane.showMessageDialog(
            view, 
            "Orders management feature coming soon!", 
            "Orders", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void clearFormFields() {
        view.getNameField().setText("");
        view.getContactField().setText("");
        view.getPlanField().setText("");
    }
    
    public void initializeView() {
        refreshMembersTable();
        updateTotalMembers();
    }
} 