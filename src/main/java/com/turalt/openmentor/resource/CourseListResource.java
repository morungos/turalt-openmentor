package com.turalt.openmentor.resource;

import java.util.List;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

import com.turalt.openmentor.dto.Course;
import com.turalt.openmentor.dto.Pager;
import com.turalt.openmentor.request.RequestAttributes;
import com.turalt.openmentor.response.CourseListResponse;

public class CourseListResource extends CourseInfoResource<CourseListResponse> {

	@Get("json")
    public Representation getResource()  {
		CourseListResponse response = new CourseListResponse();
    	buildResponseDTO(response);
       	return new JacksonRepresentation<CourseListResponse>(response);
    }
	
	@Override
	public void buildResponseDTO(CourseListResponse dto) {
		super.buildResponseDTO(dto);
		
		Pager pager = RequestAttributes.getRequestPager(getRequest());
    	List<Course> courseList = getRepository().getCourses(pager);
    	
    	for(Course c : courseList) {    		
    		dto.getCourses().add(c);
    	}
	}
}
