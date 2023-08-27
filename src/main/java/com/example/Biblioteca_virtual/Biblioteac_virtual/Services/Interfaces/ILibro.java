package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.LibroDTO;

import java.util.List;

public interface ILibro {
    List<LibroDTO> getAllLibros();
    LibroDTO getLibro(int id);
    void agregarLibro(LibroDTO libroDTO);
    void modificarLibro(LibroDTO libroDTO, int id);
    void eliminarLibro(int id);
}
