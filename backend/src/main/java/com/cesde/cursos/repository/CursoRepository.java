package com.cesde.cursos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesde.cursos.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByNombreContainingIgnoreCase(String nombre);

    List<Curso> findByFechaInicioBetween(LocalDateTime desde, LocalDateTime hasta);

    boolean existsByDocenteId(Long docenteId);
}
