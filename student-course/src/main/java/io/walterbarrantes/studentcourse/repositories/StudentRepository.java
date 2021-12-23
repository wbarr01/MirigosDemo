package io.walterbarrantes.studentcourse.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.walterbarrantes.studentcourse.student.Student;

public interface StudentRepository extends CrudRepository<Student,Integer>{
	
	public List<Student> findAll();


}
