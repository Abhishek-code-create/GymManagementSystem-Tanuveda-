package gymmanagementsystem55.model;

public class Client {
    private String name;
    private String profession;
    private String testimonial;
    private String imagePath;

    public Client(String name, String profession, String testimonial, String imagePath) {
        this.name = name;
        this.profession = profession;
        this.testimonial = testimonial;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public String getProfession() { return profession; }
    public String getTestimonial() { return testimonial; }
    public String getImagePath() { return imagePath; }
} 