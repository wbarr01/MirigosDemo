package io.walterbarrantes.studentcourse.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.walterbarrantes.studentcourse.course.Course;

public interface CourseRepository extends CrudRepository<Course,Integer>{
	
	List<Course> findAll();

}
