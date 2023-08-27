package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.UsuarioDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class UsuarioController {
    @Autowired
    private IUsuario services;

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        return ResponseEntity.ok(services.getAllUsuarios());
    }
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable String id) {
        UsuarioDTO usuario = services.getUsuario(Integer.parseInt(id));
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Void> agregar(@RequestBody UsuarioDTO usuarioDTO){
        services.guardarUsuario(usuarioDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Void> modificar(@RequestBody UsuarioDTO usuarioDTO, @PathVariable String id){
        services.modificarUsuario(usuarioDTO, Integer.parseInt(id));
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id){
        services.eliminarUsuario(Integer.parseInt(id));
        return ResponseEntity.ok().build();
    }
}
