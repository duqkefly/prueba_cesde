package com.cesde.cursos.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cesde.cursos.dto.DocenteDTO;
import com.cesde.cursos.service.DocenteService;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping
    public List<DocenteDTO> listar(@RequestParam(required = false) String nombre) {
        return docenteService.listar(nombre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocenteDTO crear(@Valid @RequestBody DocenteDTO dto) {
        return docenteService.crear(dto);
    }

    @PutMapping("/{id}")
    public DocenteDTO actualizar(@PathVariable Long id, @Valid @RequestBody DocenteDTO dto) {
        return docenteService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        docenteService.eliminar(id);
    }
}
