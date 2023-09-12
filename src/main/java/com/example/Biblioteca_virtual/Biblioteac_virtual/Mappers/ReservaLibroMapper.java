package com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Libro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.ReservaVirtual;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.embedded_id.ReservaLibro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.embedded_id.ReservaLibroKey;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.ReservaLibroDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.ReservaVirtualRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservaLibroMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReservaVirtualRepository reservaVirtualRepository;

    @Autowired
    public ReservaLibroMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(ReservaLibroDTO.class, ReservaLibro.class)
                .addMappings(mapper -> mapper.skip(ReservaLibro::setReservaVirtual));
        modelMapper.addMappings(new PropertyMap<ReservaLibro, ReservaLibroDTO>() {
            @Override
            protected void configure() {
                map().setId_reserva(source.getReservaVirtual().getId_reserva());
                map().setId_libro(source.getLibro().getId_libro());
            }
        });
        modelMapper.addMappings(new PropertyMap<ReservaLibroDTO, ReservaLibro>() {
            @Override
            protected void configure() {
                using(context -> {
                    ReservaLibroDTO dto = (ReservaLibroDTO) context.getSource();
                    Integer idLibro = dto.getId_libro();
                    if (idLibro != null) {
                        Libro libro = new Libro();
                        libro.setId_libro(idLibro);
                        return libro;
                    } else {
                        return null;
                    }
                }).map(source, destination.getLibro());
            }
        });
    }

    public ReservaLibroDTO convertirToDto(ReservaLibro reservaLibro){
        return modelMapper.map(reservaLibro, ReservaLibroDTO.class);
    }

    public ReservaLibro convertirToEntity(ReservaLibroDTO reservaLibroDTO) {
        ReservaLibro reservaLibro = modelMapper.map(reservaLibroDTO, ReservaLibro.class);

        // Obtiene el último id_libro registrado
        ReservaVirtual ultimaReservaRegistrado = reservaVirtualRepository.correlativoReservaVirtual();

        reservaLibro.setReservaVirtual(ultimaReservaRegistrado); // Asigna el objeto ReservaVirtual a ReservaLibro


        // Genera el id_reserva con el mismo valor que el id_reserva
        reservaLibro.setId(new ReservaLibroKey(ultimaReservaRegistrado.getId_reserva(), reservaLibroDTO.getId_libro()));

        return reservaLibro;
    }
}
