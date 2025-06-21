package gymmanagementsystem.model;

import gymmanagementsystem.database.Mysqlconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginSystemModel {
    private Mysqlconnection dbConnection;

    public LoginSystemModel() {
        this.dbConnection = new Mysqlconnection();
    }

    public String authenticate(String email, String password) {
        Connection conn = null;
        try {
            conn = dbConnection.openConnection();
            if (conn != null) {
                // First check admin table
                String adminQuery = "SELECT * FROM admin WHERE username = ? AND password = ?";
                PreparedStatement adminStmt = conn.prepareStatement(adminQuery);
                adminStmt.setString(1, email);
                adminStmt.setString(2, password);
                
                ResultSet adminRs = adminStmt.executeQuery();
                if (adminRs.next()) {
                    adminRs.close();
                    adminStmt.close();
                    return "admin";
                }
                adminRs.close();
                adminStmt.close();
                
                // Then check user table
                String userQuery = "SELECT * FROM user WHERE email = ? AND password = ?";
                PreparedStatement userStmt = conn.prepareStatement(userQuery);
                userStmt.setString(1, email);
                userStmt.setString(2, password);
                
                ResultSet userRs = userStmt.executeQuery();
                if (userRs.next()) {
                    userRs.close();
                    userStmt.close();
                    return "user";
                }
                userRs.close();
                userStmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                dbConnection.closeConnection(conn);
            }
        }
        return null; // Authentication failed
    }
} 