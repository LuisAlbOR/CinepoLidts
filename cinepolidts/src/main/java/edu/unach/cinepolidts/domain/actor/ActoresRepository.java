package edu.unach.cinepolidts.domain.actor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActoresRepository extends JpaRepository<Actor, Integer> {
    @Override
    Page<Actor> findAll(Pageable pageable);
}
