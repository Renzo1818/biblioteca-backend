package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.SinopsisLibroDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.ISinopsisLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SinopsisLibroController {
    @Autowired
    private ISinopsisLibro services;

    @GetMapping("/sinopsis")
    public List<SinopsisLibroDTO> getAll(){
        return services.getAllSinopsis();
    }

    @GetMapping("/sinopsis/{id}")
    public SinopsisLibroDTO getSinopsis(@PathVariable int id){
        return services.getSinopsis(id);
    }

    @PostMapping("/sinopsis")
    public void agregar(@RequestBody SinopsisLibroDTO sinopsisLibroDTO){
        services.agregarSinopsis(sinopsisLibroDTO);
    }

    @PutMapping("/sinopsis/{id}")
    public void modificar(@RequestBody SinopsisLibroDTO sinopsisLibroDTO, @PathVariable int id){
        services.modificarSinopsis(sinopsisLibroDTO, id);
    }

    @DeleteMapping("/sinopsis/{id}")
    public void eliminar(@PathVariable int id){
        services.eliminarSinopsis(id);
    }
}
