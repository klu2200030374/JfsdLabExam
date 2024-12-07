package HCQLDemo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cricketer_table")
public class Cricketer {

    @Id
    @Column(name = "cricketer_id")
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "age", nullable = false)
    private double age;

    @Column(name = "batting_style", nullable = false, length = 20)
    private String battingStyle;

    @Column(name = "bowling_style", nullable = false, length = 20)
    private String bowlingStyle;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "contact_number", nullable = false, length = 20, unique = true)
    private String contactNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getBattingStyle() {
        return battingStyle;
    }

    public void setBattingStyle(String battingStyle) {
        this.battingStyle = battingStyle;
    }

    public String getBowlingStyle() {
        return bowlingStyle;
    }

    public void setBowlingStyle(String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Cricketer [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + 
               ", battingStyle=" + battingStyle + ", bowlingStyle=" + bowlingStyle + ", email=" + email + 
               ", contactNumber=" + contactNumber + "]";
    }
}
