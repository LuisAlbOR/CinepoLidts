package edu.unach.cinepolidts.domain.actor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Actor")
@Table(name = "actor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    public Actor(DatosRegistroActor datosRegistroActor) {
        this.id = datosRegistroActor.id();
        this.nombre = datosRegistroActor.nombre();
    }

    public void actualiarDatos(DatosActualizarActor datosActualizarActor) {
        this.nombre = datosActualizarActor.nombre();
    }
}
