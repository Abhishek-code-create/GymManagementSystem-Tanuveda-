package gymmanagementsystem.controller;

import com.toedter.calendar.JCalendar;
import gymmanagesystem.model.AttendanceModel;
import gymmanagementsystem.view.DashBoardView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class AttendanceController {
    private final AttendanceModel model;
    private final DashBoardView view;
    
    public AttendanceController(AttendanceModel model, DashBoardView view) {
        this.model = model;
        this.view = view;
        initializeListeners();
    }
    
    private void initializeListeners() {
        // Add action listener to the check-in button
        view.getCheckInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckIn();
            }
        });
    }
    
    private void handleCheckIn() {
        // Get the selected date from calendar
        Date selectedDate = view.getCalendar().getDate();
        Calendar selectedCal = Calendar.getInstance();
        selectedCal.setTime(selectedDate);
        
        // Get today's date
        Calendar todayCal = Calendar.getInstance();
        
        // Check if selected date is today
        if (isSameDay(selectedCal, todayCal)) {
            // Increment streak in model
            int newStreak = model.incrementStreak();
            
            // Update the streak label in view
            view.updateStreakLabel(newStreak);
            
            // Disable the check-in button for today
            view.getCheckInButton().setEnabled(false);
            
            // Show success message
            JOptionPane.showMessageDialog(
                view,
                "Check-in successful! Your streak is now " + newStreak + " days.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            // Show error message if date is not today
            JOptionPane.showMessageDialog(
                view,
                "You can only check in for today's date!",
                "Invalid Date",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private boolean isSameDay(Calendar cal1, Calendar cal2) {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
} 