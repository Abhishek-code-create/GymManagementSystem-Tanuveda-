package GymManagementSystem.controller;

import GymManagementSystem.model.reminder;
import java.util.ArrayList;
import java.util.List;

public class remindercontroller {
    private List<reminder> reminders = new ArrayList<>();

    public List<reminder> getReminders() {
        return reminders;
    }

    public void addReminder(reminder r) {
        reminders.add(r);
    }

    public void updateReminder(int index, reminder r) {
        if (index >= 0 && index < reminders.size()) {
            reminders.set(index, r);
        }
    }

    public void deleteReminder(int index) {
        if (index >= 0 && index < reminders.size()) {
            reminders.remove(index);
        }
    }

    public void markCompleted(int index) {
        if (index >= 0 && index < reminders.size()) {
            reminders.get(index).setStatus("completed");
        }
    }
} 