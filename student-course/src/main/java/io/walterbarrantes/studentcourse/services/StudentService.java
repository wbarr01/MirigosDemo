package io.walterbarrantes.studentcourse.services;

import java.util.List;

import io.walterbarrantes.studentcourse.course.CourseDTO;
import io.walterbarrantes.studentcourse.student.StudentDTO;


public interface StudentService {

	public List<StudentDTO> getStudents();
	
	public StudentDTO addStudent(StudentDTO student);
	
	public void updateStudent(StudentDTO student);
	
	public void deleteStudent(Integer Id);
	
	public List<StudentDTO> getStudentsBy(String firstName, String LastName);
	
	public List<CourseDTO> getStudentCourses(Integer Id);
	

}
