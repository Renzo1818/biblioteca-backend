package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.AuthResponse;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.CredentialsDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.UsuarioDTO;

public interface IAuth {
    AuthResponse login(CredentialsDTO credentialsDTO);
    //AuthResponse register(UsuarioDTO usuarioDTO);
}
