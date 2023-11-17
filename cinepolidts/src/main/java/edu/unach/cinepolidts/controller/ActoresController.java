package edu.unach.cinepolidts.controller;

import edu.unach.cinepolidts.domain.actor.*;
import edu.unach.cinepolidts.domain.director.DatosRespuestaDirector;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/actor")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@SecurityRequirement(name = "bearer-key")
public class ActoresController {

    @Autowired
    private ActoresRepository actoresRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaActor> registrarActor(@RequestBody @Valid DatosRegistroActor datosRegistroActor, UriComponentsBuilder uriComponentsBuilder){
        Actor actor = actoresRepository.save(new Actor(datosRegistroActor));

        DatosRespuestaActor datosRespuestaActor = new DatosRespuestaActor(actor.getId(), actor.getNombre());

        URI url = uriComponentsBuilder.path("/director/{id}").buildAndExpand(actor.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaActor);
    }

    @GetMapping                                  //Tamaño por defecto cambiado a 5, se muestra por nombre y pagina 0
    public ResponseEntity<Page<DatosListadoActor>> listarActores(@PageableDefault(page = 0,size = 5,  sort={"nombre"}) Pageable paginacion){
        return ResponseEntity.ok(actoresRepository.findAll(paginacion).map(DatosListadoActor::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaActor> actualizarActor(@RequestBody @Valid DatosActualizarActor datosActualizarActor){
        Actor actor = actoresRepository.getReferenceById(datosActualizarActor.id());

        actor.actualiarDatos(datosActualizarActor);

        return ResponseEntity.ok(new DatosRespuestaActor(actor.getId(), actor.getNombre()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarActor(@PathVariable int id){
        Actor actor = actoresRepository.getReferenceById(id);

        actoresRepository.delete(actor);

        return ResponseEntity.noContent().build();
    }

}
