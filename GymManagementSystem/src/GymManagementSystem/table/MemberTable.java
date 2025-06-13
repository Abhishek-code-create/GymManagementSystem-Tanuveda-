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
        // Initialize table with existing members if you have any.
    }

    public int getTotalMembers() {
        return model.getRowCount();
    }

    public void addMember(String name, String contact, String plan) {
        // Add a new row with default status and join date, for example:
        Object[] newRow = new Object[] { name, contact, plan, "Active", "2025-06-13", "Edit/Delete" };
        model.addRow(newRow);
    }
    
}
