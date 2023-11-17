package edu.unach.cinepolidts.domain.horario;

import java.sql.Time;
import java.util.List;

public record DatosRegistroHorario(
        Integer idPelicula,
        List<Time> horarios
) {
        // Constructor y métodos adicionales si es necesario
}

