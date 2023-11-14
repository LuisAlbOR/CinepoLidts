package edu.unach.cinepolidts.infra.security;

import edu.unach.cinepolidts.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Obtener el token del encabezado de autorización
        var authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            // Extraer el token eliminando el prefijo "Bearer "
            var token = authHeader.replace("Bearer ", "");

            // Obtener el nombre de usuario (u otro identificador relevante) desde el token
            var nombreUsuario = tokenService.getSubject(token);

            if (nombreUsuario != null) {
                // El token es válido, buscar el usuario en la base de datos
                var usuario = usuarioRepository.findByUser(nombreUsuario);

                // Crear una instancia de UsernamePasswordAuthenticationToken
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                // Establecer la autenticación en el contexto de seguridad de Spring
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}

