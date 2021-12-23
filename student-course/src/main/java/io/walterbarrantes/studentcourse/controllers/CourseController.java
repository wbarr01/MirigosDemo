package io.walterbarrantes.studentcourse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.walterbarrantes.studentcourse.course.CourseDTO;
import io.walterbarrantes.studentcourse.course.CourseDTOResponse;
import io.walterbarrantes.studentcourse.services.CourseService;
import io.walterbarrantes.studentcourse.student.StudentDTO;
import io.walterbarrantes.studentcourse.student.StudentDTOResponse;



@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@RequestMapping("/")
	public ResponseEntity<CourseDTOResponse> getCourses(@RequestParam(name="code", required=false) String code,@RequestParam(name="title", required=false) String title,@RequestParam(name="description", required=false) String description) {
		
		HttpStatus status = HttpStatus.OK;
		CourseDTOResponse courseList = null;
		try {
			List<CourseDTO> list= null;
			if(!StringUtils.hasLength(code) && !StringUtils.hasLength(title) && !StringUtils.hasLength(description)) {
				list = courseService.getCourses();
			}else {
				list = courseService.getCoursesBy(code, title, description);
			}
			courseList = new CourseDTOResponse();
			courseList.setCourses(list);
		} catch (Exception ex) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			ex.printStackTrace();
		}

		return new ResponseEntity<CourseDTOResponse>(courseList,null,status);
	}
	

	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO course) {
		HttpStatus status = HttpStatus.CREATED;
		CourseDTO result = null;
		try {
			if (course == null) {
				status = HttpStatus.BAD_REQUEST;
			}else {
				result = courseService.addCourse(course);

			}
		}catch(Exception ex) {
			status= HttpStatus.INTERNAL_SERVER_ERROR;
			ex.printStackTrace();
		}
		
		return new ResponseEntity<CourseDTO>(result,null,status);	
	}

	@RequestMapping(value="/", method=RequestMethod.PUT)
	public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO course) {
		HttpStatus status = HttpStatus.OK;
		CourseDTO result = course;
		try {
			if (course == null) {
				status = HttpStatus.BAD_REQUEST;
				result = null;
			}else {
				courseService.updateCourse(course);
			}
		}catch(Exception ex) {
			status= HttpStatus.INTERNAL_SERVER_ERROR;
			ex.printStackTrace();
			result = null;

		}
		return new ResponseEntity<CourseDTO>(result,null,status);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> saveCourse(@PathVariable Integer id) {
		HttpStatus status = HttpStatus.OK;
		String response = "Course was removed";
		try {
			courseService.deleteCourse(id);
		}catch(Exception ex) {
			status= HttpStatus.INTERNAL_SERVER_ERROR;
			response = "Was not able to remove course";
			ex.printStackTrace();
		}
		return new ResponseEntity<String>(response,null,status);
	}
	
	@RequestMapping(value="/{courseId}/students", method=RequestMethod.GET)
	public ResponseEntity<StudentDTOResponse> getStudentCourses(@PathVariable Integer courseId) {
		
		HttpStatus status = HttpStatus.OK;
		StudentDTOResponse result = null;
		try {
			List<StudentDTO> studentList= courseService.getCourseStudents(courseId);
			if (!CollectionUtils.isEmpty(studentList)) {
				result = new StudentDTOResponse();
				result.setStudents(studentList);
			}else {
				status = HttpStatus.NO_CONTENT;
			}

		} catch (Exception ex) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			ex.printStackTrace();
		}

		return new ResponseEntity<StudentDTOResponse>(result,null,status);
	}
}
