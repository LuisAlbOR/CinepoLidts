package edu.unach.cinepolidts.controller;

import edu.unach.cinepolidts.domain.actores.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actores")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class ActoresController {

    @Autowired
    private ActoresRepository actoresRepository;

    @PostMapping
    public void registrarActor(@RequestBody @Valid DatosRegistroActor datosRegistroActor){
        actoresRepository.save(new Actor(datosRegistroActor));
    }

    @GetMapping                                  //Tamaño por defecto cambiado a 5, se muestra por nombre y pagina 0
    public Page<DatosListadoActor> listarActores(@PageableDefault(page = 0,size = 5,  sort={"nombre"}) Pageable paginacion){
        return actoresRepository.findAll(paginacion).map(DatosListadoActor::new);
    }

    @PutMapping
    @Transactional
    public void actualizarActor(@RequestBody @Valid DatosActualizarActor datosActualizarActor){
        Actor actor = actoresRepository.getReferenceById(datosActualizarActor.id());

        actor.actualiarDatos(datosActualizarActor);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarActor(@PathVariable int id){
        Actor actor = actoresRepository.getReferenceById(id);

        actoresRepository.delete(actor);
    }
}
