package GymManagementSystem.model;

import GymManagementSystem.controller.ReminderController;
import javax.swing.table.AbstractTableModel;

public class ReminderTableModel extends AbstractTableModel {
    private final String[] columns = {"Date", "Task", "Status", "Duly on"};
    private ReminderController controller;

    public ReminderTableModel(ReminderController controller) {
        this.controller = controller;
    }

    @Override
    public int getRowCount() {
        return controller.getReminders().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Reminder r = controller.getReminders().get(row);
        switch (col) {
            case 0: return r.getDate();
            case 1: return r.getTask();
            case 2: return r.getStatus();
            case 3: return r.getDue();
            default: return null;
        }
    }

    public Reminder getReminderAt(int row) {
        return controller.getReminders().get(row);
    }

    public void refresh() {
        fireTableDataChanged();
    }
} 