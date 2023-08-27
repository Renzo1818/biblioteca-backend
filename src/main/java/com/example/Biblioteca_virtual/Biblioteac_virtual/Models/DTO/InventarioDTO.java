package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class InventarioDTO {
    private int id_inventario;
    private int id_persona;
    private int id_libro;
    private int cantidad_libros;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fecha_registro;

    public InventarioDTO(){
    }
}
