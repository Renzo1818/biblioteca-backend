package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.InventarioDTO;

import java.util.List;

public interface IInventario {
    List<InventarioDTO> getAllInventarios();
    InventarioDTO getInventario(int id);
    void agregarInventario(InventarioDTO inventarioDTO);
    void modificarInventario(InventarioDTO inventarioDTO, int id);
    void eliminarInventario(int id);
}
