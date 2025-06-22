/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagesystem.Dao;
import gymmanagesystem.database.DbConnection;
import gymmanagesystem.database.Mysqlconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ACER
 */
public class ProductDAO {
    private DbConnection dbConnection;

    public ProductDAO() {
        this.dbConnection = new Mysqlconnection();
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean updateProductStock(int productId, int newStock) {
        String query = "UPDATE products SET stock = ? WHERE id = ?";
        
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, newStock);
            stmt.setInt(2, productId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static class Product {

        public Product(int aInt, String string, String string0, String string1, double aDouble, int aInt0) {
        }
    }
}