package gymmanagementsystem.model;

import java.time.LocalTime;

public class DashboardModel {
    public String getWelcomeMessage(String username) {
        return "Welcome, " + username + "!";
    }
    
    public String getTimeBasedGreeting(String username) {
        LocalTime now = LocalTime.now();
        String greeting;
        
        if (now.getHour() < 12) {
            greeting = "Good Morning";
        } else if (now.getHour() < 17) {
            greeting = "Good Afternoon";
        } else {
            greeting = "Good Evening";
        }
        
        return greeting + ", " + username + "!";
    }
    
    // Add more dashboard data methods as needed
} 