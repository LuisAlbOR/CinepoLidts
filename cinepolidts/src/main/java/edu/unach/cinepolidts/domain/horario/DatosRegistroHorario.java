package edu.unach.cinepolidts.domain.horario;

import jakarta.validation.constraints.NotBlank;

import java.sql.Time;

public record DatosRegistroHorario(
        @NotBlank
        int idPelicula,
        @NotBlank
        Time horario
) {
}
