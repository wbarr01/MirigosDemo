package io.walterbarrantes.studentcourse.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.walterbarrantes.studentcourse.course.CourseDTO;
import io.walterbarrantes.studentcourse.repositories.StudentLogicRepository;
import io.walterbarrantes.studentcourse.repositories.StudentRepository;
import io.walterbarrantes.studentcourse.student.Student;
import io.walterbarrantes.studentcourse.student.StudentDTO;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private StudentLogicRepository studentLogicRepo;
	
	@Override
	public List<StudentDTO> getStudents() {
		List<Student> list = studentRepo.findAll();
		List<StudentDTO> resultList = list.stream().map(stu -> new StudentDTO(stu.getId(),stu.getFirstName(),stu.getLastName())).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public StudentDTO addStudent(StudentDTO student) {
		Student newStudent = new Student();
		newStudent.setFirstName(student.getFirstName());
		newStudent.setLastName(student.getLastName());
		newStudent  = studentRepo.save(newStudent);
		student.setStudentId(newStudent.getId());
		return student;
	}

	@Override
	public void updateStudent(StudentDTO student) {
		studentRepo.save(new Student(student.getStudentId(),student.getFirstName(),student.getLastName()));
		
	}

	@Override
	public void deleteStudent(Integer Id) {
		studentRepo.deleteById(Id);
		
	}

	@Override
	public List<StudentDTO> getStudentsBy(String firstName, String lastName) {
		List<Student> list = studentLogicRepo.findStudentByFields(firstName,lastName);
		List<StudentDTO> resultList = list.stream().map(stu -> new StudentDTO(stu.getId(),stu.getFirstName(),stu.getLastName())).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public List<CourseDTO> getStudentCourses(Integer id) {
		List<CourseDTO> courseList = null;
		Optional<Student> studentOpt = studentRepo.findById(id);
		if (studentOpt.isPresent()) {
			courseList = studentOpt.get().getCourses().stream().map(course -> new CourseDTO(course.getId(), course.getCode(),course.getTitle(),course.getDescription())).collect(Collectors.toList());
		}
		return courseList;
	}

}
