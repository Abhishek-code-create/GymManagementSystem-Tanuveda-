/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagesystem.Dao;

import gymmanagesystem.database.Mysqlconnection;
import gymmanagesystem.model.personaldata;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class personalDao {

    private Mysqlconnection db = new Mysqlconnection();
    private String id;

    public boolean insertPersonalData(personaldata data) {
        String sql = "INSERT INTO personal_information (full_name, email, phone, dob, gender, address, emergency_contact, profile_picture_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data.getFullName());
            stmt.setString(2, data.getEmail());
            stmt.setString(3, data.getPhone());
            stmt.setString(4, data.getDob());
            stmt.setString(5, data.getGender());
            stmt.setString(6, data.getAddress());
            stmt.setString(7, data.getEmergencyContact());
            stmt.setString(8, data.getProfilePicturePath());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Insert personal data failed: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePersonalData(personaldata data) {
        String sql = "UPDATE personal_information SET full_name=?, phone=?, dob=?, gender=?, address=?, emergency_contact=?, profile_picture_path=? WHERE email=?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data.getFullName());
            stmt.setString(2, data.getPhone());
            stmt.setString(3, data.getDob());
            stmt.setString(4, data.getGender());
            stmt.setString(5, data.getAddress());
            stmt.setString(6, data.getEmergencyContact());
            stmt.setString(7, data.getProfilePicturePath());
            stmt.setString(8, data.getEmail());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Update personal data failed: " + e.getMessage());
            return false;
        }
    }

    public personaldata getPersonalDataById(int id) {
    String sql = "SELECT * FROM personal_information WHERE id=?";
    try (Connection conn = db.openConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            personaldata data = new personaldata();
            data.setId(rs.getInt("id"));
            data.setFullName(rs.getString("full_name"));
            data.setEmail(rs.getString("email"));
            data.setPhone(rs.getString("phone"));
            data.setDob(rs.getString("dob"));
            data.setGender(rs.getString("gender"));
            data.setAddress(rs.getString("address"));
            data.setEmergencyContact(rs.getString("emergency_contact"));
            data.setProfilePicturePath(rs.getString("profile_picture_path"));
            return data;
        }
    } catch (Exception e) {
        System.err.println("Get personal data by ID failed: " + e.getMessage());
        e.printStackTrace();
    }
    return null;
}

    public personaldata getPersonalDataByEmail(String email) {
        String sql = "SELECT * FROM personal_information WHERE email=?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                personaldata data = new personaldata();
                data.setId(rs.getInt("id"));
                data.setFullName(rs.getString("full_name"));
                data.setEmail(rs.getString("email"));
                data.setPhone(rs.getString("phone"));
                data.setDob(rs.getString("dob"));
                data.setGender(rs.getString("gender"));
                data.setAddress(rs.getString("address"));
                data.setEmergencyContact(rs.getString("emergency_contact"));
                data.setProfilePicturePath(rs.getString("profile_picture_path"));
                return data;
            }
        } catch (Exception e) {
            System.err.println("Get personal data by email failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

