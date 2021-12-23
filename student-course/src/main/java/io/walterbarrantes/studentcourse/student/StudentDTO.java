package io.walterbarrantes.studentcourse.student;

import java.util.ArrayList;
import java.util.List;

import io.walterbarrantes.studentcourse.course.CourseDTO;

public class StudentDTO {
	
	private Integer studentId;
	private String lastName;
	private String firstName;
	
	private List<CourseDTO> courseList = new ArrayList<>();
	
	public StudentDTO() {
		super();
	}

	public StudentDTO(Integer studentId, String lastName, String firstName) {
		super();
		this.studentId = studentId;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<CourseDTO> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseDTO> courseList) {
		this.courseList = courseList;
	}
	
	
}
