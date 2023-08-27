package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.LibroDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.ILibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LibroController {
    @Autowired
    private ILibro services;

    @GetMapping("/libros")
    public List<LibroDTO> getAll(){
        return services.getAllLibros();
    }

    @GetMapping("/libros/{id}")
    public LibroDTO getLibro(@PathVariable int id){
        return services.getLibro(id);
    }

    @PostMapping("/libros")
    public void agregar(@RequestBody LibroDTO libroDTO){
        services.agregarLibro(libroDTO);
    }

    @PutMapping("libros/{id}")
    public void modificar(@RequestBody LibroDTO libroDTO, @PathVariable int id){
        services.modificarLibro(libroDTO, id);
    }

    @DeleteMapping("libros/{id}")
    public void eliminar(@PathVariable int id){
        services.eliminarLibro(id);
    }
}
