package model;

import java.time.LocalDateTime;

public class Notification {
    private int id;
    private String type; // "SUCCESS", "ERROR", "WARNING", "INFO"
    private String message;
    private LocalDateTime timestamp;
    private boolean isRead;
    private String action; // Optional action to perform when clicked
    private String title; // Notification title
    private String targetAudience; // "ALL" or username/userId
    private LocalDateTime scheduledTime; // For scheduled notifications
    private String deliveryStatus; // "PENDING", "SENT", "FAILED"
    private String recipient; // For specific user

    public Notification(String type, String message) {
        this.type = type;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
    }

    public Notification(String type, String message, String action) {
        this(type, message);
        this.action = action;
    }

    public Notification(String title, String type, String message, String targetAudience, String recipient, LocalDateTime scheduledTime) {
        this.title = title;
        this.type = type;
        this.message = message;
        this.targetAudience = targetAudience;
        this.recipient = recipient;
        this.scheduledTime = scheduledTime;
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
        this.deliveryStatus = "PENDING";
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s: %s", type, timestamp.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")), title, message);
    }
}