package gymmanagementsystem.model;

public class AttendanceModel {
    private int currentStreak;
    
    public AttendanceModel() {
        this.currentStreak = 0;
    }
    
    public int getCurrentStreak() {
        return currentStreak;
    }
    
    public int incrementStreak() {
        currentStreak++;
        return currentStreak;
    }
    
    public void setCurrentStreak(int streak) {
        this.currentStreak = streak;
    }
} 