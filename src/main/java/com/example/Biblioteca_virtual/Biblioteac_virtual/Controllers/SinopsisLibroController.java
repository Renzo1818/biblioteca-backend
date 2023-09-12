package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.SinopsisLibroDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.ISinopsisLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SinopsisLibroController {
    @Autowired
    private ISinopsisLibro services;

    @GetMapping("/sinopsis")
    public ResponseEntity<List<SinopsisLibroDTO>> getAll(){
        return ResponseEntity.ok(services.getAllSinopsis());
    }

    @GetMapping("/sinopsis/{id}")
    public ResponseEntity<SinopsisLibroDTO> getSinopsis(@PathVariable int id){
        SinopsisLibroDTO sinopsisLibroDTO = services.getSinopsis(id);
        if(sinopsisLibroDTO != null){
            return ResponseEntity.ok(sinopsisLibroDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sinopsis")
    public ResponseEntity<?> agregar(@RequestBody SinopsisLibroDTO sinopsisLibroDTO){
        services.agregarSinopsis(sinopsisLibroDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/sinopsis/{id}")
    public ResponseEntity<Void> modificar(@RequestBody SinopsisLibroDTO sinopsisLibroDTO, @PathVariable int id){
        services.modificarSinopsis(sinopsisLibroDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/sinopsis/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        services.eliminarSinopsis(id);
        return ResponseEntity.ok().build();
    }
}
