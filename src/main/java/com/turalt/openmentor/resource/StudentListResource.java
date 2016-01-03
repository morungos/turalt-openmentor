package com.turalt.openmentor.resource;

import com.turalt.openmentor.repository.CourseInfoRepository;

public class StudentListResource extends PersonListResource {

	@Override
	public String getPersonType() {
		return CourseInfoRepository.STUDENT_ROLE;
	}
}
