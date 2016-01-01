package com.turalt.openmentor.service;

/**
 * An interface for current user services, that makes it a 
 * little easier to adapt to different authentication systems.
 * 
 * @author morungos
 */
public interface CurrentUserService {
	
	/**
	 * Returns the current user name (if any)
	 * @return the user name
	 */
	String getCurrentUserName();
	
	/**
	 * Returns true if the current user is an adminstrator
	 * @return
	 */
	Boolean isAdministrator();
}
