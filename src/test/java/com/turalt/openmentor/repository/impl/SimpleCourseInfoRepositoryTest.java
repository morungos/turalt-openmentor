package com.turalt.openmentor.repository.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.turalt.openmentor.dto.Course;
import com.turalt.openmentor.dto.Pager;
import com.turalt.openmentor.dto.Person;
import com.turalt.openmentor.repository.CourseInfoRepository;
import com.turalt.openmentor.service.CurrentUserService;

import static org.easymock.EasyMock.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/testContextDatabase.xml" })
public class SimpleCourseInfoRepositoryTest {
	
	@Autowired
    private SimpleCourseInfoRepository courseInfoRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private CurrentUserService mockCurrentUserService(boolean administrator, String username) {
		CurrentUserService currentUserService = createMock(CurrentUserService.class);
		expect(currentUserService.isAdministrator()).andStubReturn(administrator);
		expect(currentUserService.getCurrentUserName()).andStubReturn(username);
		replay(currentUserService);
		return currentUserService;
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetCourseCount() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "test");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Long courses = courseInfoRepository.getCourseCount();
		Assert.assertEquals(4, courses.intValue());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetCourseCountUser() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "stuart");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Long courses = courseInfoRepository.getCourseCount();
		Assert.assertEquals(5, courses.intValue());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetCourseCountAdministrator() {
		
		CurrentUserService currentUserService = mockCurrentUserService(true, "admin");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Long courses = courseInfoRepository.getCourseCount();
		Assert.assertEquals(6, courses.intValue());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetCourses() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "test");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Pager pager = new Pager();
		List<Course> courses = courseInfoRepository.getCourses(pager);
		Assert.assertEquals(4, courses.size());
		
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("CM2006"))));
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("CM2007"))));
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("CM3010"))));
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("AA1003"))));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetCoursesUser() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "stuart");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Pager pager = new Pager();
		List<Course> courses = courseInfoRepository.getCourses(pager);
		Assert.assertEquals(5, courses.size());
		
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("CM2006"))));
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("CM2007"))));
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("CM3010"))));
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("AA1003"))));
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("CMM511"))));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetCoursesAdministrator() {
		
		CurrentUserService currentUserService = mockCurrentUserService(true, "admin");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Pager pager = new Pager();
		List<Course> courses = courseInfoRepository.getCourses(pager);
		Assert.assertEquals(6, courses.size());
		
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("CM2006"))));
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("CM2007"))));
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("CM3010"))));
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("AA1003"))));
		Assert.assertThat(courses, hasItem(hasProperty("identifier", equalTo("CMM511"))));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindCourse() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "test");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Course found = courseInfoRepository.findCourse("CM2006");
		Assert.assertThat(found, hasProperty("identifier", equalTo("CM2006")));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindCourseMissing() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "test");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Course found = courseInfoRepository.findCourse("XM2006");
		Assert.assertNull(found);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindCourseOtherUser() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "test");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Course found = courseInfoRepository.findCourse("CMM511");
		Assert.assertNull(found);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindCourseUser() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "stuart");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Course found = courseInfoRepository.findCourse("CM2006");
		Assert.assertThat(found, hasProperty("identifier", equalTo("CM2006")));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindCourseUserOwned() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "stuart");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Course found = courseInfoRepository.findCourse("CMM511");
		Assert.assertThat(found, hasProperty("identifier", equalTo("CMM511")));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetStudentCount() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "test");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Long people = courseInfoRepository.getPersonCount(CourseInfoRepository.STUDENT_ROLE);
		Assert.assertEquals(7, people.intValue());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetTutorCount() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "test");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Long people = courseInfoRepository.getPersonCount(CourseInfoRepository.TUTOR_ROLE);
		Assert.assertEquals(3, people.intValue());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetStudentCountUser() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "stuart");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Long people = courseInfoRepository.getPersonCount(CourseInfoRepository.STUDENT_ROLE);
		Assert.assertEquals(8, people.intValue());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetTutorCountUser() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "stuart");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Long people = courseInfoRepository.getPersonCount(CourseInfoRepository.TUTOR_ROLE);
		Assert.assertEquals(4, people.intValue());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetStudentCountAdministrator() {
		
		CurrentUserService currentUserService = mockCurrentUserService(true, "admin");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Long people = courseInfoRepository.getPersonCount(CourseInfoRepository.STUDENT_ROLE);
		Assert.assertEquals(9, people.intValue());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetTutorCountAdministrator() {
		
		CurrentUserService currentUserService = mockCurrentUserService(true, "admin");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Long people = courseInfoRepository.getPersonCount(CourseInfoRepository.TUTOR_ROLE);
		Assert.assertEquals(5, people.intValue());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetStudents() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "test");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Pager pager = new Pager();
		List<Person> people = courseInfoRepository.getPeople(CourseInfoRepository.STUDENT_ROLE, pager);
		Assert.assertEquals(7, people.size());

		Assert.assertThat(people, hasItem(hasProperty("identifier", equalTo("09000231"))));
		Assert.assertThat(people, not(hasItem(hasProperty("identifier", equalTo("09000238")))));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetTutors() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "test");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Pager pager = new Pager();
		List<Person> people = courseInfoRepository.getPeople(CourseInfoRepository.TUTOR_ROLE, pager);
		Assert.assertEquals(3, people.size());

		Assert.assertThat(people, hasItem(hasProperty("identifier", equalTo("M4000061"))));
		Assert.assertThat(people, not(hasItem(hasProperty("identifier", equalTo("M4000064")))));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetStudentsUser() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "stuart");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Pager pager = new Pager();
		List<Person> people = courseInfoRepository.getPeople(CourseInfoRepository.STUDENT_ROLE, pager);
		Assert.assertEquals(8, people.size());

		Assert.assertThat(people, hasItem(hasProperty("identifier", equalTo("09000231"))));
		Assert.assertThat(people, hasItem(hasProperty("identifier", equalTo("09000238"))));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetTutorsUser() {
		
		CurrentUserService currentUserService = mockCurrentUserService(false, "stuart");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Pager pager = new Pager();
		List<Person> people = courseInfoRepository.getPeople(CourseInfoRepository.TUTOR_ROLE, pager);
		Assert.assertEquals(4, people.size());

		Assert.assertThat(people, hasItem(hasProperty("identifier", equalTo("M4000061"))));
		Assert.assertThat(people, hasItem(hasProperty("identifier", equalTo("M4000064"))));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetStudentsAdministrator() {
		
		CurrentUserService currentUserService = mockCurrentUserService(true, "admin");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Pager pager = new Pager();
		List<Person> people = courseInfoRepository.getPeople(CourseInfoRepository.STUDENT_ROLE, pager);
		Assert.assertEquals(9, people.size());

		Assert.assertThat(people, hasItem(hasProperty("identifier", equalTo("09000231"))));
		Assert.assertThat(people, hasItem(hasProperty("identifier", equalTo("09000238"))));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetTutorsAdministrator() {
		
		CurrentUserService currentUserService = mockCurrentUserService(true, "admin");
		courseInfoRepository.setCurrentUserService(currentUserService);
		
		Pager pager = new Pager();
		List<Person> people = courseInfoRepository.getPeople(CourseInfoRepository.TUTOR_ROLE, pager);
		Assert.assertEquals(5, people.size());

		Assert.assertThat(people, hasItem(hasProperty("identifier", equalTo("M4000061"))));
		Assert.assertThat(people, hasItem(hasProperty("identifier", equalTo("M4000064"))));
	}
}
