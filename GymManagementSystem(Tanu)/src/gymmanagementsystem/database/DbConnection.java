/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagementsystem.database;

import java.sql.Connection;

/**
 *
 * @author DELL
 */
public interface DbConnection {
    Connection openConnection();
    void closeConnection(Connection conn);
    
}
