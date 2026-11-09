package com.spring.hibernate_demo;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Review;
import com.spring.hibernate_demo.entity.Student;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;

@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	/**
	 * Calls the specific functions to be executed.
	 */
	@Bean 
	CommandLineRunner commandLineRunner(StudentDao studentDao, AppDao app) {
		return runner -> {
			// createStudent(studentDao);
			// readStudent(studentDao);
			// queryStudents(studentDao);
			// queryStudentLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudent(studentDao);

			// createInstructor(app);
			// findInstructorById(app);
			// deleteInstructorId(app);
			// findInstructorDetailById(app);
			// deleteInstructorDetail(app);
			// createInstructorWithCourses(app);
			// findInstructorWithCourses(app);
			// findCoursesForInstructor(app);
			// findInstructorByIdJoinFetch(app);
			// updateInstructor(app);
			// deleteInstructorById(app);
			// createCourseAndReviews(app);
			// retrieveCourseAndReviews(app);
			// deleteCourseAndReviews(app);
			// createCourseAndStudents(app);
			findCourseAndStudents(app);
		};
	}

	private void findCourseAndStudents(AppDao app) {
		int id = 8;

		Course course = app.findCourseAndReviewsByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());
	}

	private void createCourseAndStudents(AppDao app) {
		Course course = new Course("How to be a FullStack Dev in Half a Day");

		Student student1 = new Student("Noelle", "Silva", "noelle@clover-kingdom.com");
		Student student2 = new Student("Yuno", "Grinberryall", "yuno@clover-kingdom.com");

		course.addStudent(student1);
		course.addStudent(student2);

		app.save(course);
		System.out.println("Successfully enrolled!");
	}

	private void deleteCourseAndReviews(AppDao app) {
		int id = 4;
		System.out.println("Delete Course: " + id) ;

		app.deleteCourseById(id);
		System.out.println("Deleted Successfully!");
	}

	private void retrieveCourseAndReviews(AppDao app) {
		int id = 3;
		System.out.println("Get courses and reviews from id: " + id);

		Course course = app.findCourseAndReviewsByCourseId(id);

		System.out.println(course);

		System.out.println(course.getReview());
	}

	private void createCourseAndReviews(AppDao app) {
		Course course = new Course("How to Speed Run Spring in 1 Day");

		course.addReview(new Review("Great speed 2x...loved it!!!"));
		course.addReview(new Review("I wanted the speed 0.75x :(("));
		course.addReview(new Review("Hoping to do a speed of 3x :D. Anyways nice!"));

		System.out.println("Courses saved!");
		app.save(course);
	}

	private void deleteInstructorById(AppDao app) {
		int id = 11;
		System.out.println("Delete instructor: " + id);

		app.deleteInstructorById(id);
		System.out.println("Deleted Successfully!");
	}

	private void updateInstructor(AppDao app) {
		int id = 11;

		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		ins.setLastName("Cosma");

		System.out.println("Updated!");
		app.update(ins);
	}

	private void findInstructorByIdJoinFetch(AppDao app) {
		int id = 11;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	private void findCoursesForInstructor(AppDao app) {
		int id = 11;

		System.out.println("Find instructor id: " + id);
		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);

		System.out.println("Courses from id: " + ins);
		List<Course> courses = app.findCoursesByInstructorId(id);

		ins.setCourses(courses);

		System.out.println(ins.getCourses());

	}

	//if you try to run this one please do change the fetch type to EARGER in Instructor.java line 39
	private void findInstructorWithCourses(AppDao app) {
		int id = 10;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	private void createInstructorWithCourses(AppDao app) {
		Instructor ins = new Instructor("Asta", "WizardKing", "asta@clover-kingdom.com");
		InstructorDetail deets = new InstructorDetail("Anti-Magic", "Being Dumb");

		ins.setInstructorDeets(deets);

		Course course1 = new Course("Will Power");
		Course course2 = new Course("Faith");

		ins.add(course1);
		ins.add(course2);

		System.out.println("Courses saved: " + ins.getCourses());
		app.save(ins);
	}

	private void deleteInstructorDetail(AppDao app) {
		int id = 10;
		System.out.println("Delete id: " + id);

		app.deleteInstructorDetail(id);
		System.out.println("Deleted Successfully!");
	}

	private void findInstructorDetailById(AppDao app) {
		int id = 4;
		InstructorDetail inDeets = app.findInstructorDetailById(id);

		System.out.println("Details: " + inDeets);
		System.out.println("Instructor: " + inDeets.getInstructor());
	}

	private void deleteInstructorId(AppDao app) {
		int id = 2;
		System.out.println("Delete id: " + id);

		app.deleteInstructorId(id);
		System.out.println("Deleted Succesfully!");
	}

	private void findInstructorById(AppDao app) {
		int id = 1;
		System.out.println("Find id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Details: " + ins.getInstructorDeets());
	}

	private void createInstructor(AppDao app) {
		Instructor ins = new Instructor("Vicky", "Magtanggol", "vicky.mags@example.com");
		InstructorDetail deets = new InstructorDetail("Math", "Solving Problems");

		ins.setInstructorDeets(deets);

		System.out.println("Information saved!");
		app.save(ins);
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
