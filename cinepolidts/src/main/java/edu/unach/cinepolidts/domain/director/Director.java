package edu.unach.cinepolidts.domain.director;


import edu.unach.cinepolidts.domain.pelicula.Categoria;
import edu.unach.cinepolidts.domain.pelicula.Pelicula;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Director")
@Table(name = "director")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    private String nombre;

    @OneToMany(mappedBy = "director")
    private List<Pelicula> peliculas;


    public Director(DatosRegistroDirector datosRegistroDirector) {
        this.id = datosRegistroDirector.id();
        this.nombre = datosRegistroDirector.nombre();
    }

    public void actualzarDirector(DatosActualizarDirector datosActualizarDirector) {
        this.nombre = datosActualizarDirector.nombre();
    }
}
