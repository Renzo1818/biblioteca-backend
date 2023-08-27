package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class PersonaDTO {
    private int id_persona;
    private int id_tipo_persona;
    private int id_distrito;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;

    public PersonaDTO() {
    }
}
