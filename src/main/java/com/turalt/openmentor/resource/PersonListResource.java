package com.turalt.openmentor.resource;

import java.util.List;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

import com.turalt.openmentor.dto.Pager;
import com.turalt.openmentor.dto.Person;
import com.turalt.openmentor.request.RequestAttributes;
import com.turalt.openmentor.response.PersonListResponse;

public abstract class PersonListResource extends CourseInfoResource<PersonListResponse> {

	@Get("json")
    public Representation getResource()  {
		PersonListResponse response = new PersonListResponse();
    	buildResponseDTO(response);
       	return new JacksonRepresentation<PersonListResponse>(response);
    }
	
	public abstract String getPersonType();

	@Override
	public void buildResponseDTO(PersonListResponse dto) {
		super.buildResponseDTO(dto);
		
		String personType = getPersonType();
		dto.setPersonType(personType);
		
		Pager pager = RequestAttributes.getRequestPager(getRequest());
    	List<Person> peopleList = getRepository().getPeople(personType, pager);
    	
    	for(Person p : peopleList) {    		
    		dto.getPeople().add(p);
    	}
	}
}
