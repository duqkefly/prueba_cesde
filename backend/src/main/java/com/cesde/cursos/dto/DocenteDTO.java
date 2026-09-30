package com.cesde.cursos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DocenteDTO(
        Long id,
        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "[A-Za-zÁÉÍÓÚáéíóúÑñ ]+", message = "El nombre solo debe tener letras") String nombre,
        @NotBlank(message = "El documento es obligatorio")
        @Pattern(regexp = "\\d{5,15}", message = "El documento solo debe tener numeros (entre 5 y 15)") String documento,
        @NotBlank(message = "El correo es obligatorio") @Email(message = "El correo no es valido") String correo) {
}
