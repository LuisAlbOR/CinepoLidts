package edu.unach.cinepolidts.domain.director;

public record DatosListarDirector(int id,String nombre
) {
    public DatosListarDirector(Director director){
        this(director.getId(), director.getNombre());
    }
}
