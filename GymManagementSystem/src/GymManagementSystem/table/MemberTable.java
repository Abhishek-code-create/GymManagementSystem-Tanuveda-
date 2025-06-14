/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymManagementSystem.table;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class MemberTable {
    private JTable table;
    private DefaultTableModel model;

    public MemberTable(JTable table) {
        this.table = table;
        this.model = (DefaultTableModel) table.getModel();
    }

    // Returns total members (row count)
    public int getTotalMembers() {
        return model.getRowCount();
    }

    // Adds a member row to the table
    public void addMember(String name, String contact, String plan) {
        // Example fixed values for Status and Join date
        String status = "Active";
        String joinDate = java.time.LocalDate.now().toString();
        String actions = "Edit/Delete"; // Placeholder text

        Object[] rowData = {name, contact, plan, status, joinDate, actions};
        model.addRow(rowData);
    }
}
