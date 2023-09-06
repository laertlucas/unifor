package com.unifor.crudspring.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unifor.crudspring.model.Course;
import com.unifor.crudspring.repository.CourseRepository;

@CrossOrigin(origins = "http://localhost:8080") // @CrossOriginserve para configurar origens permitidas.
@RestController // @RestControllera anotação é usada para definir um controlador e para indicar que o valor de retorno dos métodos deve ser vinculado ao corpo da resposta da web.
@RequestMapping("/api") // @RequestMapping("/api") declara que todas as URLs das Apis no controlador começarão com /api.
public class CourseController {
    
    @Autowired // @Autowiredpara injetar CourseRepository bean na variável local.
    CourseRepository courseRepository;

    @GetMapping("/courses")
    public ResponseEntity <List<Course>> getAllCourses(@RequestParam(required = false) String name) {
        try {
            List<Course> courses = new ArrayList<Course>();

			if (name == null)
				courseRepository.findAll().forEach(courses::add);
			else
				courseRepository.findByNameContaining(name).forEach(courses::add);

			if (courses.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(courses, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @GetMapping("/Courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") long id) {
    Optional<Course> courseData = courseRepository.findById(id);
		if (courseData.isPresent()) {
			return new ResponseEntity<>(courseData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

    @PostMapping("/Courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course _course = courseRepository
                    .save(new Course(course.getName(), course.getCategory(), false));
            return new ResponseEntity<>(_course, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course course) {
    Optional<Course> courseData = courseRepository.findById(id);

		if (courseData.isPresent()) {
			Course _course = courseData.get();
			_course.setName(course.getName());
			_course.setCategory(course.getCategory());
			_course.setPublished(course.isPublished());
			return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
}

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id) {
        try {
            courseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/Courses")
    public ResponseEntity<HttpStatus> deleteAllCourse() {
        try {
            courseRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }   
    }

    @GetMapping("/courses/published")
    public ResponseEntity<List<Course>> findByPublished() {
        try {
            List<Course> courses = courseRepository.findByPublished(true);

            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
