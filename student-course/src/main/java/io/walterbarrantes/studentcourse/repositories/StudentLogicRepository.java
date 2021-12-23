package io.walterbarrantes.studentcourse.repositories;

import java.util.List;

import io.walterbarrantes.studentcourse.student.Student;

public interface StudentLogicRepository {
	
	List<Student> findStudentByFields(String firstName, String lastName);

}
