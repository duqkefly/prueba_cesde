package com.cesde.cursos.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesde.cursos.repository.CursoRepository;
import com.cesde.cursos.repository.DocenteRepository;

// Controlador temporal para probar la conexion con la BD
@RestController
@RequestMapping("/api/prueba")
public class PruebaController {

    private final DocenteRepository docenteRepository;
    private final CursoRepository cursoRepository;

    public PruebaController(DocenteRepository docenteRepository, CursoRepository cursoRepository) {
        this.docenteRepository = docenteRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public Map<String, Object> probar() {
        return Map.of(
                "mensaje", "Conexion a la BD correcta",
                "docentes", docenteRepository.count(),
                "cursos", cursoRepository.count());
    }
}
