package edu.unach.cinepolidts.domain.pelicula;

import edu.unach.cinepolidts.domain.director.Director;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "Pelicula")
@Table(name = "pelicula")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String urlTrailer;

    private String nombre;

    private int minutos;

    @Enumerated(EnumType.STRING)
    private Clasificacion clasificacion;

    @Enumerated(EnumType.STRING)
    private Categoria tipo;

    private int year;

    private String sinopsis;

    @ManyToOne
    @JoinColumn(name = "director_id")  // Esto debe coincidir con el nombre de la columna en tu tabla de Peliculas
    private Director director;

}
