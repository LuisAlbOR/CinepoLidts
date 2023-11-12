package edu.unach.cinepolidts.controller;

import edu.unach.cinepolidts.domain.horario.DatosRegistroHorario;
import edu.unach.cinepolidts.domain.horario.Horario;
import edu.unach.cinepolidts.domain.horario.HorarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horario")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class HorarioController {

    @Autowired
    private HorarioRepository horarioRepository;

    @PostMapping
    public void regitrarHorario(@RequestBody @Valid DatosRegistroHorario datosRegistroHorario){
        horarioRepository.save(new Horario(datosRegistroHorario));
    }
}
