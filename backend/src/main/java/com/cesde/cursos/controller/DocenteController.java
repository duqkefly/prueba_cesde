package com.cesde.cursos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<DocenteDTO> listar() {
        return docenteService.listar();
    }
}
