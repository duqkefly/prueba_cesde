package com.cesde.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesde.cursos.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
