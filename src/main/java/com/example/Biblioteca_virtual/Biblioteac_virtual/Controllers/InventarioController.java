package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.InventarioDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class InventarioController {
    @Autowired
    private IInventario services;

    @GetMapping("/inventarios")
    public List<InventarioDTO> getAll(){
        return services.getAllInventarios();
    }

    @GetMapping("/inventarios/{id}")
    public InventarioDTO getInventario(@PathVariable int id){
        return services.getInventario(id);
    }

    @PostMapping("/inventarios")
    public void agregar(@RequestBody InventarioDTO inventarioDTO){
        services.agregarInventario(inventarioDTO);
    }

    @PutMapping("/inventarios/{id}")
    public void modificar(@RequestBody InventarioDTO inventarioDTO, @PathVariable int id){
        services.modificarInventario(inventarioDTO, id);
    }

    @DeleteMapping("/inventarios/{id}")
    public void eliminar(@PathVariable int id){
        services.eliminarInventario(id);
    }
}
