package com.cesde.cursos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CursoDTO(
        Long id,
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "La descripcion es obligatoria") String descripcion,
        @NotNull(message = "La duracion es obligatoria") @Positive(message = "La duracion debe ser mayor a 0") Integer duracionSemanas,
        @NotNull(message = "El precio es obligatorio") @Positive(message = "El precio debe ser mayor a 0") BigDecimal precio,
        @NotNull(message = "La fecha de inicio es obligatoria") LocalDateTime fechaInicio,
        @NotNull(message = "El docente es obligatorio") Long docenteId,
        String docenteNombre) {
}
