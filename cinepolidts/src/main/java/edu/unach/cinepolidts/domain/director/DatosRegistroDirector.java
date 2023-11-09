package edu.unach.cinepolidts.domain.director;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroDirector(
        int id,
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z]{4,100}$", message = "Solo se acepta de 4 a 100 caracteres y no se aceptan caracteres especiales")
        String nombre
) {
}
