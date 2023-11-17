package edu.unach.cinepolidts.domain.horario;


import edu.unach.cinepolidts.domain.pelicula.PeliculasRepository;
import edu.unach.cinepolidts.infra.errors.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrarHorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private PeliculasRepository peliculasRepository;

    public void registrarHorario(DatosRegistroHorario datosRegistroHorario) {

    //Verificar si la pelicula esta registrada en el repo
        if(datosRegistroHorario.idPelicula() != null && !peliculasRepository.existsById(datosRegistroHorario.idPelicula()))
            throw new ValidacionDeIntegridad("La pelicula no se encuentra registrada");

        var pelicula = peliculasRepository.findById(datosRegistroHorario.idPelicula()).get();

        // Crear una lista para almacenar los horarios que se deben agregar
        List<Horario> horariosPorAgregar = new ArrayList<>();

        // Iterar sobre la lista de horarios
        for (Time horario : datosRegistroHorario.horarios()) {
            // Verificar si el horario ya está registrado para esta película
            if (!horarioRepository.existsByPeliculaAndHorario(pelicula, horario)) {
                // Crear un nuevo horario y agregarlo a la lista
                Horario nuevoHorario = new Horario(pelicula, horario);
                horariosPorAgregar.add(nuevoHorario);
            }
        }
        // Guardar todos los horarios que no estén registrados
        horarioRepository.saveAll(horariosPorAgregar);
    }
}
