package pe.edu.colegiocima.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.colegiocima.demo.models.entities.AlumnoColegio;
import pe.edu.colegiocima.demo.services.AlumnoColegioService;
import pe.edu.colegiocima.demo.services.AlumnoColegioServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/alumnocolegio")
public class AlumnoColegioController {

    @Autowired
    private AlumnoColegioService service;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        return  ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{idAlumnoColegio}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idAlumnoColegio){
        return ResponseEntity.ok(service.buscarPorId(idAlumnoColegio));
    }

    @GetMapping("activos/aniolectivo/{idAnioLectivo}")
    public ResponseEntity<?> listarActivos(@PathVariable Short idAnioLectivo){
        return  ResponseEntity.ok(service.listarActivoPorAnioLectivo(idAnioLectivo));
    }

    @GetMapping("/aniolectivo/{idAnioLectivo}/gradoseccion/{idGradoSeccion}")
    public ResponseEntity<?> listarAlumnos(@PathVariable Short idAnioLectivo, @PathVariable Short idGradoSeccion){
        return  ResponseEntity.ok(service.listarAlumnos(idAnioLectivo,idGradoSeccion));
    }

    @GetMapping("/listar/pagina")
    public ResponseEntity<?> paginar(Pageable page){
        return ResponseEntity.ok(service.paginar(page));
    }

    @DeleteMapping("/{idAlumnoColegio}")
    public ResponseEntity<?> deshabilitar(@PathVariable Long idAlumnoColegio){
        AlumnoColegio ac = service.buscarPorId(idAlumnoColegio);
        if(Objects.isNull(ac)){
            return ResponseEntity.notFound().build();
        }
        ac.setEstado(false);
        service.guardar(ac);  // repository.save
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody AlumnoColegio alumnoColegio){
        return ResponseEntity.ok(service.guardar(alumnoColegio));
    }

    @PutMapping("/actualizar/{idAlumnoColegio}")
    public ResponseEntity<?> actualizar(@RequestBody AlumnoColegio alumnoColegio, @PathVariable Long idAlumnoColegio){


        AlumnoColegio ac = service.buscarPorId(idAlumnoColegio);
        Map<String,String> respuesta = new HashMap<>();

        if(Objects.isNull(ac)){
            respuesta.put("message","El alumno no se encontró");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        ac.setObservacion(alumnoColegio.getObservacion());
        ac.setObservacionGerencia(alumnoColegio.getObservacionGerencia());
        ac.setIdTurno(alumnoColegio.getIdTurno());
        // colocar todo lo que se actualiza.

        return ResponseEntity.ok(service.guardar(ac));

    }

    //@PutMapping("")

    // GET   POST  PUT DELETE



}
