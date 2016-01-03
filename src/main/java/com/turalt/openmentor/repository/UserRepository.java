package com.turalt.openmentor.repository;

import java.util.List;

import com.turalt.openmentor.dto.Pager;
import com.turalt.openmentor.dto.User;

public interface UserRepository {

	/**
	 * Returns the a list of user. 
	 * @return the list of users
	 */
	List<User> getUsers(Pager pager);

	/**
	 * Returns the user count.
	 * @return
	 */
	Long getCourseCount();

	/**
	 * Locates and returns a user by username.
	 * @param username
	 * @return the user
	 */
	User findUser(String username);

	/**
	 * Deletes a user.
	 */
	void deleteUser(User user);

}
