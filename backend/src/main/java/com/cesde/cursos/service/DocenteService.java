package com.cesde.cursos.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public DocenteDTO crear(DocenteDTO dto) {
        Docente docente = new Docente();
        docente.setNombre(dto.nombre());
        docente.setDocumento(dto.documento());
        docente.setCorreo(dto.correo());
        return toDTO(docenteRepository.save(docente));
    }

    public DocenteDTO actualizar(Long id, DocenteDTO dto) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el docente con id " + id));

        docente.setNombre(dto.nombre());
        docente.setDocumento(dto.documento());
        docente.setCorreo(dto.correo());
        return toDTO(docenteRepository.save(docente));
    }

    private DocenteDTO toDTO(Docente docente) {
        return new DocenteDTO(docente.getId(), docente.getNombre(), docente.getDocumento(), docente.getCorreo());
    }
}
