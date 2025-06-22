package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import model.Notification;
import view.NotificationView;

public class NotificationController {
    private List<Notification> notifications;
    private NotificationView notificationView;
    private List<NotificationListener> listeners;
    private static NotificationController instance;

    public interface NotificationListener {
        void onNotificationAdded(Notification notification);
        void onNotificationRemoved(Notification notification);
        void onNotificationCountChanged(int count);
    }

    private NotificationController() {
        notifications = new CopyOnWriteArrayList<>();
        listeners = new ArrayList<>();
        notificationView = new NotificationView(this);
    }

    public static NotificationController getInstance() {
        if (instance == null) {
            instance = new NotificationController();
        }
        return instance;
    }

    public void addNotification(String type, String message) {
        addNotification(type, message, null);
    }

    public void addNotification(String type, String message, String action) {
        Notification notification = new Notification(type, message, action);
        notification.setId(notifications.size() + 1);
        notifications.add(notification);
        
        // Notify listeners
        for (NotificationListener listener : listeners) {
            listener.onNotificationAdded(notification);
            listener.onNotificationCountChanged(getUnreadCount());
        }
    }

    public void addNotification(Notification notification) {
        notification.setId(notifications.size() + 1);
        notifications.add(notification);
        // Notify listeners
        for (NotificationListener listener : listeners) {
            listener.onNotificationAdded(notification);
            listener.onNotificationCountChanged(getUnreadCount());
        }
    }

    public void markAsRead(int notificationId) {
        for (Notification notification : notifications) {
            if (notification.getId() == notificationId) {
                notification.setRead(true);
                break;
            }
        }
        
        // Notify listeners
        for (NotificationListener listener : listeners) {
            listener.onNotificationCountChanged(getUnreadCount());
        }
    }

    public void markAllAsRead() {
        for (Notification notification : notifications) {
            notification.setRead(true);
        }
        
        // Notify listeners
        for (NotificationListener listener : listeners) {
            listener.onNotificationCountChanged(getUnreadCount());
        }
    }

    public void removeNotification(int notificationId) {
        notifications.removeIf(n -> n.getId() == notificationId);
        
        // Notify listeners
        for (NotificationListener listener : listeners) {
            listener.onNotificationRemoved(null);
            listener.onNotificationCountChanged(getUnreadCount());
        }
    }

    public void clearAllNotifications() {
        notifications.clear();
        
        // Notify listeners
        for (NotificationListener listener : listeners) {
            listener.onNotificationCountChanged(0);
        }
    }

    public List<Notification> getAllNotifications() {
        return new ArrayList<>(notifications);
    }

    public List<Notification> getUnreadNotifications() {
        List<Notification> unread = new ArrayList<>();
        for (Notification notification : notifications) {
            if (!notification.isRead()) {
                unread.add(notification);
            }
        }
        return unread;
    }

    public int getUnreadCount() {
        int count = 0;
        for (Notification notification : notifications) {
            if (!notification.isRead()) {
                count++;
            }
        }
        return count;
    }

    public void showNotificationView() {
        notificationView.displayNotifications(getAllNotifications());
        notificationView.setVisible(true);
    }

    public void addListener(NotificationListener listener) {
        listeners.add(listener);
    }

    public void removeListener(NotificationListener listener) {
        listeners.remove(listener);
    }

    // Convenience methods for common notification types
    public void showSuccess(String message) {
        addNotification("SUCCESS", message);
    }

    public void showError(String message) {
        addNotification("ERROR", message);
    }

    public void showWarning(String message) {
        addNotification("WARNING", message);
    }

    public void showInfo(String message) {
        addNotification("INFO", message);
    }
}