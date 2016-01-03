package com.turalt.openmentor.resource;

import java.net.URL;
import java.util.List;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.springframework.beans.factory.annotation.Required;

import com.turalt.openmentor.dto.Pager;
import com.turalt.openmentor.dto.User;
import com.turalt.openmentor.repository.UserRepository;
import com.turalt.openmentor.request.RequestAttributes;
import com.turalt.openmentor.response.UserListResponse;

public class UserListResource extends ServerResource {

	private UserRepository repository;
	
	@Required
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }
	
	public UserRepository getRepository() {
		return repository;
	}

	@Get("json")
    public Representation getResource()  {
		UserListResponse response = new UserListResponse();
    	buildResponseDTO(response);
       	return new JacksonRepresentation<UserListResponse>(response);
    }
	
	public void buildResponseDTO(UserListResponse dto) {
    	URL url = getRequest().getRootRef().toUrl();
    	dto.setServiceUrl(url);
    	
		Pager pager = RequestAttributes.getRequestPager(getRequest());
    	List<User> userList = getRepository().getUsers(pager);
    	
    	for(User c : userList) {    		
    		dto.getUsers().add(c);
    	}
	};

}
