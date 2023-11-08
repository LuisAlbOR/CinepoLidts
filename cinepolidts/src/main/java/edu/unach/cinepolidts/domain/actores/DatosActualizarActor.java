package edu.unach.cinepolidts.domain.actores;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosActualizarActor(
        @NotNull
        int id,
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z]{4,100}$", message = "No se aceptan caracteres especiales")
        String nombre) {
}
