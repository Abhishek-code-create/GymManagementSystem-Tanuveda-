package gymmanagementsystem.model;

/**
 * Model class for personal information data
 */
public class personaldata {
    private String fullName;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private String address;
    private String emergencyContact;
    private String profilePicturePath;

    // Default constructor
    public personaldata() {
        this.fullName = "";
        this.email = "";
        this.phone = "";
        this.dob = "";
        this.gender = "";
        this.address = "";
        this.emergencyContact = "";
        this.profilePicturePath = "";
    }

    // Parameterized constructor
    public personaldata(String fullName, String email, String phone, String dob, 
                       String gender, String address, String emergencyContact) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.profilePicturePath = "";
    }

    // Getters
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    // Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    // Method to validate data
    public boolean isValid() {
        return fullName != null && !fullName.trim().isEmpty() &&
               email != null && !email.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "PersonalData{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                '}';
    }
} 