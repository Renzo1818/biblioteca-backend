package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.SinopsisLibroDTO;

import java.util.List;

public interface ISinopsisLibro {
    List<SinopsisLibroDTO> getAllSinopsis();
    SinopsisLibroDTO getSinopsis(int id);
    void agregarSinopsis(SinopsisLibroDTO sinopsisLibroDTO);
    void modificarSinopsis(SinopsisLibroDTO sinopsisLibroDTO, int id);
    void eliminarSinopsis(int id);
}
