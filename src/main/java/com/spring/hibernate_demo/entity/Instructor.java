package com.spring.hibernate_demo.entity;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Initializes an instructor table to be created in the DB with
 * its columns and relationships specified.
 */
@Entity 
@Table(name="instructor")
public class Instructor {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private int id;

    @Column (name="first_name")
    private String firstName;

    @Column (name="last_name")
    private String lastName;

    @Column (name="email")
    private String email;

    //Relationship to Instructor_Detail Table with cascading delete.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="instructor_detail_id")

    private InstructorDetail instructorDeets;

    //Relationship to Course Table WITHOUT cascading delete.
    @OneToMany(mappedBy="instructor", fetch=FetchType.LAZY, 
                                    cascade= {CascadeType.DETACH,
                                                CascadeType.MERGE,
                                                CascadeType.PERSIST,
                                                CascadeType.REFRESH})
    public List<Course> courses;

    //setter for Instructor Details
    public void setInstructorDeets(InstructorDetail instructorDeets){
        this.instructorDeets = instructorDeets;
    }

    //getter for Instructor Details
    public InstructorDetail getInstructorDeets() {
        return instructorDeets;
    }

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //getter for id
    public int getId() {
        return id;
    }

    //setter for id
    public void setId(int id) {
        this.id = id;
    }

    //getter for firstName
    public String getFirstName() {
        return firstName;
    }

    //setter for firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //getter for lastName
    public String getLastName() {
        return lastName;
    }

    //setter for lastName
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //getter for email
    public String getEmail() {
        return email;
    }

    //setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    //convenience function to add course
    public void add(Course tcourse) {
        if(this.courses == null) {
            this.courses = new ArrayList<>();
        }

        this.courses.add(tcourse);

        tcourse.setInstructor(this);
    }

    //getter for courses
    public List<Course> getCourses() {
        return courses;
    }

    //setter for courses
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    //converts java object display to string
    @Override 
    public String toString() {
        return "Instructor [id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email + "]";
    }
}
