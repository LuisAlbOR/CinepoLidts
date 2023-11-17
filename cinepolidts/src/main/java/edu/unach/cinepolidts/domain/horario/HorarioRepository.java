package edu.unach.cinepolidts.domain.horario;

import edu.unach.cinepolidts.domain.pelicula.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface HorarioRepository extends JpaRepository<Horario,Integer> {
    boolean existsByPeliculaAndHorario(Pelicula pelicula, Time horario);

    List<Horario> findByPeliculaId(Integer idPelicula);
}
