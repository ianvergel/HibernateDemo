package com.spring.hibernate_demo.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * Intializes a class called AppDaoImplement where it
 * creates a entity em to manage the conversation with the DB.
 * It contains all functions initialized in AppDao.java.
 */
@Repository 
public class AppDaoImplement implements AppDao {

    private EntityManager em;

    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    /**
     * Saves the instructor information to the DB.
     */
    @Override 
    @Transactional 
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    /**
     * @param id in the application
     * @return the details of the Instructor
     */
    @Override 
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    /**
     * @param id in the application
     * Finds the instructor by using the id and them
     * passes that details as in and them removed.
     */
    @Override 
    @Transactional 
    public void deleteInstructorId(int theId) {
        Instructor in = em.find(Instructor.class, theId);

        em.remove(in);
    }

    /**
     * @param id in the application
     * @return returns the details of the instructor 
     * including "hobby" and "subject".
     */
    @Override 
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    /**
     * @param id in the application
     * Finds the instructor detail id and once found, 
     * it deletes that instructor_detail including
     * the instructor itself (cascade delete).
     */
    // @Override 
    // @Transactional 
    // public void deleteInstructorDetail(int theId) {
    //     InstructorDetail deets = em.find(InstructorDetail.class, theId);

    //     em.remove(deets);
    // }

    /**
     * @param id in the application
     * Finds the instructor detail id and once found, 
     * it deletes that instructor _details but not including
     * the instructor rather it sets the instructor_detail_id
     * in Instructor Table to null (delete without cascade).
     */
    @Override 
    @Transactional 
    public void deleteInstructorDetail(int theId) {
        InstructorDetail deets = em.find(InstructorDetail.class, theId);

        deets.getInstructor().setInstructorDeets(null);
        
        em.remove(deets);
    }

    /**
     * @param id in the application
     * @return the list courses in by using the instructor id.
     */
    @Override 
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = em.createQuery
        ("FROM Course WHERE instructor.id=:data", Course.class);
        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();
        return courses;
    }

    /**
     * @param id in the application
     * @return the details of the Instructor both in the
     * Courses Table and in Instructor Detail Table.
     */
    @Override 
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = em.createQuery
        ("SELECT i from Instructor i " + 
        "JOIN FETCH i.courses " + 
        "JOIN FETCH i.instructorDeets " + 
        "WHERE i.id=:data", Instructor.class);

        query.setParameter("data", theId);

        Instructor in = query.getSingleResult();
        return in;
    }

    /**
     * Updates a specific column of the Instructor such as firstName and etc.
     */
    @Override 
    @Transactional 
    public void update(Instructor in) {
        em.merge(in);
    }

    /**
     * @param id in the application
     * Removes an instructor without cascading the records in Courses.
     * If the Instructor is removed the function loops to the courses 
     * that has the id of the instructor and sets instructor_id to null.
     */
    @Override 
    @Transactional 
    public void deleteInstructorById(int theId) {
        Instructor in = em.find(Instructor.class, theId);

        List<Course> courses = in.getCourses();
        for (Course c: courses) {
            c.setInstructor(null);
        }

        em.remove(in);
    }

    /**
     * Adds new courses to the Course Table.
     */
    @Override 
    @Transactional 
    public void save(Course theCourse) {
        em.persist(theCourse);
    }

    /**
     * @param id in the application
     * @return the Course detail including
     * those reviews of that specific course.
     */
    @Override 
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery
        ("SELECT c FROM Course c " + 
        "JOIN FETCH c.review " + 
        "WHERE c.id=:data", Course.class);
        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }

    /**
     * @param id in the application
     * Removes a course from the Table.
     */
    @Override 
    @Transactional 
    public void deleteCourseById(int theId) {
        Course course = em.find(Course.class, theId);

        em.remove(course);
    }

    /**
     * @param id in the application
     * @return the Course details and including 
     * the students that is enrolled in that course.
     */
    @Override 
    public Course findCourseAndStudentByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery
        ("SELECT c FROM Course c " + 
        "JOIN FETCH c.students " + 
        "WHERE c.id=:data", Course.class);
        query.setParameter("data", theId);

        Course course = query.getSingleResult();
        return course;
    }

    /**
     * @param id in the application
     * @return the Student Details and
     * the courses that the student is enrolled at.
     */
    @Override 
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student> query = em.createQuery
        ("SELECT s FROM Student s " + 
        "JOIN FETCH s.courses " + 
        "WHERE s.id=:data", Student.class);
        query.setParameter("data", theId);

        Student student = query.getSingleResult();

        return student;
    }

    /**
     * Updates the Student_Course Table to add the
     * student id and the course id of the new courses added.
     */
    @Override 
    @Transactional 
    public void update(Student student) {
        em.merge(student);
    }

    /**
     * @param id in the application
     * Deletes a student from the table without cascading
     * the courses he or she is enrolled at,
     * where if student is removed all student id is changed to null.
     */
    @Override 
    @Transactional 
    public void deleteStudentById(int theId) {
        Student student = em.find(Student.class, theId);

        if(student != null) {
            List<Course> courses = student.getCourses();

            for(Course c: courses) {
                c.getStudents().remove(student);
            }
        }

        em.remove(student);
    }
}
