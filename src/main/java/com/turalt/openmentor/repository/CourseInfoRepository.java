package com.turalt.openmentor.repository;

import java.util.List;

import com.turalt.openmentor.dto.Assignment;
import com.turalt.openmentor.dto.Course;
import com.turalt.openmentor.dto.Person;

public interface CourseInfoRepository {
	
	public static final String STUDENT_ROLE = "student";
	
	public static final String TUTOR_ROLE = "tutor";
	
	/**
	 * Returns the a list of courses. 
	 * @return the list of courses
	 */
	List<Course> getCourses();
	
	/**
	 * Returns the course count
	 * @return
	 */
	Long getCourseCount();
	
	/**
	 * Locates and returns a course by identifier
	 * @param courseId
	 * @return the course
	 */
	Course findCourse(String identifier);
	
	/**
	 * Locates and returns a list of courses by like pattern
	 * @param courseId
	 * @return a list of courses
	 */
	List<Course> findCoursesLike(String identifier);
	
	/**
	 * Returns the a list of people. 
	 * @return the list of people
	 */
    List<Person> getPeople(String role); 
    
    /**
	 * Returns the person count
	 * @return
	 */
    Long getPersonCount(String role);
	
	/**
	 * Locates and returns a person by identifier
	 * @param studentId
	 * @return the student
	 */
	Person findPerson(String role, String identifier);
	
	/**
	 * Locates and returns a list of people by like pattern
	 * @param identifier
	 * @return a list of people
	 */
	List<Person> findPeopleLike(String role, String identifier);
	
	/**
	 * Returns the a list of assignments for a given course. 
	 * @param params a map of filtering features
	 * @return the list of assignments
	 */
	List<Assignment> getAssignments(Course course);
	
	/**
	 * Returns the course count of assignments for a given course
	 * @return
	 */
	Long getAssignmentCount(Course course);
	
	/**
	 * Locates and returns an assignment for a given course by identifier
	 * @param code
	 * @return the assignment
	 */
	Assignment findAssignment(Course course, String code);
	
	/**
	 * Locates and returns a list of assignments for a given course by like pattern
	 * @param code
	 * @return a list of assignments
	 */
	List<Assignment> findAssignmentsLike(Course course, String code);
	
	/**
	 * Initializes a course with the appropriate owner, if required.
	 * @param course
	 */
	void initializeCourse(Course course);
	
	/**
	 * Initializes a person with the appropriate owner, if required.
	 * @param student
	 */
	void initializePerson(Person person);
	
	/**
	 * Initializes an assignment with the appropriate owner, if required.
	 * @param assignment
	 */
	void initializeAssignment(Assignment assignment);
	
	/**
	 * Deletes a course with the appropriate owner, if required.
	 */
	void deleteCourse(Course course);
	
	/**
	 * Deletes a person with the appropriate owner, if required.
	 */
	void deleteStudent(Person person);
	
	/**
	 * Deletes an assignment with the appropriate owner, if required.
	 */
	void deleteAssignment(Assignment assignment);
}
