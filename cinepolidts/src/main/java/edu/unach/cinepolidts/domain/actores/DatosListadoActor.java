package edu.unach.cinepolidts.domain.actores;

public record DatosListadoActor(int id, String nombre) {
    public DatosListadoActor(Actor actor){
        this(actor.getId(), actor.getNombre());
    }
}
