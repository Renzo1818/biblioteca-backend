package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDTO {
    private int id_usuario;
    private String correo_electronico;
    private String contrasena;
    private boolean estado;
    public UsuarioDTO() {
    }
}
