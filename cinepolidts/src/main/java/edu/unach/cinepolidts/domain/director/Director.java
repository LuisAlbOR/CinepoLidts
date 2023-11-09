package edu.unach.cinepolidts.domain.director;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Director(DatosRegistroDirector datosRegistroDirector) {
        this.id = datosRegistroDirector.id();
        this.nombre = datosRegistroDirector.nombre();
    }

    public void actualzarDirector(DatosActualizarDirector datosActualizarDirector) {
        this.nombre = datosActualizarDirector.nombre();
    }
}
