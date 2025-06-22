package gymmanagesystem.Dao;

import gymmanagesystem.database.Mysqlconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {
    private final Mysqlconnection db = new Mysqlconnection();

    public boolean insertPurchase(String userEmail, String userName, String userPhone,
                                 int productId, String productName, double productPrice) {
        String sql = "INSERT INTO purchases (user_email, user_name, user_phone, product_id, product_name, product_price) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, userEmail);
            pst.setString(2, userName);
            pst.setString(3, userPhone);
            pst.setInt(4, productId);
            pst.setString(5, productName);
            pst.setDouble(6, productPrice);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Object[]> getPurchasesByUserEmail(String userEmail) {
        List<Object[]> purchases = new ArrayList<>();
        String sql = "SELECT id, product_id, product_name, product_price, purchase_time FROM purchases WHERE user_email = ? ORDER BY purchase_time DESC";
        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, userEmail);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                purchases.add(new Object[] {
                    rs.getInt("id"),
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getDouble("product_price"),
                    rs.getTimestamp("purchase_time")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return purchases;
    }
} 