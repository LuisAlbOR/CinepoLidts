package edu.unach.cinepolidts.domain.pelicula;

import edu.unach.cinepolidts.domain.actor.Actor;
import edu.unach.cinepolidts.domain.director.Director;
import edu.unach.cinepolidts.domain.participacion.Participacion;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record DatosDetallePelicula(
        int id,
        String nombre,
        String urlTrailer,
        Clasificacion clasificacion,
        Categoria tipo,
        String sinopsis,
        String director,
        List<String> actores
) {
    public DatosDetallePelicula(Pelicula pelicula, Director director, List<Actor> actores) {
        this(
                pelicula.getId(),
                pelicula.getNombre(),
                pelicula.getUrlTrailer(),
                pelicula.getClasificacion(),
                pelicula.getTipo(),
                pelicula.getSinopsis(),
                director.getNombre(),
                actores.stream().map(Actor::getNombre).collect(Collectors.toList())
        );
    }

}
