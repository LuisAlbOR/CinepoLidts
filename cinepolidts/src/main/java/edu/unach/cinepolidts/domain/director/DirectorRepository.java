package edu.unach.cinepolidts.domain.director;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director,Integer> {
    @Override
    Page<Director> findAll(Pageable pageable);
}
