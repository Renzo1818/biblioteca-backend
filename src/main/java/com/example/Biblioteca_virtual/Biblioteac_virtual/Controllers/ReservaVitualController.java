package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.ReservaVirtualDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IReservaVirtual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ReservaVitualController {
    @Autowired
    private IReservaVirtual services;

    @GetMapping("/reservas")
    public ResponseEntity<List<ReservaVirtualDTO>> getAll(){
        return ResponseEntity.ok(services.getAllReservas());
    }
    @GetMapping("/reservas/{id}")
    public ResponseEntity<ReservaVirtualDTO> getReserva(@PathVariable int id){
        ReservaVirtualDTO reservaVirtualDTO = services.getReserva(id);
        if(reservaVirtualDTO != null){
            return ResponseEntity.ok(reservaVirtualDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/reservas")
    public ResponseEntity<?> agregar(@RequestBody ReservaVirtualDTO reservaVirtualDTO){
        services.agregarReserva(reservaVirtualDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/reservas/{id}")
    public ResponseEntity<Void> modificar(@PathVariable int id){
        services.devolverReserva(id);
        return ResponseEntity.ok().build();
    }
}
