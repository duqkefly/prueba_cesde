package com.cesde.cursos.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cesde.cursos.dto.DocenteDTO;
import com.cesde.cursos.model.Docente;
import com.cesde.cursos.repository.CursoRepository;
import com.cesde.cursos.repository.DocenteRepository;

@Service
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final CursoRepository cursoRepository;

    public DocenteService(DocenteRepository docenteRepository, CursoRepository cursoRepository) {
        this.docenteRepository = docenteRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<DocenteDTO> listar(String nombre) {
        List<Docente> docentes;

        if (nombre != null && !nombre.isBlank()) {
            docentes = docenteRepository.findByNombreContainingIgnoreCase(nombre);
        } else {
            docentes = docenteRepository.findAll();
        }

        return docentes.stream().map(this::toDTO).toList();
    }

    public DocenteDTO crear(DocenteDTO dto) {
        if (docenteRepository.existsByDocumento(dto.documento())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un docente con ese documento");
        }
        if (docenteRepository.existsByCorreo(dto.correo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un docente con ese correo");
        }

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

    public void eliminar(Long id) {
        if (!docenteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el docente con id " + id);
        }
        // No se borra si todavia dicta cursos
        if (cursoRepository.existsByDocenteId(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede eliminar, el docente tiene cursos asignados");
        }
        docenteRepository.deleteById(id);
    }

    private DocenteDTO toDTO(Docente docente) {
        return new DocenteDTO(docente.getId(), docente.getNombre(), docente.getDocumento(), docente.getCorreo());
    }
}
