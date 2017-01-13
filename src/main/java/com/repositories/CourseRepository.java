package com.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dto.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	public Optional<Course> findByName(String courseName);

	public List<Course> findByAuthor(String author);

	public Long countByName(String courseName);

	public Optional<Course> findByNameAndAuthor(String courseName, String author);

	public List<Course> findByNameOrAuthor(String courseName, String author);

	public List<Course> findDistinctByType(String courseType);

	public List<Course> findFirst3ByNameOrderByNameAsc(String courseName);

}
