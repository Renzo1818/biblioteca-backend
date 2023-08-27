package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.ReservaVirtualDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IReservaVirtual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ReservaVitualController {
    @Autowired
    private IReservaVirtual services;

    @GetMapping("/reservas")
    public List<ReservaVirtualDTO> getAll(){
        return services.getAllReservas();
    }
    @GetMapping("/reservas/{id}")
    public ReservaVirtualDTO getReserva(@PathVariable int id){
        return services.getReserva(id);
    }
    @PostMapping("/reservas")
    public void agregar(@RequestBody ReservaVirtualDTO reservaVirtualDTO){
        services.agregarReserva(reservaVirtualDTO);
    }
    @PutMapping("/reservas/{id}")
    public void modificar(@PathVariable int id){
        services.devolverReserva(id);
    }
}
