package com.example.Perseo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Perseo.models.WorkExperience;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
    // Aquí puedes añadir métodos personalizados si los necesitas.
}
