package gymmanagementsystem55.database;

import java.sql.*;
import java.util.Random;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/Tanuveda";
    private static final String USER = "root";
    private static final String PASS = "Avishek123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Create tables if not exist
    public static void createTables() {
        String adminTable = "CREATE TABLE IF NOT EXISTS admin (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(100) NOT NULL UNIQUE," +
                "password VARCHAR(100) NOT NULL," +
                "admin_keyword VARCHAR(100)," +
                "unique_code VARCHAR(4) NOT NULL" +
                ")";
        String userTable = "CREATE TABLE IF NOT EXISTS user (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "fullname VARCHAR(100) NOT NULL," +
                "dob DATE," +
                "email VARCHAR(100) NOT NULL UNIQUE," +
                "phone VARCHAR(20)," +
                "password VARCHAR(100) NOT NULL," +
                "unique_code VARCHAR(4) NOT NULL" +
                ")";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(adminTable);
            stmt.execute(userTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Generate a unique 4-digit code
    public static String generateUniqueCode() {
        Random rand = new Random();
        int code = 1000 + rand.nextInt(9000);
        return String.valueOf(code);
    }

    // Register Admin
    public static String registerAdmin(String username, String password, String adminKeyword) {
        String code = generateUniqueCode();
        String sql = "INSERT INTO admin (username, password, admin_keyword, unique_code) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, adminKeyword);
            ps.setString(4, code);
            ps.executeUpdate();
            return code;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Register User
    public static String registerUser(String fullname, java.util.Date dob, String email, String phone, String password) {
        String code = generateUniqueCode();
        String sql = "INSERT INTO user (fullname, dob, email, phone, password, unique_code) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fullname);
            if (dob != null) ps.setDate(2, new java.sql.Date(dob.getTime()));
            else ps.setNull(2, Types.DATE);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, password);
            ps.setString(6, code);
            ps.executeUpdate();
            return code;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Login Admin
    public static String loginAdmin(String username, String password) {
        String sql = "SELECT unique_code FROM admin WHERE username=? AND password=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("unique_code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Login User
    public static String loginUser(String email, String password) {
        String sql = "SELECT unique_code FROM user WHERE email=? AND password=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("unique_code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Forgot password check (Admin)
    public static boolean checkAdminCode(String username, String code) {
        String sql = "SELECT * FROM admin WHERE username=? AND unique_code=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, code);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Forgot password check (User)
    public static boolean checkUserCode(String email, String code) {
        String sql = "SELECT * FROM user WHERE email=? AND unique_code=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, code);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
} 