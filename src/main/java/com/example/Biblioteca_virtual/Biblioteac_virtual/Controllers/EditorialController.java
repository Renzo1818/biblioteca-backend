package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Editorial;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IEditorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EditorialController {
    @Autowired
    private IEditorial services;

    @GetMapping("/editoriales")
    public ResponseEntity<List<Editorial>> getAll(){
        return ResponseEntity.ok(services.getAllEditoriales());
    }

    @GetMapping("/editoriales/{id}")
    public ResponseEntity<Editorial> getEditorial(@PathVariable int id){
        Editorial editorial = services.getEditorial(id);
        if(editorial != null){
            return ResponseEntity.ok(editorial);
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("/editoriales")
    public ResponseEntity<?> agregar(@RequestBody Editorial editorial){
        services.guardarEditorial(editorial);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editoriales/{id}")
    public ResponseEntity<Void> modificar(@RequestBody Editorial editorial, @PathVariable int id){
        services.modificarEditorial(editorial, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/editoriales/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        services.eliminarEditorial(id);
        return ResponseEntity.ok().build();
    }
}
