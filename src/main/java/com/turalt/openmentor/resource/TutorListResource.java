package com.turalt.openmentor.resource;

import com.turalt.openmentor.repository.CourseInfoRepository;

public class TutorListResource extends PersonListResource {

	@Override
	public String getPersonType() {
		return CourseInfoRepository.TUTOR_ROLE;
	}
}
