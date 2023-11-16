package edu.unach.cinepolidts.domain.pelicula;

import edu.unach.cinepolidts.controller.ActoresController;
import edu.unach.cinepolidts.domain.actor.Actor;
import edu.unach.cinepolidts.domain.actor.ActoresRepository;
import edu.unach.cinepolidts.domain.director.DirectorRepository;
import edu.unach.cinepolidts.domain.participacion.Participacion;
import edu.unach.cinepolidts.domain.participacion.ParticipacionRepository;
import edu.unach.cinepolidts.infra.errors.ValidacionDeIntegridad;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrarPeliculaService {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ActoresRepository actoresRepository;

    @Autowired
    private PeliculasRepository peliculasRepository;

    @Autowired
    private ParticipacionRepository participacionRepository;



    public DatosDetallePelicula registrarPelicula(DatosRegistroPelicula datosRegistroPelicula) {
        // Verificar si todos los actores existen en el repositorio
        for (Integer actorId : datosRegistroPelicula.actoresIds()) {
            if (!actoresRepository.existsById(actorId)) {
                throw new ValidacionDeIntegridad("Uno o más IDs de actores no fueron encontrados");
            }
        }

        // Verificar si el director existe en el repositorio
        if (datosRegistroPelicula.directorId() != null && !directorRepository.existsById(datosRegistroPelicula.directorId()))
            throw new ValidacionDeIntegridad("El director con el ID especificado no fue encontrado");


        List<Actor> actores = actoresRepository.findAllById(datosRegistroPelicula.actoresIds());

        var director = directorRepository.findById(datosRegistroPelicula.directorId()).get();

        var pelicula = peliculasRepository.save(new Pelicula(datosRegistroPelicula, director));

        List<Participacion> participaciones = actores.stream()
                .map(actor -> new Participacion(actor, pelicula))
                .collect(Collectors.toList());

        participacionRepository.saveAll(participaciones);

        return new DatosDetallePelicula(pelicula,director,actores);
    }
}
