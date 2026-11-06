package com.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Initializes the creation of the Student Table
 */
@Entity 
@Table(name="student")
public class Student {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;
    
    //default constructor as required by JPA
    public Student() {

    }
    
    //constructor for the Student
    public Student(String firstName, String lastName, String email) {
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

    //getter for first name
    public String getFirstName() {
        return firstName;
    }

    //setter for first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //getter for last name
    public String getLastName() {
        return lastName;
    }

    //setter for last name
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
    
    //converts the object to a string
    @Override 
    public String toString() {
        return "Student [id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email + "]";
    }

}
