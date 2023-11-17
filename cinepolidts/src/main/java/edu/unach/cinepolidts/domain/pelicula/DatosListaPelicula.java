package edu.unach.cinepolidts.domain.pelicula;

import edu.unach.cinepolidts.domain.director.Director;

import java.awt.image.DirectColorModel;
import java.sql.Time;
import java.time.Year;

public record DatosListaPelicula(
        int id,
        String image,
        String urlTrailer,
        String nombre,
        Time minutos,
        Clasificacion clasificacion,
        Categoria tipo,
        Year year,
        String sinopsis,
        Director director
        ) {
        public DatosListaPelicula(Pelicula pelicula){
            this(pelicula.getId(), pelicula.getImage(), pelicula.getUrlTrailer(), pelicula.getNombre(), pelicula.getMinutos(), pelicula.getClasificacion(), pelicula.getTipo(), pelicula.getYear(), pelicula.getSinopsis(), pelicula.getDirector());
        }
}
