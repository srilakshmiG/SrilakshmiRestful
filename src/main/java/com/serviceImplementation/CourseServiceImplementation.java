package com.serviceImplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Course;
import com.repositories.CourseRepository;
import com.services.CourseService;

@Service
@Transactional
public class CourseServiceImplementation implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public Course createCourse(Course course) throws Exception {
		course.setCreatedDateTime(LocalDateTime.now());
		course.setUpdatedDateTime(LocalDateTime.now());
		return courseRepository.save(course);
	}

	public Course updateCourse(Course course) throws Exception {
		course.setUpdatedDateTime(LocalDateTime.now());
		return courseRepository.save(course);
	}

	public List<Course> getAllCouses() throws Exception {
		return courseRepository.findAll();
	}

	public Course getCourseById(Long courseId) throws Exception {
		return courseRepository.findOne(courseId);
	}

	public void deleteCourse(Long courseId) throws Exception {
		Course course = courseRepository.findOne(courseId);
		if (course == null) {
			throw new Exception("sorry the given courseId-" + courseId + "is not found");
		}
		courseRepository.delete(courseId);

	}

}
