package com.turalt.openmentor.repository.impl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.turalt.openmentor.dto.Pager;
import com.turalt.openmentor.dto.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/testContextDatabase.xml" })
public class SimpleUserRepositoryTest {

	@Autowired
    private SimpleUserRepository userRepository;

	@Test
	@Transactional
	@Rollback(true)
	public void testGetUserCount() {
		
		Long users = userRepository.getCourseCount();
		Assert.assertEquals(3, users.intValue());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetUsers() {
		
		Pager pager = new Pager();
		List<User> users = userRepository.getUsers(pager);
		Assert.assertEquals(3, users.size());
		
		Assert.assertThat(users, hasItem(hasProperty("username", equalTo("admin"))));
		Assert.assertThat(users, hasItem(hasProperty("username", equalTo("stuart"))));
		Assert.assertThat(users, hasItem(hasProperty("username", equalTo("morag"))));
	}

}
