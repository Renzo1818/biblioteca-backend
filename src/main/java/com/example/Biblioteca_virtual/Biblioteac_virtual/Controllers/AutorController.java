package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Autor;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AutorController {
    @Autowired
    private IAutor services;

    @GetMapping("/autores")
    public ResponseEntity<List<Autor>> getAll(){
        return ResponseEntity.ok(services.getAllAutores());
    }

    @GetMapping("/autores/{id}")
    public ResponseEntity<Autor> getAutor(@PathVariable int id){
        Autor autor = services.getAutor(id);
        if(autor != null){
            return ResponseEntity.ok(autor);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/autores")
    public ResponseEntity<?> guardar(@RequestBody Autor autor){
        services.guardarAutor(autor);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/autores/{id}")
    public ResponseEntity<Void> modificar(@RequestBody Autor autor, @PathVariable int id){
        services.modifcarAutor(autor, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/autores/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        services.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
