package com.turalt.openmentor.repository.impl;

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

import com.turalt.openmentor.service.CurrentUserService;

import static org.easymock.EasyMock.*;

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
}
