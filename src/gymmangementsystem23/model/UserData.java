/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmangementsystem23.model;

/**
 *
 * @author ASUS
 */
public class UserData {
    private String name;
    private String password;
    private String ConfirmPassword;
    private String Email;
    
        // Constructor
    public UserData(String name, String Email, String password,String ConfirmPassword) {
        this.name = name;
        this.Email = Email;
        this.password = password;
        this.ConfirmPassword = ConfirmPassword;
    }

    // Getters and Setters
    public String getFullName() {
        return name;
    }

    public void setFullName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return password;
    }

 public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setPhoneNumber(String ConfirmPassword) {
        this.ConfirmPassword = ConfirmPassword;
        
    }
    

}
     