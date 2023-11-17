package edu.unach.cinepolidts.controller;

import edu.unach.cinepolidts.domain.pelicula.*;
import edu.unach.cinepolidts.infra.errors.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Year;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/pelicula")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@SecurityRequirement(name = "bearer-key")
public class PeliculaController {
        @Autowired
        private RegistrarPeliculaService registrarPeliculaService;

        @Autowired
        private PeliculasRepository peliculasRepository;

        @PostMapping
        @Transactional
        public ResponseEntity registrarPelicula(@RequestBody @Valid DatosRegistroPelicula datosRegistroPelicula) throws ValidacionDeIntegridad {
                 registrarPeliculaService.registrarPelicula(datosRegistroPelicula);
                return ResponseEntity.ok(datosRegistroPelicula);
        }


}
