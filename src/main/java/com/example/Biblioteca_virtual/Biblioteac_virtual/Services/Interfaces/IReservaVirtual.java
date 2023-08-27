package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.ReservaVirtualDTO;

import java.util.List;

public interface IReservaVirtual {
    List<ReservaVirtualDTO> getAllReservas();
    ReservaVirtualDTO getReserva(int id);
    void agregarReserva(ReservaVirtualDTO reservaVirtualDTO);
    void devolverReserva(int id);

}
