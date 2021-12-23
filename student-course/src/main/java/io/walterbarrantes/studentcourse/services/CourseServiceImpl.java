package io.walterbarrantes.studentcourse.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.walterbarrantes.studentcourse.course.Course;
import io.walterbarrantes.studentcourse.course.CourseDTO;
import io.walterbarrantes.studentcourse.repositories.CourseLogicRepository;
import io.walterbarrantes.studentcourse.repositories.CourseRepository;
import io.walterbarrantes.studentcourse.student.Student;
import io.walterbarrantes.studentcourse.student.StudentDTO;


@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private CourseLogicRepository courseLogicRepo;
	
	@Override
	public List<CourseDTO> getCourses() {
		List<Course> list = courseRepo.findAll();
		List<CourseDTO> resultList = list.stream().map(course -> new CourseDTO(course.getId(), course.getCode(),course.getTitle(),course.getDescription())).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public CourseDTO addCourse(CourseDTO course) {
		Course newCourse = new Course();
		newCourse.setCode(course.getCode());
		newCourse.setDescription(course.getDescription());
		newCourse.setTitle(course.getTitle());
		newCourse = courseRepo.save(newCourse);
		course.setId(newCourse.getId());
		return course;
	}

	@Override
	public void updateCourse(CourseDTO course) {
		
		courseRepo.save(new Course(course.getId(),course.getCode(),course.getTitle(),course.getDescription()));
	}

	@Override
	public void deleteCourse(Integer id) {
		courseRepo.deleteById(id);
		
	}

	@Override
	public List<StudentDTO> getCourseStudents(Integer id) {
		List<StudentDTO> studentList = null;
		Optional<Course> courseOpt = courseRepo.findById(id);
		if (courseOpt.isPresent()) {
			studentList = courseOpt.get().getStudents().stream().map(stu -> new StudentDTO(stu.getId(),stu.getFirstName(),stu.getLastName())).collect(Collectors.toList());
		}
		return studentList;
	}

	@Override
	public List<CourseDTO> getCoursesBy(String code, String title, String description) {
		List<Course> list = courseLogicRepo.findCourseByFields(code,title,description);
		List<CourseDTO> resultList = list.stream().map(course -> new CourseDTO(course.getId(), course.getCode(),course.getTitle(),course.getDescription())).collect(Collectors.toList());
		return resultList;
	}

}
