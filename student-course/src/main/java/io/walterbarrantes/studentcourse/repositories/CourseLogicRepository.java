package io.walterbarrantes.studentcourse.repositories;

import java.util.List;

import io.walterbarrantes.studentcourse.course.Course;

public interface CourseLogicRepository {
	
	List<Course> findCourseByFields(String code, String title, String description);

}
