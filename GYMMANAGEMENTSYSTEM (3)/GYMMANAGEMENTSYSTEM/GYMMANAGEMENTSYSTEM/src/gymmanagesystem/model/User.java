/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagesystem.model;
import java.util.Date;
/**
 *
 * @author ACER
 */
public class User {
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private Date dateOfBirth;

    public User(String fullName, String email, String phone, String password, Date dateOfBirth) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
}
