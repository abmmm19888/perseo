package com.example.Perseo.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Perseo.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Método para encontrar cursos por título (si necesitas esta funcionalidad)
    List<Course> findByTitleContainingIgnoreCase(String title);

    // Método para encontrar cursos por rango de precios (si es necesario)
    List<Course> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Método para encontrar cursos por una palabra clave en la descripción
    List<Course> findByDescriptionContainingIgnoreCase(String keyword);

    // Aquí puedes añadir más métodos personalizados si los necesitas.
}
