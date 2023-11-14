package edu.unach.cinepolidts.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import edu.unach.cinepolidts.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            // 1. Define el algoritmo de firma (en este caso, HMAC con clave secreta)
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // 2. Crea el token JWT usando la librería jjwt
            return JWT.create()
                    .withIssuer("cinepolidts")             // Emisor del token
                    .withSubject(usuario.getUser())    // Sujeto del token (generalmente el nombre de usuario)
                    .withClaim("id", usuario.getId())   // Agrega una claim personalizada (en este caso, el ID del usuario)
                    .withExpiresAt(generarFechaExpiracion())  // Configura la fecha de expiración del token
                    .sign(algorithm);                   // Firma el token con el algoritmo y la clave secreta
        } catch (JWTCreationException exception) {
            // 3. Captura cualquier excepción que pueda ocurrir durante la creación del token
            throw new RuntimeException();
        }
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
