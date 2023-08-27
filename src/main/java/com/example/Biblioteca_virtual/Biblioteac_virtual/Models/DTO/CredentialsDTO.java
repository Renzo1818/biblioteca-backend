package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO;

import lombok.Data;

@Data
public class CredentialsDTO {
    private String correo_electronico;
    private String contrasena;

    public CredentialsDTO() {
    }
}
