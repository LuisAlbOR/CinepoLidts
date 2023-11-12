package edu.unach.cinepolidts.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRespository extends JpaRepository<Usuario, Integer> {

    UserDetails findByUser(String username);
}
