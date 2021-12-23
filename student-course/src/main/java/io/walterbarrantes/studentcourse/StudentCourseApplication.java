package io.walterbarrantes.studentcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages="io.walterbarrantes")
@SpringBootApplication
//@ComponentScan(basePackages="io.walterbarrantes.studentcourse")
public class StudentCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCourseApplication.class, args);
	}

}
