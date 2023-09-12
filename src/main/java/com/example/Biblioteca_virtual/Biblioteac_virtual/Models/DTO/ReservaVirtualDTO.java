package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class ReservaVirtualDTO {
    private int id_reserva;
    private int id_persona;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fecha_inicio;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fecha_final;
    private boolean estado;

    public ReservaVirtualDTO() {
    }
}
