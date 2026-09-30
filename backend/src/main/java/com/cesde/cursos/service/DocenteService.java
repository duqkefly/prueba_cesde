package com.cesde.cursos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesde.cursos.dto.DocenteDTO;
import com.cesde.cursos.model.Docente;
import com.cesde.cursos.repository.DocenteRepository;

@Service
public class DocenteService {

    private final DocenteRepository docenteRepository;

    public DocenteService(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    public List<DocenteDTO> listar() {
        return docenteRepository.findAll().stream().map(this::toDTO).toList();
    }

    private DocenteDTO toDTO(Docente docente) {
        return new DocenteDTO(docente.getId(), docente.getNombre(), docente.getDocumento(), docente.getCorreo());
    }
}
