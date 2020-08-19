package pe.edu.colegiocima.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pe.edu.colegiocima.demo.models.entities.GradoSeccion;
import pe.edu.colegiocima.demo.services.GradoSeccionService;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gradoseccion")
public class GradoSeccionController {
    @Autowired
    private GradoSeccionService service;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody GradoSeccion gradoSeccion, BindingResult result){
        Map<String,Object> errores = new HashMap<>();
        if(result.hasErrors()){
            List<FieldError> lError = result.getFieldErrors();
            for (FieldError error:lError){
                errores.put(error.getField(),"El campo " + error.getField()+ " " + error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errores);
        }

        GradoSeccion gradoSeccionDB = service.save(gradoSeccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(gradoSeccionDB);
    }
}
