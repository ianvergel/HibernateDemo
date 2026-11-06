package com.spring.hibernate_demo;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Student;

@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	/**
	 * Calls the specific functions to be executed.
	 */
	@Bean 
	CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			createStudent(studentDao);
			// readStudent(studentDao);
			// queryStudents(studentDao);
			// queryStudentLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudent(studentDao);
		};
	}

	/**
	 * Deletes all the students in the Student Table
	 */
	private void deleteAllStudent(StudentDao studentDao) {
		System.out.println("Deleting all student information...");
		int rowsDeleted = studentDao.deleteAll();
		System.out.println("Number of deleted rows: " + rowsDeleted);
	}

	/**
	 * Deletes all the a student in the Table that
	 * is equal to the id initialized.
	 */
	private void deleteStudent(StudentDao studentDao) {
		int id = 11;
		System.out.println("Delete ID: " + id);

		studentDao.delete(id);
	}

	/**
	 * Updates specific record by querying with the specific id.
	 */
	private void updateStudent(StudentDao studentDao) {
		int id = 9;

		System.out.println("Student ID: " + id);
		Student student = studentDao.findById(id);

		System.out.println("Updating student name...");
		student.setFirstName("Dark");

		studentDao.update(student);

		System.out.println("Updated student information: " + student);
	}

	/**
	 * Fetches the student from the database by querying the lastname.
	 */
	private void queryStudentLastName(StudentDao studentDao) {
		List<Student> students = studentDao.findByLastName("WizardKing");

		for(Student s:students) {
			System.out.println(s);
		}
	}

	/**
	 * Return all the list of students in the database
	 */
	private void queryStudents(StudentDao studentDao) {
		List<Student> students = studentDao.findAll();

		for(Student s:students) {
			System.out.println(s);
		}
	}

	/**
	 * Finds specific students by referencing on the declared id.
	 */
	private void readStudent(StudentDao studentDao) {
		System.out.println("Retrieving student id 2...");
		Student student = studentDao.findById(2);

		System.out.println("Found the Student: " + student);
	}

	/**
	 * Creates a new student by saving the details with the new
	 * Student Object onto the database.
	 */
	private void createStudent(StudentDao studentDao) {
		System.out.println("Creating student information...");
		Student student = new Student("Magna", "Swing",
		"magna@cloverkingdom.com");

		System.out.println("Saving student information...");
		studentDao.save(student);

		System.out.println("Saved! Generated id: " + student.getId());
	}
}
