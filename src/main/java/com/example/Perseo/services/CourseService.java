package com.example.Perseo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Perseo.models.Course;
import com.example.Perseo.repositories.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Obtiene todos los cursos
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Obtiene un curso por ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Guarda un nuevo curso o actualiza uno existente
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // Elimina un curso por ID
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
