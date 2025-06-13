/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmangementsystem23.Controller;

import gymmangementsystem23.dao.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author ASUS
 */


public class RegisterController {
    private UserDao userDao;

    public RegisterController() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "Username", "password");
            this.userDao = new UserDao(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(String Username, String password,String Email,String confirmpassword) {
        if (Username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty.");
            return false;
        }
        return userDao.Registerview(Username,password,Email,confirmpassword);
    }

    public static void main(String[] args) {
        RegisterController controller = new RegisterController();
        boolean success = controller.registerUser("testUser", "securePassword","Email","confirmpassword");

        if (success) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration failed.");
        }
    }
}
    