package edu.unach.cinepolidts.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    public String getSubject(String token) {
        // Verifica si el token es nulo y lanza una excepción si es así
        if (token == null) {
            throw new RuntimeException("Token nulo");
        }

        // Objeto para almacenar la información del token después de la verificación
        DecodedJWT verifier = null;

        try {
            // Configura el algoritmo de firma con la clave secreta 'apiSecret'
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // Construye y verifica el token JWT
            verifier = JWT.require(algorithm)
                    .withIssuer("cinepolidts") // Configura el emisor esperado del token
                    .build()
                    .verify(token);

            // Intenta obtener el sujeto del token
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            // Captura y maneja excepciones relacionadas con la verificación del token
            System.out.println("Error en la verificación del token: " + exception.toString());
        }

        // Verifica si el sujeto obtenido es nulo y lanza una excepción si es así
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier inválido: Sujeto nulo");
        }

        // Retorna el sujeto del token después de la verificación exitosa
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
