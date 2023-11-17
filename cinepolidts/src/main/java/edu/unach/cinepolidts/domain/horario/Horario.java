package edu.unach.cinepolidts.domain.horario;

import edu.unach.cinepolidts.domain.pelicula.Pelicula;
import edu.unach.cinepolidts.domain.pelicula.PeliculasRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Entity(name = "Horario")
@Table(name = "horario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "pelicula_id")  // Esto debe coincidir con el nombre de la columna en tu tabla de Horarios que referencia a la tabla de Peliculas
    private Pelicula pelicula;
    private Time horario;


    public Horario(Pelicula pelicula, Time horario) {
        this.pelicula = pelicula;
        this.horario = horario;
    }

    public void actualizarDatos(DatosActualizarHorario datosActualizarHorario) {
        this.horario = datosActualizarHorario.nuevoHorario();
    }
}
