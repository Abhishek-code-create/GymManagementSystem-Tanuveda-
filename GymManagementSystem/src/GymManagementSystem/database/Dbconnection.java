/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymManagementSystem.database;

import java.sql.Connection;

/**
 *
 * @author ACER
 */
public class Dbconnection {
    public interface DbConnection {
    Connection openConnection();
    void closeConnection(Connection conn);
    
}

}