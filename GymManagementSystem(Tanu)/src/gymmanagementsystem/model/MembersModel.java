package gymmanagementsystem.model;

import gymmanagementsystem.database.DbConnection;
import gymmanagementsystem.database.Mysqlconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class MembersModel {
    private final DbConnection dbConnection;
    
    public MembersModel() {
        this.dbConnection = new Mysqlconnection();
    }
    
    /**
     * Validates member data before adding to database
     * @param name Member's name
     * @param contact Member's contact number
     * @param plan Membership plan
     * @return true if data is valid, false otherwise
     */
    public boolean validateMemberData(String name, String contact, String plan) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        if (contact == null || contact.trim().isEmpty() || !contact.matches("\\d+")) {
            return false;
        }
        if (plan == null || plan.trim().isEmpty()) {
            return false;
        }
        return true;
    }
    
    /**
     * Adds a new member to the database
     * @param name Member's name
     * @param contact Member's contact number
     * @param plan Membership plan
     * @return true if member was added successfully, false otherwise
     */
    public boolean addMember(String name, String contact, String plan) {
        if (!validateMemberData(name, contact, plan)) {
            return false;
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dbConnection.openConnection();
            String query = "INSERT INTO members (name, contact, plan) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name.trim());
            stmt.setString(2, contact.trim());
            stmt.setString(3, plan.trim());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding member: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
            if (conn != null) {
                dbConnection.closeConnection(conn);
            }
        }
    }
    
    /**
     * Retrieves all members from database as a TableModel
     * @return DefaultTableModel containing all members
     */
    public DefaultTableModel getMembersTableModel() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"ID", "Name", "Contact", "Plan", "Status", "Join Date"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Integer.class; // ID
                if (columnIndex == 5) return Timestamp.class; // Join Date
                return String.class; // All other columns
            }
        };
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.openConnection();
            String query = "SELECT member_id, name, contact, plan, status, join_date FROM members ORDER BY join_date DESC";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("member_id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("plan"),
                    rs.getString("status"),
                    rs.getTimestamp("join_date")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving members: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            if (conn != null) {
                dbConnection.closeConnection(conn);
            }
        }
        
        return model;
    }
    
    /**
     * Gets the total count of members in the database
     * @return total number of members
     */
    public int getTotalMembers() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.openConnection();
            String query = "SELECT COUNT(*) AS total FROM members";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error counting members: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            if (conn != null) {
                dbConnection.closeConnection(conn);
            }
        }
        return 0;
    }
    
    /**
     * Deletes a member from the database
     * @param memberId ID of member to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteMember(int memberId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = dbConnection.openConnection();
            String query = "DELETE FROM members WHERE member_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, memberId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting member: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
            if (conn != null) {
                dbConnection.closeConnection(conn);
            }
        }
    }
    
    /**
     * Updates member information in the database
     * @param memberId ID of member to update
     * @param name Updated name
     * @param contact Updated contact
     * @param plan Updated plan
     * @param status Updated status
     * @return true if update was successful, false otherwise
     */
    public boolean updateMember(int memberId, String name, String contact, String plan, String status) {
        if (!validateMemberData(name, contact, plan)) {
            return false;
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = dbConnection.openConnection();
            String query = "UPDATE members SET name = ?, contact = ?, plan = ?, status = ? WHERE member_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name.trim());
            stmt.setString(2, contact.trim());
            stmt.setString(3, plan.trim());
            stmt.setString(4, status != null ? status.trim() : "Active");
            stmt.setInt(5, memberId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating member: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
            if (conn != null) {
                dbConnection.closeConnection(conn);
            }
        }
    }
    
    /**
     * Searches for members by name or contact
     * @param searchTerm term to search for
     * @return List of matching members
     */
    public List<Object[]> searchMembers(String searchTerm) {
        List<Object[]> results = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.openConnection();
            String query = "SELECT member_id, name, contact, plan, status, join_date FROM members " +
                         "WHERE name LIKE ? OR contact LIKE ? ORDER BY name";
            stmt = conn.prepareStatement(query);
            String likeTerm = "%" + searchTerm + "%";
            stmt.setString(1, likeTerm);
            stmt.setString(2, likeTerm);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                results.add(new Object[]{
                    rs.getInt("member_id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("plan"),
                    rs.getString("status"),
                    rs.getTimestamp("join_date")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error searching members: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            if (conn != null) {
                dbConnection.closeConnection(conn);
            }
        }
        
        return results;
    }
    
    /**
     * Gets a single member by ID
     * @param memberId ID of member to retrieve
     * @return Object array with member data or null if not found
     */
    public Object[] getMemberById(int memberId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = dbConnection.openConnection();
            String query = "SELECT member_id, name, contact, plan, status, join_date FROM members WHERE member_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, memberId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Object[]{
                    rs.getInt("member_id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("plan"),
                    rs.getString("status"),
                    rs.getTimestamp("join_date")
                };
            }
        } catch (SQLException e) {
            System.err.println("Error getting member by ID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            if (conn != null) {
                dbConnection.closeConnection(conn);
            }
        }
        return null;
    }
}