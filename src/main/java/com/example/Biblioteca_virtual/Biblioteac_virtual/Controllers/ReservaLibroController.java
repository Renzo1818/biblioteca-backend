package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.ReservaLibroDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements.ReservaLibroImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservaLibroController {
    private final ReservaLibroImp services;

    @GetMapping("/reservas-libro")
    public ResponseEntity<List<ReservaLibroDTO>> getAll(){
        return ResponseEntity.ok(services.getAllReservaLibros());
    }
    @GetMapping("/reservas-libro/{idReserva}/{idLibro}")
    public ResponseEntity<ReservaLibroDTO> getById(@PathVariable int idReserva, @PathVariable int idLibro){
        ReservaLibroDTO reservaLibroDTO = services.getReservaLibro(idReserva, idLibro);
        if(reservaLibroDTO != null){
            return ResponseEntity.ok(reservaLibroDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("/reservas-libro")
    public ResponseEntity<?> agregar(@RequestBody ReservaLibroDTO reservaLibroDTO){
        services.agregarReservaLibro(reservaLibroDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/reservas-libro/{idReserva}/{idLibro}")
    public ResponseEntity<Void> modificar(@PathVariable int idReserva, @PathVariable int idLibro){
        services.devolverReservaLibro(idReserva, idLibro);
        return ResponseEntity.ok().build();
    }

}
