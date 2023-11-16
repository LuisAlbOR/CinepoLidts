package edu.unach.cinepolidts.domain.participacion;

import edu.unach.cinepolidts.domain.actor.Actor;
import edu.unach.cinepolidts.domain.pelicula.Pelicula;
import jakarta.persistence.*;
import lombok.*;

@Table(name ="participacion")
@Entity(name = "Participacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Participacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    public Participacion(Actor actor, Pelicula pelicula) {
        this.actor = actor;
        this.pelicula = pelicula;
    }
}
