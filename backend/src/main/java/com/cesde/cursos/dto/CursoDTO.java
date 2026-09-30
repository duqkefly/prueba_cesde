package com.cesde.cursos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CursoDTO(
        Long id,
        String nombre,
        String descripcion,
        Integer duracionSemanas,
        BigDecimal precio,
        LocalDateTime fechaInicio,
        Long docenteId,
        String docenteNombre) {
}
