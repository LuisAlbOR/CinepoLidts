package edu.unach.cinepolidts.domain.actores;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroActor(
        int id,
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z]{4,100}$", message = "No se aceptan caracteres especiales")
        //@Pattern(regexp = "\\d{4,100}")
        String nombre) {
}
