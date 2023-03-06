package com.example.SpringHello.controllers;

import com.example.SpringHello.models.Alumno;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.SpringHello.repositories.AlumnoRepository;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 *
 * @author anton
 */
@RestController
@RequestMapping("/alumnado")
public class AlumnosController {

    @Autowired
    AlumnoRepository repo;

    @GetMapping()
    public List<Alumno> list(){
        return repo.findAll();
    }

    /*Listar el detalle de un libro concreto*/
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> get(@PathVariable Integer id) {
        if (repo.existsById(id)) {
            return new ResponseEntity<>(repo.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/suspensos/ad")
    public List<Alumno> getByModuloAd() {

        List<Alumno> alumnosSuspensos = new ArrayList();
        List<Alumno> alumnos = repo.findAll();
        for(Alumno alumno: alumnos){
            if(alumno.getAd()<5.0)
                alumnosSuspensos.add(alumno);
        }
        
        return alumnosSuspensos;
    }
    
    @GetMapping("/suspensos/di")
    public List<Alumno> getByModuloDi() {
        
        List<Alumno> alumnosSuspensos = new ArrayList();
        List<Alumno> alumnos = repo.findAll();
        for(Alumno alumno: alumnos){
            if(alumno.getDi()<5.0)
                alumnosSuspensos.add(alumno);
        }
        
        return alumnosSuspensos;
    }
    

    @PostMapping
    public ResponseEntity<Alumno> post(@RequestBody Alumno input) {
        repo.save(input);
        System.out.println(input.getNombre());
        return new ResponseEntity<>(input, HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Alumno input) {
        repo.delete(input);
        return new ResponseEntity<>(input, HttpStatus.OK);
    }
    
//    @DeleteMapping("/{isbn}")
//    public ResponseEntity<?> delete(@PathVariable String isbn) {
//        repo.deleteById(isbn);
//        return new ResponseEntity<>("Borrado con exito", HttpStatus.OK);
//    }
}
