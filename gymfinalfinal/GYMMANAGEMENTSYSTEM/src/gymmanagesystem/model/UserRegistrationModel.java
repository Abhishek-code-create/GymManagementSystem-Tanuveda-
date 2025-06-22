/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagesystem.model;
import gymmanagesystem.Dao.Userdao;

import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author ACER
 */
public class UserRegistrationModel {
    private final Userdao userDao;

    public UserRegistrationModel() {
        this.userDao = new Userdao();
    }

    public boolean isEmailAlreadyExists(String email) {
        return userDao.isEmailExists(email);
    }

    public boolean validateAllFields(String fullName, String email, String password, String confirmPassword) {
        if (fullName == null || fullName.trim().isEmpty()) return false;

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null || !Pattern.matches(emailRegex, email)) return false;

        if (password == null || password.length() < 8) return false;

        if (!password.equals(confirmPassword)) return false;

        return true;
    }

    public boolean registerUser(String fullName, String email, String phone, String password, Date dateOfBirth) {
        User user = new User(fullName, email, phone, password, dateOfBirth);
        return userDao.insertUser(user);
    }
}
