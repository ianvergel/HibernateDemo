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
			createStudent(studentDao);
			// readStudent(studentDao);
			// queryStudents(studentDao);
			// queryStudentLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudent(studentDao);

			createInstructor(app);
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
			// findCourseAndStudents(app);
			// findStudentAndCourses(app);
			// addMoreCoursesForStudent(app);
			// deleteCourseById(app);
			// deleteStudent(app);
		};
	}

	/**
	 * Deletes a student using the specified student ID.
	 */
	private void deleteStudent(AppDao app) {
		int id = 19;
		System.out.println("Delete student: " + id);

		app.deleteStudentById(id);
		System.out.println("Deleted Successfully!");
	}

	/**
	 * Deletes a course using the specified course ID.
	 */
	private void deleteCourseById(AppDao app) {
		int id = 9;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted successfully!");
	}

	/**
	 * Adds new courses to an existing student's course collection
	 * and updates the student in the database.
	 */
	private void addMoreCoursesForStudent(AppDao app) {
		int id = 19;
		Student student = app.findStudentAndCoursesByStudentId(id);

		Course course1 = new Course("How to Test without Running application");
		Course course2 = new Course("How to Develop without Computer");
		Course course3 = new Course("How to be a Legit Developer");

		student.addCourse(course1);
		student.addCourse(course2);
		student.addCourse(course3);

		System.out.println("Successfully enrolled to new courses!");

		app.update(student);
	}

	/**
	 * Retrieves a student and the courses associated with the student ID.
	 */
	private void findStudentAndCourses(AppDao app) {
		int id = 19;
		Student student = app.findStudentAndCoursesByStudentId(id);

		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());
	}

	/**
	 * Retrieves a course and the students enrolled in the course.
	 */
	private void findCourseAndStudents(AppDao app) {
		int id = 5;

		Course course = app.findCourseAndStudentByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());
	}

	/**
	 * Creates a new course, enrolls students in it, and saves the course
	 * along with its associated students to the database.
	 */
	private void createCourseAndStudents(AppDao app) {
		Course course = new Course("How to be a FullStack Dev in Half a Day");

		Student student1 = new Student
		("Noelle", "Silva", "noelle@clover-kingdom.com");
		Student student2 = new Student
		("Yuno", "Grinberryall", "yuno@clover-kingdom.com");

		course.addStudent(student1);
		course.addStudent(student2);

		app.save(course);
		System.out.println("Successfully enrolled!");
	}

	/**
	 * Deletes a course using its ID. Associated reviews are also deleted
	 * according to the configured cascade and relationship settings.
	 */
	private void deleteCourseAndReviews(AppDao app) {
		int id = 4;
		System.out.println("Delete Course: " + id) ;

		app.deleteCourseById(id);
		System.out.println("Deleted Successfully!");
	}

	/**
	 * Retrieves a course and its associated reviews using the course ID.
	 */
	private void retrieveCourseAndReviews(AppDao app) {
		int id = 3;
		System.out.println("Get courses and reviews from id: " + id);

		Course course = app.findCourseAndReviewsByCourseId(id);

		System.out.println(course);

		System.out.println(course.getReview());
	}

	/**
	 * Creates a new course, adds reviews to it, and saves the course
	 * along with its associated reviews to the database.
	 */
	private void createCourseAndReviews(AppDao app) {
		Course course = new Course("How to Speed Run Spring in 1 Day");

		course.addReview(new Review("Great speed 2x...loved it!!!"));
		course.addReview(new Review("I wanted the speed 0.75x :(("));
		course.addReview(new Review
			("Hoping to do a speed of 3x :D. Anyways nice!"));

		System.out.println("Courses saved!");
		app.save(course);
	}

	/**
	 * Deletes an instructor using the specified instructor ID.
	 */
	private void deleteInstructorById(AppDao app) {
		int id = 11;
		System.out.println("Delete instructor: " + id);

		app.deleteInstructorById(id);
		System.out.println("Deleted Successfully!");
	}

	/**
	 * Retrieves an instructor using the specified ID, modifies the
	 * instructor's last name, and updates the record in the database.
	 */
	private void updateInstructor(AppDao app) {
		int id = 11;

		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		ins.setLastName("Cosma");

		System.out.println("Updated!");
		app.update(ins);
	}

	/**
	 * Retrieves an instructor and eagerly fetches the courses associated
 	 * with the instructor using a JOIN FETCH query.
	 */
	private void findInstructorByIdJoinFetch(AppDao app) {
		int id = 11;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	/**
	 * Fetches record of Courses related to an id of an Instructor.
	 */
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

	/**
	* JAVADOC: Finds an instructor record in the Table using the id initialized
	* @return the Instructor and its Courses handled.
	* 
	* NOTE: if you try to run this one please do change the 
	*fetch type to EARGER in Instructor.java line 39
	*/
	private void findInstructorWithCourses(AppDao app) {
		int id = 10;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	/**
	 * Creates new Instructor and Instructor_Details record and
	 * adds it onto the DB using setInstructorDeets.
	 * Creates two new courses and adds it to the Table.
	 */
	private void createInstructorWithCourses(AppDao app) {
		Instructor ins = new Instructor
		("Asta", "WizardKing", "asta@clover-kingdom.com");
		InstructorDetail deets = new InstructorDetail
		("Anti-Magic", "Being Dumb");

		ins.setInstructorDeets(deets);

		Course course1 = new Course("Will Power");
		Course course2 = new Course("Faith");

		ins.add(course1);
		ins.add(course2);

		System.out.println("Courses saved: " + ins.getCourses());
		app.save(ins);
	}

	/**
	 * Deletes a record using the id initialized.
	 */
	private void deleteInstructorDetail(AppDao app) {
		int id = 10;
		System.out.println("Delete id: " + id);

		app.deleteInstructorDetail(id);
		System.out.println("Deleted Successfully!");
	}

	/**
	 * Finds an instructorDetail by using the ID.
	 * @return the Details of the instructor and the instructor who owns it.
	 */
	private void findInstructorDetailById(AppDao app) {
		int id = 4;
		InstructorDetail inDeets = app.findInstructorDetailById(id);

		System.out.println("Details: " + inDeets);
		System.out.println("Instructor: " + inDeets.getInstructor());
	}

	/**
	 * Deletes an instructor by using the id being initialized.
	 */
	private void deleteInstructorId(AppDao app) {
		int id = 2;
		System.out.println("Delete id: " + id);

		app.deleteInstructorId(id);
		System.out.println("Deleted Succesfully!");
	}

	/**
	 * Find Instructor using the id being initialized.
	 * @return the instructor details.
	 */
	private void findInstructorById(AppDao app) {
		int id = 1;
		System.out.println("Find id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Details: " + ins.getInstructorDeets());
	}

	/**
	 * Creates a new Instructor record and Instructor Detail.
	 */
	private void createInstructor(AppDao app) {
		Instructor ins = new Instructor
		("Vicky", "Magtanggol", "vicky.mags@example.com");
		InstructorDetail deets = new InstructorDetail
		("Math", "Solving Problems");

		ins.setInstructorDeets(deets);

		System.out.println("Information saved!");
		app.save(ins);
	}

	/**
	 * Deletes all the students in the Student Table.
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
	 * @return all the list of students in the database
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
