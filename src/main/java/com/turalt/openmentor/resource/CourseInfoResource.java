package com.turalt.openmentor.resource;

import java.net.URL;

import org.restlet.resource.ServerResource;
import org.springframework.beans.factory.annotation.Required;

import com.turalt.openmentor.repository.CourseInfoRepository;
import com.turalt.openmentor.response.AbstractResponse;

public class CourseInfoResource<T extends AbstractResponse> extends ServerResource {
	
	private CourseInfoRepository repository;
	
	@Required
    public void setRepository(CourseInfoRepository repository) {
        this.repository = repository;
    }
	
	public CourseInfoRepository getRepository() {
		return repository;
	}
	
	public void buildResponseDTO(T dto) {
    	URL url = getRequest().getRootRef().toUrl();
    	dto.setServiceUrl(url);
	};
}
