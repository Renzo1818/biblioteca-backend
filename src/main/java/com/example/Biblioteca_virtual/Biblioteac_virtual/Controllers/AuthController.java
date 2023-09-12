package com.example.Biblioteca_virtual.Biblioteac_virtual.Controllers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.AuthResponse;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.CredentialsDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IAuth services;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody CredentialsDTO credentialsDTO){
        return ResponseEntity.ok(services.login(credentialsDTO));
    }
}
