package edu.unach.cinepolidts.controller;

import edu.unach.cinepolidts.domain.actor.Actor;
import edu.unach.cinepolidts.domain.actor.DatosRespuestaActor;
import edu.unach.cinepolidts.domain.horario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/horario")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@SecurityRequirement(name = "bearer-key")
public class HorarioController {

    @Autowired
    private RegistrarHorarioService registrarHorarioService;

    @Autowired
    private HorarioServicio horarioServicio;

    @PostMapping
    public ResponseEntity regitrarHorario(@RequestBody @Valid DatosRegistroHorario datosRegistroHorario, UriComponentsBuilder UriComponentsBuilder) {
        registrarHorarioService.registrarHorario(datosRegistroHorario);

        DatosRespuestaHorario datosRespuestaHorario = new DatosRespuestaHorario(datosRegistroHorario.idPelicula(), datosRegistroHorario.horarios());

        URI url = UriComponentsBuilder.path("/director/{id}").buildAndExpand(datosRespuestaHorario.idPelicula()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaHorario);
    }

    @GetMapping("/pelicula/{idPelicula}")
    public ResponseEntity<List<DatosRespuestaHorario>> obtenerHorariosDePelicula(@PathVariable int idPelicula) {
        List<Horario> horarios = horarioServicio.obtenerHorariosPorPelicula(idPelicula);

        // Mapea los horarios a DatosRespuestaHorario si es necesario
        List<DatosRespuestaHorario> datosRespuestaHorarios = horarios.stream()
                .map(horario -> new DatosRespuestaHorario(idPelicula, Collections.singletonList(horario.getHorario())))
                .collect(Collectors.toList());

        return ResponseEntity.ok(datosRespuestaHorarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHorario(@PathVariable int id) {
        horarioServicio.eliminarHorario(id);
        return ResponseEntity.noContent().build();
    }
}


