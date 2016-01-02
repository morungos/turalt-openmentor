package com.turalt.openmentor.response;

import java.util.ArrayList;
import java.util.List;

import com.turalt.openmentor.dto.Course;

public class CourseListResponse extends AbstractResponse {

	List<Course> courses = new ArrayList<Course>();
	
	Integer courseCount;

	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	/**
	 * @return the courseCount
	 */
	public Integer getCourseCount() {
		return courseCount;
	}

	/**
	 * @param courseCount the courseCount to set
	 */
	public void setCourseCount(Integer courseCount) {
		this.courseCount = courseCount;
	}
	
	
}
