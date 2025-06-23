/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymManagementSystem.Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import GymManagementSystem.model.Userdata;

/**
 *
 * @author ACER
 */
public class AdminDao {
  
    private final String jdbcURL = "jdbc:mysql://localhost:3306/gymdb"; // Replace with your DB name
    private final String jdbcUsername = "root"; // Replace with your DB username
    private final String jdbcPassword = "";     // Replace with your DB password

    private static final String INSERT_USER_SQL = 
        "INSERT INTO admin (fullname, email, password, phone_number) VALUES (?, ?, ?, ?)";

    // Method to register admin
    public boolean register(Userdata user) {
        boolean rowInserted = false;

        try {
            // Step 1: Load JDBC driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

                // Step 3: Set parameters
                preparedStatement.setString(1, user.getFullName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getPhoneNumber());

                // Step 4: Execute
                rowInserted = preparedStatement.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Database error:");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found:");
            e.printStackTrace();
        }

        return rowInserted;
    }
}
