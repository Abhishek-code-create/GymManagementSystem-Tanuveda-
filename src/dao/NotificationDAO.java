package dao;

import java.util.ArrayList;
import java.util.List;
import model.Notification;

public class NotificationDAO {
    private static final List<Notification> notificationDB = new ArrayList<>();

    public static void save(Notification notification) {
        notificationDB.add(notification);
    }

    public static List<Notification> getAll() {
        return new ArrayList<>(notificationDB);
    }

    public static void updateStatus(int id, String status) {
        for (Notification n : notificationDB) {
            if (n.getId() == id) {
                n.setDeliveryStatus(status);
                break;
            }
        }
    }
}
