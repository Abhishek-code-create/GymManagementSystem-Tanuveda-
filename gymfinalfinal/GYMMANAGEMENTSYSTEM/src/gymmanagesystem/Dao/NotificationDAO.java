package gymmanagesystem.Dao;

import gymmanagesystem.database.Mysqlconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    private final Mysqlconnection db = new Mysqlconnection();

    public boolean insertNotification(String title, String message, String type, String audience, boolean scheduled) {
        String sql = "INSERT INTO notifications (title, message, type, audience, scheduled) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, title);
            pst.setString(2, message);
            pst.setString(3, type);
            pst.setString(4, audience);
            pst.setBoolean(5, scheduled);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Object[]> getAllNotifications() {
        List<Object[]> notifications = new ArrayList<>();
        String sql = "SELECT id, title, message, type, audience, scheduled, created_at FROM notifications ORDER BY created_at DESC";
        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                notifications.add(new Object[] {
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("message"),
                    rs.getString("type"),
                    rs.getString("audience"),
                    rs.getBoolean("scheduled"),
                    rs.getTimestamp("created_at")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public void clearAllNotifications() {
        String sql = "DELETE FROM notifications";
        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 