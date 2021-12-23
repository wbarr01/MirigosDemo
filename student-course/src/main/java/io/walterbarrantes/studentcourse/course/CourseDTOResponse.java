package io.walterbarrantes.studentcourse.course;

import java.util.List;

public class CourseDTOResponse {

	private List<CourseDTO> courses;

	public CourseDTOResponse() {
		super();
	}

	public List<CourseDTO> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDTO> courses) {
		this.courses = courses;
	}
	
	
}
