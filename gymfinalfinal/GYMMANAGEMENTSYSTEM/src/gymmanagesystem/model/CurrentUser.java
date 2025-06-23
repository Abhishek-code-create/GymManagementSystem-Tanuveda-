package gymmanagesystem.model;

public class CurrentUser {
    private static String fullName;
    private static String email;
    private static String phone;

    public static void setUser(String fullName, String email, String phone) {
        CurrentUser.fullName = fullName;
        CurrentUser.email = email;
        CurrentUser.phone = phone;
    }

    public static String getFullName() {
        return fullName;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPhone() {
        return phone;
    }
} 