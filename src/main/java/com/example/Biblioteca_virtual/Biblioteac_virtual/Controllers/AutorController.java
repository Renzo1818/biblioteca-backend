package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Autor;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AutorController {
    @Autowired
    private IAutor services;

    @GetMapping("/autores")
    public List<Autor> getAll(){
        return services.getAllAutores();
    }

    @GetMapping("/autores/{id}")
    public Autor getAutor(@PathVariable int id){
        return services.getAutor(id);
    }

    @PostMapping("/autores")
    public void guardar(@RequestBody Autor autor){
        services.guardarAutor(autor);
    }

    @PutMapping("/autores/{id}")
    public void modificar(@RequestBody Autor autor, @PathVariable int id){
        services.modifcarAutor(autor, id);
    }

    @DeleteMapping("/autores/{id}")
    public void eliminar(@PathVariable int id){
        services.eliminar(id);
    }
}
