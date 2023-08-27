package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Distrito;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IDistrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class DistritoController {
    @Autowired
    private IDistrito services;

    @GetMapping("/distritos")
    public List<Distrito> getAll(){
        return services.getAllDistritos();
    }
}
