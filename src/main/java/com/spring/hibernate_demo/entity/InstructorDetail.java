package com.spring.hibernate_demo.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Initializes an instructor_detail table to be created in the DB with
 * its columns and relationships specified.
 */
@Entity 
@Table(name="instructor_detail")
public class InstructorDetail {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private int id;

    @Column (name="subject")
    private String subject;

    @Column (name="hobby")
    private String hobby;

    /**
    * Relationship of InstructorDetail to Instructor Table
    * with cascading delete.
    */
    // @OneToOne(mappedBy = "instructorDeets", cascade = CascadeType.ALL)

    /**
    * Relationship of InstructorDetail to Instructor Table 
    * WITHOUT cascading delete.
    */
    @OneToOne(mappedBy = "instructorDeets", cascade = {CascadeType.DETACH,
                                                       CascadeType.MERGE,
                                                       CascadeType.PERSIST,
                                                       CascadeType.REFRESH})
    private Instructor instructor;

    public InstructorDetail() {

    }

    public InstructorDetail(String subject, String hobby) {
        this.subject = subject;
        this.hobby = hobby;
    }

    //getter for Instructor
    public Instructor getInstructor() {
        return instructor;
    }
    //getter for id
    public int getId() {
        return id;
    }

    //setter for id
    public void setId(int id) {
        this.id = id;
    }

    //getter for subject
    public String getSubject() {
        return subject;
    }

    //setter for subject
    public void setSubject(String subject) {
        this.subject = subject;
    }

    //getter for hobby
    public String getHobby() {
        return hobby;
    }

    //setter for hobby
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    //converts java object display to string
    @Override
    public String toString() {
    return "InstructorDetail [" +
            "id=" + id +
            ", subject='" + subject + '\'' +
            ", hobby='" + hobby + '\'' +
            ']';
}
}
