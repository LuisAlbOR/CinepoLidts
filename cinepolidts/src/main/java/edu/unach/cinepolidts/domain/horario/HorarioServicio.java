package edu.unach.cinepolidts.domain.horario;

import edu.unach.cinepolidts.domain.pelicula.Pelicula;
import edu.unach.cinepolidts.domain.pelicula.PeliculasRepository;
import edu.unach.cinepolidts.infra.errors.ValidacionDeIntegridad;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioServicio {

    @Autowired
    private PeliculasRepository peliculasRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> obtenerHorariosPorPelicula(Integer idPelicula) {
        // Verificar si la película existe
        if (!peliculasRepository.existsById(idPelicula)) {
            throw new ValidacionDeIntegridad("La película con ID " + idPelicula + " no existe");
        }

        // Obtener los horarios para la película dada
        return horarioRepository.findByPeliculaId(idPelicula);
    }

    @Transactional
    public void eliminarHorario(int id) {
        if (!horarioRepository.existsById(id)) {
            throw new ValidacionDeIntegridad("Horario no encontrado con ID: " + id);
        }
        horarioRepository.deleteById(id);
    }
}
