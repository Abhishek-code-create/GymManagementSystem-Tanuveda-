package GymManagementSystem.model;

public class Reminder {
    private String date;
    private String task;
    private String status;
    private String due;

    public Reminder(String date, String task, String status, String due) {
        this.date = date;
        this.task = task;
        this.status = status;
        this.due = due;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDue() { return due; }
    public void setDue(String due) { this.due = due; }

    @Override
    public String toString() {
        return date + ", " + task + ", " + status + ", " + due;
    }
} 