package com.cesde.cursos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesde.cursos.dto.CursoDTO;
import com.cesde.cursos.model.Curso;
import com.cesde.cursos.repository.CursoRepository;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Transactional(readOnly = true)
    public List<CursoDTO> listar() {
        return cursoRepository.findAll().stream().map(this::toDTO).toList();
    }

    private CursoDTO toDTO(Curso curso) {
        return new CursoDTO(
                curso.getId(),
                curso.getNombre(),
                curso.getDescripcion(),
                curso.getDuracionSemanas(),
                curso.getPrecio(),
                curso.getFechaInicio(),
                curso.getDocente().getId(),
                curso.getDocente().getNombre());
    }
}
