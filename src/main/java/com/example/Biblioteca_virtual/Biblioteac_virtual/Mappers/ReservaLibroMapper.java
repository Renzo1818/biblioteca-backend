package com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Libro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.ReservaVirtual;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.embedded_id.ReservaLibro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.ReservaLibroDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.ReservaVirtualRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservaLibroMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReservaVirtualRepository reservaVirtualRepository;

    public ReservaLibroMapper(ModelMapper modelMapper){
        modelMapper.typeMap(ReservaLibroDTO.class, ReservaLibro.class)
                .addMappings(mapper -> mapper.skip(ReservaLibro::setReservaVirtual));
    }

    public ReservaLibroDTO convertirToDto(ReservaLibro reservaLibro){
        return modelMapper.map(reservaLibro, ReservaLibroDTO.class);
    }

    /*public ReservaLibro convertirToEntity(ReservaLibroDTO reservaLibroDTO) {
        ReservaLibro reservaLibro = modelMapper.map(reservaLibroDTO, ReservaLibro.class);

        // Obtiene el último id_libro registrado
        ReservaVirtual ultimaReservaRegistrado = reservaVirtualRepository.correlativoReservaVirtual();

        reservaLibro.setReservaVirtual(ultimaReservaRegistrado); // Asigna el objeto Persona al Usuario

        // Genera el id_usuario con el mismo valor que el id_persona
        reservaLibro.setId(ultimaReservaRegistrado.getId_reserva());

        return sinopsisLibro;
    }*/
}
