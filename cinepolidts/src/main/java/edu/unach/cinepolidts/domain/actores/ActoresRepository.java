package edu.unach.cinepolidts.domain.actores;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActoresRepository extends JpaRepository<Actor, Integer> {

    Page<Actor> findAll(Pageable paginacion);
}
