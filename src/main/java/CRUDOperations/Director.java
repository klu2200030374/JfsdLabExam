package CRUDOperations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="director_table")
public class Director {
    
    @Id
    @Column(name="did")
    private int id;
    
    @Column(name="dname", length=50, nullable=false)
    private String name;
    
    @Column(name="dgender", length=10, nullable=false)
    private String gender;
    
    @Column(name="ddepartment", length=50, nullable=false)
    private String department;
    
    @Column(name="dsalary", nullable=false)
    private double salary;
    
    @Column(name="dcontactno", length=20, nullable=false, unique=true)
    private String contactNo;
    
    @Column(name="dexperience", nullable=false)
    private int experience; 
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
