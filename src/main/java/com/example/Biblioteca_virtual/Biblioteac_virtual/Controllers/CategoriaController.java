package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Categoria;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.ICategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CategoriaController {
    @Autowired
    private ICategoria services;

    @GetMapping("/categorias")
    public List<Categoria> getAll(){
        return services.getAllCategorias();
    }
}
