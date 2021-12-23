package io.walterbarrantes.studentcourse.student;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import io.walterbarrantes.studentcourse.course.Course;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String lastName;
	private String firstName;
	
	@ManyToMany
	private Set<Course> courses;
	
	public Student() {
		super();
	}

	public Student(Integer studentId, String lastName, String firstName) {
		super();
		this.id = studentId;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer studentId) {
		this.id = studentId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	
}
