package com.websystique.springmvc.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.websystique.springmvc.domain.Course;
import com.websystique.springmvc.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	/*
	 * Test data http://localhost:8080/HelloWorldSpringMvc/students/Student2/courses
	 * 
	 * Method is GET  
	 * 
	 * 
	 */
	
	//@GetMapping("/students/{studentId}/courses")
	@RequestMapping(value="/students/{studentId}/courses", method=RequestMethod.GET, produces = "application/json")
	public List<Course> retrieveCoursesForStudent(@PathVariable String studentId) {
		return studentService.retrieveCourses(studentId);
	}

	/*
	 * Test Data : http://localhost:8080/HelloWorldSpringMvc/students/Student2/courses
	 * 
		  {
	        "id": "Course6",
	        "name": "Spring Boot",
	        "description": "6K Students",
	        "steps": [
	            "Learn Maven",
	            "Learn Spring",
	            "Learn Spring MVC",
	            "First Example",
	            "Second Example"
	        ]
			}
	 * 
	 * 
	 */
	
	@PostMapping("/students/{studentId}/courses")
	public ResponseEntity<Void> registerStudentForCourse(
			@PathVariable String studentId, @RequestBody Course newCourse) {

		Course course = studentService.addCourse(studentId, newCourse);

		if (course == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(course.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/students/{studentId}/courses/{courseId}")
	public Course retrieveDetailsForCourse(@PathVariable String studentId,
			@PathVariable String courseId) {
		return studentService.retrieveCourse(studentId, courseId);
	}

}
