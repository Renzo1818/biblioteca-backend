package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Editorial;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IEditorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EditorialController {
    @Autowired
    private IEditorial services;

    @GetMapping("/editoriales")
    public List<Editorial> getAll(){
        return services.getAllEditoriales();
    }

    @GetMapping("/editoriales/{id}")
    public Editorial getEditorial(@PathVariable int id){
        return services.getEditorial(id);
    }

    @PostMapping("/editoriales")
    public void agregar(@RequestBody Editorial editorial){
        services.guardarEditorial(editorial);
    }

    @PutMapping("/editoriales/{id}")
    public void modificar(@RequestBody Editorial editorial, @PathVariable int id){
        services.modificarEditorial(editorial, id);
    }

    @DeleteMapping("/editoriales/{id}")
    public void eliminar(@PathVariable int id){
        services.eliminarEditorial(id);
    }
}
