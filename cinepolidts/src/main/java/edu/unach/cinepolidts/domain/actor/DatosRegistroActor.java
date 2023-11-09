package edu.unach.cinepolidts.domain.actor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroActor(
        int id,
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z]{4,100}$", message = "Solo se acepta de 4 a 100 caracteres y no se aceptan caracteres especiales")
        //@Pattern(regexp = "\\d{4,100}")
        String nombre) {
}
