package edu.unach.cinepolidts.controller;

import edu.unach.cinepolidts.domain.pelicula.*;
import edu.unach.cinepolidts.infra.errors.ValidacionDeIntegridad;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@ResponseBody
@RequestMapping("/pelicula")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class PeliculaController {
        @Autowired
        private RegistrarPeliculaService registrarPeliculaService;

        @PostMapping
        @Transactional
        public ResponseEntity registrarPelicula(@RequestBody @Valid DatosRegistroPelicula datosRegistroPelicula) throws ValidacionDeIntegridad {
                var response = registrarPeliculaService.registrarPelicula(datosRegistroPelicula);
                return ResponseEntity.ok(response);
        }
}
