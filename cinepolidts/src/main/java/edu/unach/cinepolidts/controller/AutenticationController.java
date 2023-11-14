package edu.unach.cinepolidts.controller;

import edu.unach.cinepolidts.domain.usuario.DatosAutenticacionUsuario;
import edu.unach.cinepolidts.domain.usuario.Usuario;
import edu.unach.cinepolidts.infra.security.DatosJwtToken;
import edu.unach.cinepolidts.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        // 1. Crear un token de autenticación con el nombre de usuario y la contraseña proporcionados en la solicitud.
        Authentication token = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.user(),datosAutenticacionUsuario.password());

        // 2. Autenticar el token utilizando el AuthenticationManager.
        var authenticate = authenticationManager.authenticate(token);

        var JwtToken = tokenService.generarToken((Usuario) authenticate.getPrincipal());
        // 3. Si la autenticación es exitosa, devolver una respuesta HTTP 200 OK.
        return ResponseEntity.ok(new DatosJwtToken(JwtToken));
    }
}
