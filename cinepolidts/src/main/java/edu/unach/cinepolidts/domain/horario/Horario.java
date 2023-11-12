package edu.unach.cinepolidts.domain.horario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

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
    private int idPelicula;
    private Time horario;

    public Horario(DatosRegistroHorario datosRegistroHorario) {
        this.idPelicula = datosRegistroHorario.idPelicula();
        this.horario = datosRegistroHorario.horario();
    }
}
