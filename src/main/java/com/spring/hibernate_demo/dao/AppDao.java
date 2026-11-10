package com.spring.hibernate_demo.dao;
import java.util.List;

import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;
import com.spring.hibernate_demo.entity.Course;

public interface AppDao {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorId(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetail(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor in);
    void deleteInstructorById(int theId);
    void save(Course theCourse);
    Course findCourseAndReviewsByCourseId(int theId);
    void deleteCourseById(int theId);
    Course findCourseAndStudentByCourseId(int theId);
    Student findStudentAndCoursesByStudentId(int theId);
    void update(Student student);
    void deleteStudentById(int theId);
}
