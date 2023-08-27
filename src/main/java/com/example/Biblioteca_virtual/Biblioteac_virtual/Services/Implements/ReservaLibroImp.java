package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers.ReservaLibroMapper;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.ReservaLibroDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.ReservaLibroRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IReservaLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaLibroImp implements IReservaLibro {
    @Autowired
    private ReservaLibroRepository repository;
    @Autowired
    private ReservaLibroMapper mapper;
    @Override
    public List<ReservaLibroDTO> getAllReservaLibros() {
        return null;
    }

    @Override
    public ReservaLibroDTO getReservaLibro(int id) {
        return null;
    }

    @Override
    public void agregarReservaLibro(ReservaLibroDTO reservaLibroDTO) {

    }

    @Override
    public void devolverReservaLibro(ReservaLibroDTO reservaLibroDTO) {

    }
}
