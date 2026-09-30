package com.cesde.cursos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesde.cursos.model.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    List<Docente> findByNombreContainingIgnoreCase(String nombre);

    boolean existsByDocumento(String documento);

    boolean existsByCorreo(String correo);
}
