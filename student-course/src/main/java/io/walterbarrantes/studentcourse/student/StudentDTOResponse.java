package io.walterbarrantes.studentcourse.student;

import java.util.List;

public class StudentDTOResponse {

	private List<StudentDTO> students;

	public StudentDTOResponse() {
		super();
	}

	public List<StudentDTO> getStudents() {
		return students;
	}

	public void setStudents(List<StudentDTO> students) {
		this.students = students;
	}
	
	
}
