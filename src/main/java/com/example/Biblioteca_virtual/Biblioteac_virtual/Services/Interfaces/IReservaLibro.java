package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces;


import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.ReservaLibroDTO;

import java.util.List;

public interface IReservaLibro {
    List<ReservaLibroDTO> getAllReservaLibros();
    ReservaLibroDTO getReservaLibro(int id_reserva, int id_libro);
    void agregarReservaLibro(ReservaLibroDTO reservaLibroDTO);
    void devolverReservaLibro(int id_reserva, int id_libro);
}
