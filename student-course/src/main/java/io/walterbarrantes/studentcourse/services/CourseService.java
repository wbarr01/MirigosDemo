package io.walterbarrantes.studentcourse.services;

import java.util.List;

import io.walterbarrantes.studentcourse.course.CourseDTO;
import io.walterbarrantes.studentcourse.student.StudentDTO;


public interface CourseService {
 
	public List<CourseDTO> getCourses();
	
	public CourseDTO addCourse(CourseDTO course);
	
	public void updateCourse(CourseDTO course);
	
	public void deleteCourse(Integer id) ;
	
	public List<StudentDTO> getCourseStudents(Integer id);
	
	public List<CourseDTO> getCoursesBy(String code, String title,String description);

}
