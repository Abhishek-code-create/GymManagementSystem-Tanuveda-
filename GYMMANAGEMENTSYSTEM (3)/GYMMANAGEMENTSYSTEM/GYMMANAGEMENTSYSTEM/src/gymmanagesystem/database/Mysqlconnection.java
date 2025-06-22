/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagesystem.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DELL
 */

public class Mysqlconnection implements DbConnection {

    @Override
    public Connection openConnection() {
        try {
            String username = "root";
            String password = "admin123"; // Database password
            String database = "Tanuveda"; // Database name
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL JDBC Driver
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + database,
                username,
                password
            );
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
