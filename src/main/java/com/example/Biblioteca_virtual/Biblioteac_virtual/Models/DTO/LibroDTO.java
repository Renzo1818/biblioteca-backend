package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class LibroDTO {
    private int id_libro;
    private int id_autor;
    private int id_editorial;
    private int id_categoria;
    private String titulo;
    private int stock;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fecha_publicacion;
    private String ruta_img;
    private boolean estado;

    public LibroDTO() {
    }
}
