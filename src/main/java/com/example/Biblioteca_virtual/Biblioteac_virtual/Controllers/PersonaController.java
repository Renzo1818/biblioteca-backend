package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.PersonaDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class PersonaController {
    @Autowired
    private IPersona services;

    @GetMapping("/personas")
    public ResponseEntity<List<PersonaDTO>> getAll(){
        return ResponseEntity.ok(services.getAllPersonas());
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<PersonaDTO> getById(@PathVariable String id){
        PersonaDTO personaDTO = services.getPersona(Integer.parseInt(id));
        if (personaDTO != null) {
            return ResponseEntity.ok(personaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/personas")
    public ResponseEntity<?> agregar(@RequestBody PersonaDTO persona){
        services.guardarPersona(persona);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/personas/{id}")
    public ResponseEntity<Void> modificar(@RequestBody PersonaDTO persona, @PathVariable String id){
        services.modificarPersona(persona, Integer.parseInt(id));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/personas/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id){
        services.eliminarPersona(Integer.parseInt(id));
        return ResponseEntity.ok().build();
    }

}
