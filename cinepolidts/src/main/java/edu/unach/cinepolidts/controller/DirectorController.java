package edu.unach.cinepolidts.controller;

import edu.unach.cinepolidts.domain.director.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/director")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class DirectorController {

    @Autowired
    private DirectorRepository directorRepository;

    @PostMapping
    public void registrarDirector(@RequestBody @Valid DatosRegistroDirector datosRegistroDirector){
        directorRepository.save(new Director(datosRegistroDirector));
    }

    @GetMapping
    public Page<DatosListarDirector> listarDirector(@PageableDefault(page = 0,size = 5,  sort={"nombre"}) Pageable paginacion){
        return directorRepository.findAll(paginacion).map(DatosListarDirector::new);
    }

    @PutMapping
    @Transactional
    public void actualizarDirector(@RequestBody @Valid DatosActualizarDirector datosActualizarDirector){
        Director director = directorRepository.getReferenceById(datosActualizarDirector.id());

        director.actualzarDirector(datosActualizarDirector);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarDirector(@PathVariable int id){
        Director director = directorRepository.getReferenceById(id);

        directorRepository.delete(director);
    }
}
