package com.turalt.openmentor.dto;

import java.util.Iterator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	private Integer id;

	private String username;
	
	private Boolean administrator = false;
	
	private String email;
		
	public User() { 
		this(SecurityUtils.getSubject());
	}

	public User(String username) {
		setUsername(username);
	}
	
	public User(Subject subject) {
		
		PrincipalCollection principals = subject.getPrincipals();
		
		// First get a string for the username
		@SuppressWarnings("unchecked")
		Iterator<Object> iterator = principals.iterator();
		while(iterator.hasNext()) {
		    Object p = iterator.next();
			if (p instanceof String) {
				setUsername((String) p);
			} else {
				throw new RuntimeException("Unexpected principal type: " + p.getClass().getCanonicalName());
			}
		}
		
		// Fallback in the case of a missing username
		if (getUsername() == null) {
			setUsername(principals.getPrimaryPrincipal().toString());
		}
		
		if (subject.hasRole("ROLE_ADMIN")) {
			setAdministrator(true);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty
	public Boolean getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}

	/**
	 * @return the email
	 */
	@JsonProperty
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}