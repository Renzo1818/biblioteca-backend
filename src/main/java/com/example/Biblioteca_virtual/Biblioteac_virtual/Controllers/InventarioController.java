package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.InventarioDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class InventarioController {
    @Autowired
    private IInventario services;

    @GetMapping("/inventarios")
    public ResponseEntity<List<InventarioDTO>> getAll(){
        return ResponseEntity.ok(services.getAllInventarios());
    }

    @GetMapping("/inventarios/{id}")
    public ResponseEntity<InventarioDTO> getInventario(@PathVariable int id){
        InventarioDTO inventarioDTO = services.getInventario(id);
        if(inventarioDTO != null){
            return ResponseEntity.ok(inventarioDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/inventarios")
    public ResponseEntity<?> agregar(@RequestBody InventarioDTO inventarioDTO){
        services.agregarInventario(inventarioDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/inventarios/{id}")
    public ResponseEntity<Void> modificar(@RequestBody InventarioDTO inventarioDTO, @PathVariable int id){
        services.modificarInventario(inventarioDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/inventarios/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        services.eliminarInventario(id);
        return ResponseEntity.ok().build();
    }
}
