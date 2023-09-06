package com.unifor.crudspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// A implementação é conectada automaticamente pelo Spring Data JPA .
import com.unifor.crudspring.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByPublished(boolean published); // findByPublished(): retorna todos os cursos com publishedvalor como entrada published.

    List<Course> findByNameContaining(String name); // findByNameContaining(): retorna todos os Cursos cujo título contém input name.
}
