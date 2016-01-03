package com.turalt.openmentor.response;

import java.util.ArrayList;
import java.util.List;

import com.turalt.openmentor.dto.User;

public class UserListResponse extends AbstractResponse {

	List<User> users = new ArrayList<User>();
	
	Integer userCount;

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @return the userCount
	 */
	public Integer getUserCount() {
		return userCount;
	}

	/**
	 * @param userCount the userCount to set
	 */
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	
	
}
