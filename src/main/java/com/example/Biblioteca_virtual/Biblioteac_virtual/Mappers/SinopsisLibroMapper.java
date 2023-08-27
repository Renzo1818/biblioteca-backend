package com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Libro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.SinopsisLibro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.SinopsisLibroDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.LibroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SinopsisLibroMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LibroRepository libroRepository;

    public SinopsisLibroMapper(ModelMapper modelMapper){
        modelMapper.typeMap(SinopsisLibroDTO.class, SinopsisLibro.class)
                .addMappings(mapper -> mapper.skip(SinopsisLibro::setId_libro));
    }

    public SinopsisLibroDTO convertirToDto(SinopsisLibro sinopsisLibro){
        return modelMapper.map(sinopsisLibro, SinopsisLibroDTO.class);
    }

    public SinopsisLibro convertirToEntity(SinopsisLibroDTO sinopsisLibroDTO) {
        SinopsisLibro sinopsisLibro = modelMapper.map(sinopsisLibroDTO, SinopsisLibro.class);

        // Obtiene el último id_libro registrado
        Libro ultimaLibroRegistrado = libroRepository.findTopByOrderByIdLibroDesc();

        sinopsisLibro.setLibro(ultimaLibroRegistrado); // Asigna el objeto Persona al Usuario

        // Genera el id_usuario con el mismo valor que el id_persona
        sinopsisLibro.setId_libro(ultimaLibroRegistrado.getId_libro());

        return sinopsisLibro;
    }
}
