package com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers;


import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Inventario;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Libro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Persona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.InventarioDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public InventarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<Inventario, InventarioDTO>() {
            @Override
            protected void configure() {
                map().setId_persona(source.getPersona().getId_persona());
                map().setId_libro(source.getLibro().getId_libro());
            }
        });
        modelMapper.addMappings(new PropertyMap<InventarioDTO, Inventario>() {
            @Override
            protected void configure() {
                using(context -> {
                    InventarioDTO dto = (InventarioDTO) context.getSource();
                    Integer idPersona = dto.getId_persona();
                    if (idPersona != null) {
                        Persona persona = new Persona();
                        persona.setId_persona(idPersona);
                        return persona;
                    } else {
                        return null;
                    }
                }).map(source, destination.getPersona());

                using(context -> {
                    InventarioDTO dto = (InventarioDTO) context.getSource();
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

    public InventarioDTO convertirToDto(Inventario inventario){
        return modelMapper.map(inventario, InventarioDTO.class);
    }

    public Inventario convertirToEntity(InventarioDTO inventarioDTO){
        return modelMapper.map(inventarioDTO, Inventario.class);
    }

}
