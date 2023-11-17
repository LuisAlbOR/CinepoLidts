package edu.unach.cinepolidts.domain.pelicula;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {

}
