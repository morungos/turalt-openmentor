package com.turalt.openmentor.response;

import java.util.ArrayList;
import java.util.List;

import com.turalt.openmentor.dto.Person;

public class PersonListResponse extends AbstractResponse {

	List<Person> people = new ArrayList<Person>();
	
	String personType;
	
	Integer personCount;

	/**
	 * @return the people
	 */
	public List<Person> getPeople() {
		return people;
	}

	/**
	 * @param people the people to set
	 */
	public void setPeople(List<Person> people) {
		this.people = people;
	}

	/**
	 * @return the personCount
	 */
	public Integer getPersonCount() {
		return personCount;
	}

	/**
	 * @param personCount the personCount to set
	 */
	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	/**
	 * @return the personType
	 */
	public String getPersonType() {
		return personType;
	}

	/**
	 * @param personType the personType to set
	 */
	public void setPersonType(String personType) {
		this.personType = personType;
	}

}
