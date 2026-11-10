package com.spring.hibernate_demo.dao;
import org.springframework.stereotype.Repository;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import com.spring.hibernate_demo.entity.Student;

/**
 * Concrete class that implements the functions initialized in StudentDao
 */
@Repository 
public class StudentDaoImplement implements StudentDao {

    private EntityManager em;

    public StudentDaoImplement (EntityManager em) {
        this.em = em;
    }

    /**
     * Adds a new student record in the Student Table.
     */
    @Override 
    @Transactional 
    public void save(Student theStudent) {
        em.persist(theStudent);
    }

    /**
     * @param id in the application
     * @return the student details of that id.
     */
    @Override 
    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    /**
     * @return all the student in the table ordered by LastName.
     */
    @Override 
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery
        ("FROM Student order by lastName", Student.class);

        return query.getResultList();
    }

    /**
     * @return all student record where the last name
     * is equal to the query value.
     */
    @Override 
    public List<Student> findByLastName(String lastname) {
        TypedQuery<Student> query = em.createQuery
        ("FROM Student WHERE lastName=:data", Student.class);

        query.setParameter("data", lastname);

        return query.getResultList();
    }

    /**
     * Updates a student record in the Student Table.
     */
    @Override 
    @Transactional 
    public void update(Student theStudent) {
        em.merge(theStudent);
    }

    /**
     * Removes a student record in the Student Table.
     */
    @Override 
    @Transactional 
    public void delete(Integer id) {
        Student student = em.find(Student.class, id);

        em.remove(student);
    }

    /**
     * Deletes all records in the Student Table.
     * @return the number of rows deleted.
     */
    @Override 
    @Transactional 
    public int deleteAll() {
        int numRows = em.createQuery("DELETE FROM Student").executeUpdate();
        return numRows;
    }
}
