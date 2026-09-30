package com.cesde.cursos.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.cesde.cursos.dto.CursoDTO;
import com.cesde.cursos.model.Curso;
import com.cesde.cursos.model.Docente;
import com.cesde.cursos.repository.CursoRepository;
import com.cesde.cursos.repository.DocenteRepository;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final DocenteRepository docenteRepository;

    public CursoService(CursoRepository cursoRepository, DocenteRepository docenteRepository) {
        this.cursoRepository = cursoRepository;
        this.docenteRepository = docenteRepository;
    }

    @Transactional(readOnly = true)
    public List<CursoDTO> listar(String nombre, LocalDate desde, LocalDate hasta) {
        List<Curso> cursos;

        if (nombre != null && !nombre.isBlank()) {
            cursos = cursoRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (desde != null && hasta != null) {
            cursos = cursoRepository.findByFechaInicioBetween(desde.atStartOfDay(), hasta.atTime(23, 59, 59));
        } else {
            cursos = cursoRepository.findAll();
        }

        return cursos.stream().map(this::toDTO).toList();
    }

    @Transactional
    public CursoDTO crear(CursoDTO dto) {
        Docente docente = docenteRepository.findById(dto.docenteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el docente con id " + dto.docenteId()));

        Curso curso = new Curso();
        curso.setNombre(dto.nombre());
        curso.setDescripcion(dto.descripcion());
        curso.setDuracionSemanas(dto.duracionSemanas());
        curso.setPrecio(dto.precio());
        curso.setFechaInicio(dto.fechaInicio());
        curso.setDocente(docente);
        return toDTO(cursoRepository.save(curso));
    }

    @Transactional
    public CursoDTO actualizar(Long id, CursoDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el curso con id " + id));
        Docente docente = docenteRepository.findById(dto.docenteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el docente con id " + dto.docenteId()));

        curso.setNombre(dto.nombre());
        curso.setDescripcion(dto.descripcion());
        curso.setDuracionSemanas(dto.duracionSemanas());
        curso.setPrecio(dto.precio());
        curso.setFechaInicio(dto.fechaInicio());
        curso.setDocente(docente);
        return toDTO(cursoRepository.save(curso));
    }

    public void eliminar(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el curso con id " + id);
        }
        cursoRepository.deleteById(id);
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
