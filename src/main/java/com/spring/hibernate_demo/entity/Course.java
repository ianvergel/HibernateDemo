package com.spring.hibernate_demo.entity;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Initializes a course table to be created in the DB with
 * its columns and relationships specified.
 */
@Entity 
@Table(name="course")
public class Course {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    //Relationship to instructor Table WITHOUT cascading delete.
    @ManyToOne(cascade= {CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.PERSIST,
                            CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    //Relationship to other Review Table with cascading delete.
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="course_id")
    private List<Review> review;

    //Relationship to Course Table WITHOUT cascading delete.
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
                                                    CascadeType.MERGE,
                                                    CascadeType.REFRESH,
                                                    CascadeType.PERSIST})
    @JoinTable(name="course_student", 
    joinColumns = @JoinColumn(name="course_id"), 
    inverseJoinColumns = @JoinColumn(name="student_id"))
    private List<Student> students;

    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }

    //setter for instructor list
    public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
    }

    //getter for instructor list
    public Instructor getInstructor() {
    return instructor;
    }

    //getter for student list
    public List<Student> getStudents() {
        return students;
    }

    //setter for student list
    public void setStudent(List<Student> students) {
        this.students = students;
    }

    //getter for review
    public List<Review> getReview() {
        return review;
    }

    //converts java object display to string
    @Override
    public String toString() {
    return "Course [id=" + id +
            ", title=" + title + "]";
    }

    //convenience function to add review
    public void addReview(Review rev) {
        if(review == null) {
            review = new ArrayList<>();
        }

        review.add(rev);
    }

    //convenience function to add student
    public void addStudent(Student stud) {
        if(students == null) {
            students = new ArrayList<>();
        }
        students.add(stud);
    }
}
