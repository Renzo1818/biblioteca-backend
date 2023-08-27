package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservaLibroDTO {
    private int id_reserva;
    private int id_libro;
    private int cantidad;
    private boolean estado;
    public ReservaLibroDTO() {
    }
}
