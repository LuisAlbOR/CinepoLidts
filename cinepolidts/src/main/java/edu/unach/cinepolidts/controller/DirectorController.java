package edu.unach.cinepolidts.controller;

import edu.unach.cinepolidts.domain.director.*;
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
@RequestMapping("/director")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@SecurityRequirement(name = "bearer-key")
public class DirectorController {

    @Autowired
    private DirectorRepository directorRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaDirector> registrarDirector(@RequestBody @Valid DatosRegistroDirector datosRegistroDirector, UriComponentsBuilder uriComponentsBuilder){
        Director director = directorRepository.save(new Director(datosRegistroDirector));

        DatosRespuestaDirector datosRespuestaDirector = new DatosRespuestaDirector(director.getId(), director.getNombre());

        URI url = uriComponentsBuilder.path("/director/{id}").buildAndExpand(director.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaDirector);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarDirector>> listarDirector(@PageableDefault(page = 0,size = 5,  sort={"nombre"}) Pageable paginacion){
        return ResponseEntity.ok(directorRepository.findAll(paginacion).map(DatosListarDirector::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarDirector(@RequestBody @Valid DatosActualizarDirector datosActualizarDirector){
        Director director = directorRepository.getReferenceById(datosActualizarDirector.id());

        director.actualzarDirector(datosActualizarDirector);

        return ResponseEntity.ok(new DatosRespuestaDirector(director.getId(), director.getNombre()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarDirector(@PathVariable int id){
        Director director = directorRepository.getReferenceById(id);

        directorRepository.delete(director);

        return ResponseEntity.noContent().build();
    }
}
