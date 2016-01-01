package com.turalt.openmentor.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;

import com.mysema.query.sql.SQLQuery;
import com.turalt.openmentor.dto.Assignment;
import com.turalt.openmentor.dto.Course;
import com.turalt.openmentor.dto.Person;
import com.turalt.openmentor.repository.CourseInfoRepository;
import com.turalt.openmentor.service.CurrentUserService;

import static com.turalt.openmentor.domain.QCourse.course;
import static com.turalt.openmentor.domain.QUser.user;

public class SimpleCourseInfoRepository implements CourseInfoRepository {

	private final Logger logger = LoggerFactory.getLogger(SimpleCourseInfoRepository.class);

	private QueryDslJdbcTemplate template;
	
	private boolean trainingMode = true;
	
	private CurrentUserService currentUserService;
		
	@Required
    public void setTemplate(QueryDslJdbcTemplate template) {
        this.template = template;
    }
	
	/**
	 * Configures the simple repository for training mode (which means each
	 * user has separate courses, students, and tutors).
	 * @param trainingMode the new training mode
	 */
	public void setTrainingMode(boolean trainingMode) {
		this.trainingMode = trainingMode;
	}

	/**
	 * Initializes the current user service.
	 * @param currentUserService the new CurrentUserService
	 */
	public void setCurrentUserService(CurrentUserService currentUserService) {
		this.currentUserService = currentUserService;
	}

	@Override
	public List<Course> getCourses() {
		SQLQuery sq = template.newSqlQuery().from(course);
		
		// If in training mode, use the owner to filter out non-applicable courses.
		if (trainingMode && ! currentUserService.isAdministrator()) {
			String username = currentUserService.getCurrentUserName();
			sq = sq.leftJoin(user).on(user.id.eq(course.ownerId))
					.where(user.id.isNull().or(user.username.eq(username)));
		}
		
		return template.query(sq, course);
	}

	@Override
	public Long getCourseCount() {
		SQLQuery sq = template.newSqlQuery().from(course);
		
		// If in training mode, use the owner to filter out non-applicable courses.
		if (trainingMode && ! currentUserService.isAdministrator()) {
			String username = currentUserService.getCurrentUserName();
			sq = sq.leftJoin(user).on(user.id.eq(course.ownerId))
					.where(user.id.isNull().or(user.username.eq(username)));
		}
		
		return template.count(sq);
	}

	@Override
	public Course findCourse(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> findCoursesLike(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getPeople(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getPersonCount(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findPerson(String role, String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findPeopleLike(String role, String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assignment> getAssignments(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAssignmentCount(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment findAssignment(Course course, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assignment> findAssignmentsLike(Course course, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initializeCourse(Course course) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializePerson(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeAssignment(Assignment assignment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCourse(Course course) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteStudent(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAssignment(Assignment assignment) {
		// TODO Auto-generated method stub

	}

}