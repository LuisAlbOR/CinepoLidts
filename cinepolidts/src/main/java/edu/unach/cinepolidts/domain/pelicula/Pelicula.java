package edu.unach.cinepolidts.domain.pelicula;

import edu.unach.cinepolidts.domain.director.Director;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.Year;


@Entity(name = "Pelicula")
@Table(name = "pelicula")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String image;

    private String urlTrailer;

    private String nombre;


    private Time minutos;

    @Enumerated(EnumType.STRING)
    private Clasificacion clasificacion;

    @Enumerated(EnumType.STRING)
    private Categoria tipo;

    private Year year;

    private String sinopsis;

    @ManyToOne
    @JoinColumn(name = "director_id")  // Esto debe coincidir con el nombre de la columna en tu tabla de Peliculas
    private Director director;

    public Pelicula(DatosRegistroPelicula datosRegistroPelicula, Director director) {
        this.image = datosRegistroPelicula.image();
        this.urlTrailer = datosRegistroPelicula.urlTrailer();
        this.nombre = datosRegistroPelicula.nombre();
        this.minutos = datosRegistroPelicula.minutos();
        this.clasificacion = datosRegistroPelicula.clasificacion();
        this.tipo = datosRegistroPelicula.tipo();
        this.year = datosRegistroPelicula.year();
        this.sinopsis = datosRegistroPelicula.sinopsis();
        this.director = director ;
    }
}
