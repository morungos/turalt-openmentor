package com.turalt.openmentor.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    private Integer id;

	private Integer ownerId;
	
	private Integer role;
	
	private String identifier;
    
	private String givenName;
    
	private String familyName;
    
	private Set<Course> courses;
	
	String getName() {
        return givenName + " " + familyName;
    }

    String getIdentifierAndName() {
        return identifier + " - " + givenName + " " + familyName;
    }

	/**
	 * @return the id
	 */
    @JsonProperty
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the ownerId
	 */
	@JsonProperty
	public Integer getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the role
	 */
	public Integer getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Integer role) {
		this.role = role;
	}

	/**
	 * @return the identifier
	 */
	@JsonProperty
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the givenName
	 */
	@JsonProperty
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the familyName
	 */
	@JsonProperty
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the courses
	 */
	public Set<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	/**
	 * Defines a standard equality test
	 */
	@Override
	public boolean equals(Object o) {
	    if (o == null)              return false;
	    if (!(o instanceof Course)) return false;

	    Person other = (Person) o;
	    if (this.id != other.id)                          return false;
	    if (this.ownerId != other.ownerId)                return false;
	    if (! this.identifier.equals(other.identifier))   return false;
	    if (! this.role.equals(other.role))               return false;
	    if (! this.givenName.equals(other.givenName))     return false;
	    if (! this.familyName.equals(other.familyName))   return false;

	    return true;
	}
	
	/**
	 * Defines a standard hashCode
	 */
	@Override
	public int hashCode() {
		return (this.id == null ? 1 : this.id) * this.identifier.hashCode();
	}
}
