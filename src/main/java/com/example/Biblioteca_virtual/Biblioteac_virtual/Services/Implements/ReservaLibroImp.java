package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers.ReservaLibroMapper;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.embedded_id.ReservaLibro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.embedded_id.ReservaLibroKey;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.ReservaLibroDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.ReservaLibroRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IReservaLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservaLibroImp implements IReservaLibro {
    @Autowired
    private ReservaLibroRepository repository;
    @Autowired
    private ReservaVirtualImp reservaVirtualImp;
    @Autowired
    private ReservaLibroMapper mapper;
    @Override
    public List<ReservaLibroDTO> getAllReservaLibros() {
        List<ReservaLibro> reservaLibros = repository.findAll();
        List<ReservaLibroDTO> reservaLibroDTOS = new ArrayList<>();
        for(ReservaLibro reservaLibro: reservaLibros){
            ReservaLibroDTO reservaLibroDTO = mapper.convertirToDto(reservaLibro);
            reservaLibroDTOS.add(reservaLibroDTO);
        }
        return reservaLibroDTOS;
    }

    @Override
    public ReservaLibroDTO getReservaLibro(int id_reserva, int id_libro) {
        Optional<ReservaLibro> optionalReservaLibro = repository.findById(new ReservaLibroKey(id_reserva,id_libro));
        if(optionalReservaLibro.isPresent()){
            ReservaLibro reservaLibro = optionalReservaLibro.get();
            return mapper.convertirToDto(reservaLibro);
        }
        throw new NoSuchElementException("No se encontró la reserva con los ID's: " + id_reserva + " " + id_libro);
    }

    @Override
    public void agregarReservaLibro(ReservaLibroDTO reservaLibroDTO) {
        ReservaLibro reservaLibro = mapper.convertirToEntity(reservaLibroDTO);
        repository.save(reservaLibro);
    }

    @Override
    public void devolverReservaLibro(int id_reserva, int id_libro) {
        Optional<ReservaLibro> optionalReservaLibro = repository.getReservaLibroByIdStatus(id_reserva, id_libro);

        if(optionalReservaLibro.isPresent()){
            ReservaLibro reservaLibro = optionalReservaLibro.get();
            reservaLibro.setEstado(true);
            repository.save(reservaLibro);

            List<ReservaLibro> reservaLibros = repository.getAllReservaLibroById(id_reserva);
            if(reservaLibros.size() == 0){
                reservaVirtualImp.devolverReserva(id_reserva);
            }
        }
        throw new NoSuchElementException("No se encontro la reserva con los ID's: " + id_reserva + " " + id_libro);


    }
}
