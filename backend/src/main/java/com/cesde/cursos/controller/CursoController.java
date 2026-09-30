package com.cesde.cursos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cesde.cursos.dto.CursoDTO;
import com.cesde.cursos.service.CursoService;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<CursoDTO> listar() {
        return cursoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoDTO crear(@RequestBody CursoDTO dto) {
        return cursoService.crear(dto);
    }

    @PutMapping("/{id}")
    public CursoDTO actualizar(@PathVariable Long id, @RequestBody CursoDTO dto) {
        return cursoService.actualizar(id, dto);
    }
}
