package edu.unach.cinepolidts.domain.horario;

import java.sql.Time;
import java.util.List;

public record DatosRespuestaHorario(Integer idPelicula, List<Time> Horarios) {
}
