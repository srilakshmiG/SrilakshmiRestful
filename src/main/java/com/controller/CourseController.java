package com.controller;

import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.Course;
import com.services.CourseService;

@RestController
@RequestMapping("courses")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@GetMapping
	public List<Course> getAllCourses() throws Exception {
		return courseService.getAllCouses();
	}

	@GetMapping("/{courseId}")
	public ResponseEntity<Course> getCourse(@PathVariable("courseId") Long courseId) throws Exception {
		Course course = courseService.getCourseById(courseId);
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(2, TimeUnit.MINUTES).cachePrivate())
				.lastModified(course.getUpdatedDateTime().toInstant(ZoneOffset.UTC).toEpochMilli()).body(course);
	}

	@PostMapping
	public Course create(@Valid @RequestBody Course course) throws Exception {
		return courseService.createCourse(course);
	}

	@PutMapping
	public Course update(@RequestBody Course course) throws Exception {
		return courseService.updateCourse(course);
	}

	@DeleteMapping(path = "/{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long courseId) throws Exception {
		courseService.deleteCourse(courseId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
