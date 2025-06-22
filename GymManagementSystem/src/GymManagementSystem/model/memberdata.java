/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymManagementSystem.model;

/**
 *
 * @author ACER
 */
public class memberdata {
    private String name;
    private String contact;
    private String plan;

    public memberdata(String name, String contact, String plan) {
        this.name = name;
        this.contact = contact;
        this.plan = plan;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getPlan() {
        return plan;
    }
}

    

