package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers.ReservaVirtualMapper;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.ReservaVirtual;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.ReservaVirtualDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.ReservaVirtualRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IReservaVirtual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservaVirtualImp implements IReservaVirtual {
    @Autowired
    private ReservaVirtualRepository repository;
    @Autowired
    private ReservaVirtualMapper mapper;

    @Override
    public List<ReservaVirtualDTO> getAllReservas() {
        List<ReservaVirtual> lstReservas = repository.findAll();
        List<ReservaVirtualDTO> reservaVirtualDTOS = new ArrayList<>();
        for(ReservaVirtual reservaVirtual: lstReservas){
            reservaVirtualDTOS.add(mapper.convertirToDto(reservaVirtual));
        }
        return reservaVirtualDTOS;
    }

    @Override
    public ReservaVirtualDTO getReserva(int id) {
        Optional<ReservaVirtual> optionalReservaVirtual = repository.findById(id);
        if(optionalReservaVirtual.isPresent()){
            return mapper.convertirToDto(optionalReservaVirtual.get());
        }
        else{
            throw new NoSuchElementException("No se encontró la reserva con el id: " + id);
        }
    }

    @Override
    public void agregarReserva(ReservaVirtualDTO reservaVirtualDTO) {
        ReservaVirtual reservaVirtual = mapper.convertirToEntity(reservaVirtualDTO);
        repository.save(reservaVirtual);
    }

    @Override
    public void devolverReserva(int id) {
        Optional<ReservaVirtual> optionalReservaVirtual = repository.findById(id);
        if(optionalReservaVirtual.isPresent()){
            ReservaVirtual reservaVirtual = new ReservaVirtual();
            reservaVirtual.setId_reserva(id);
            reservaVirtual.setEstado(true);
            repository.save(reservaVirtual);
        }
        else{
            throw new NoSuchElementException("No se encontró la reserva con el id: " + id);
        }
    }
}
