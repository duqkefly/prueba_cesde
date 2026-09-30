package com.cesde.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesde.cursos.model.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
}
