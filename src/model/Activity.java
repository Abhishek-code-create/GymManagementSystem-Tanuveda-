package model;

import java.time.LocalDate;

public class Activity {
    private LocalDate date;
    private String title;
    private String details;

    public Activity(LocalDate date, String title, String details) {
        this.date = date;
        this.title = title;
        this.details = details;
    }

    public LocalDate getDate() { return date; }
    public String getTitle() { return title; }
    public String getDetails() { return details; }
}
