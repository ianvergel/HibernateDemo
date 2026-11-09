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

@Entity 
@Table(name="course")
public class Course {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @ManyToOne(cascade= {CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.PERSIST,
                            CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="course_id")
    private List<Review> review;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(name="course_student", joinColumns = @JoinColumn(name="course_id"), inverseJoinColumns = @JoinColumn(name="student_id"))
    private List<Student> students;

    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }

    public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
    }

    public Instructor getInstructor() {
    return instructor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudent(List<Student> students) {
        this.students = students;
    }

    public List<Review> getReview() {
        return review;
    }

    @Override
    public String toString() {
    return "Course [id=" + id +
            ", title=" + title + "]";
    }

    public void addReview(Review rev) {
        if(review == null) {
            review = new ArrayList<>();
        }

        review.add(rev);
    }

    public void addStudent(Student stud) {
        if(students == null) {
            students = new ArrayList<>();
        }
        students.add(stud);
    }
}
