package gymmanagesystem;

import gymmanagesystem.controller.AttendanceController;
import gymmanagesystem.model.AttendanceModel;
import gymmanagesystem.view.DashBoardView;

public class Main {
    public static void main(String[] args) {
        // Create the MVC components
        AttendanceModel model = new AttendanceModel();
        DashBoardView view = new DashBoardView();
        AttendanceController controller = new AttendanceController(model, view);
        
        // Show the dashboard
        view.setVisible(true);
    }
} 