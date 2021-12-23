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
import io.walterbarrantes.studentcourse.services.StudentService;
import io.walterbarrantes.studentcourse.student.StudentDTO;
import io.walterbarrantes.studentcourse.student.StudentDTOResponse;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<StudentDTOResponse> getStudents(@RequestParam(name="firstName", required=false) String firstName, @RequestParam(name="lastName", required=false) String lastName) {
		
		HttpStatus status = HttpStatus.OK;
		StudentDTOResponse students = null;
		try {
			List<StudentDTO> studentList= null;
			if(!StringUtils.hasLength(firstName) && !StringUtils.hasLength(lastName) ) {
				studentList = studentService.getStudents();
			} else {
				studentList = studentService.getStudentsBy(firstName, lastName);
			}
			
			students = new StudentDTOResponse();
			students.setStudents(studentList);
		} catch (Exception ex) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			ex.printStackTrace();
		}

		return new ResponseEntity<StudentDTOResponse>(students,status);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO student) {
		HttpStatus status = HttpStatus.CREATED;
		StudentDTO result = null;
		try {
			if (student == null) {
				status = HttpStatus.BAD_REQUEST;
			}else {
				result = studentService.addStudent(student);

			}
		}catch(Exception ex) {
			status= HttpStatus.INTERNAL_SERVER_ERROR;
			ex.printStackTrace();
		}
		
		return new ResponseEntity<StudentDTO>(result,null,status);
	}

	@RequestMapping(value="/", method=RequestMethod.PUT)
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO student) {
		HttpStatus status = HttpStatus.OK;
		StudentDTO result = student;
		try {
			if (student == null) {
				status = HttpStatus.BAD_REQUEST;
				result = null;
			}else {
				studentService.updateStudent(student);

			}
		}catch(Exception ex) {
			status= HttpStatus.INTERNAL_SERVER_ERROR;
			ex.printStackTrace();
			result = null;

		}
		return new ResponseEntity<StudentDTO>(student,null,status);
	}

	@RequestMapping(value="/{studentId}", method=RequestMethod.DELETE)
	public ResponseEntity<String> saveStudent(@PathVariable Integer studentId) {
		HttpStatus status = HttpStatus.OK;
		String response = "Student was removed";
		try {
				studentService.deleteStudent(studentId);	
		}catch(Exception ex) {
			status= HttpStatus.INTERNAL_SERVER_ERROR;
			response = "Was not able to remove student";
			ex.printStackTrace();
		}
		return new ResponseEntity<String>(response,null,status);
	}
	
	@RequestMapping(value="/{studentId}/courses", method=RequestMethod.GET)
	public ResponseEntity<CourseDTOResponse> getStudentCourses(@PathVariable Integer studentId) {
		
		HttpStatus status = HttpStatus.OK;
		CourseDTOResponse result = null;
		try {
			List<CourseDTO> courseList= studentService.getStudentCourses(studentId);
			if (!CollectionUtils.isEmpty(courseList)) {
				result = new CourseDTOResponse();
				result.setCourses(courseList);
			}else {
				status = HttpStatus.NO_CONTENT;
			}

		} catch (Exception ex) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			ex.printStackTrace();
		}

		return new ResponseEntity<CourseDTOResponse>(result,null,status);
	}
	
	//@RequestMapping("/")
	//public StudentDTOResponse getStudebtBy(@RequestParam(name="firstName", required=false) String firstName, @RequestParam(name="lastName", required=false) String lastName) {
		//List<StudentDTO> list= studentService.getCourses();
		//StudentDTOResponse courseList = new StudentDTOResponse();
		//courseList.setCourses(list);
	//	return null;
	//}
}
