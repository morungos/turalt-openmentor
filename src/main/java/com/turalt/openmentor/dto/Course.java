package com.turalt.openmentor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {

    private Integer id;

	private Integer ownerId;
	
	private String identifier;
	
	private String title;

	String getIdentifierAndTitle() {
		return identifier + " - " + title;
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
	 * @return the title
	 */
	@JsonProperty
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Defines a standard equality test
	 */
	@Override
	public boolean equals(Object o) {
	    if (o == null)              return false;
	    if (!(o instanceof Course)) return false;

	    Course other = (Course) o;
	    if (this.id != other.id)                          return false;
	    if (this.ownerId != other.ownerId)                return false;
	    if (! this.identifier.equals(other.identifier))   return false;
	    if (! this.title.equals(other.title))             return false;

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
