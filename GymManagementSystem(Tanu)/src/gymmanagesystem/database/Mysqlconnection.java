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
public class Mysqlconnection implements DbConnection{

    @Override
    public Connection openConnection() {
      try{
          String username="root";
          String password=" ";
          String database="";
          Class.forName("com.mysql.jdbc.Driver");
          Connection conn;
          conn = DriverManager.getConnection("jdbc"+
                  database,username,password);
          return conn;
      } catch(Exception e){
          return null;
      }
    }

    @Override
    public void closeConnection(Connection conn) {
       try{
           if(conn!=null && !conn.isClosed()){
               conn.close();
           }
       } catch(Exception e){
           
       }
    }
    
}

    
