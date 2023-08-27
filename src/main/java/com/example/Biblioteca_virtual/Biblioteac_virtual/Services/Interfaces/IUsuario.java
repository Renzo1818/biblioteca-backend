package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces;


import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.UsuarioDTO;

import java.util.List;
public interface IUsuario {
    List<UsuarioDTO> getAllUsuarios();
    UsuarioDTO getUsuario(int id);
    void guardarUsuario(UsuarioDTO usuarioDTO);
    void modificarUsuario(UsuarioDTO usuarioDTO, int id);
    void eliminarUsuario(int id);


}
