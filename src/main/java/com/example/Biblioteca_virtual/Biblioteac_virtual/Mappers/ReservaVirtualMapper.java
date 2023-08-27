package com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers;


import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Persona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.ReservaVirtual;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.ReservaVirtualDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservaVirtualMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ReservaVirtualMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<ReservaVirtual, ReservaVirtualDTO>() {
            @Override
            protected void configure() {
                map().setId_persona(source.getPersona().getId_persona());
            }
        });
        modelMapper.addMappings(new PropertyMap<ReservaVirtualDTO, ReservaVirtual>() {
            @Override
            protected void configure() {
                using(context -> {
                    ReservaVirtualDTO dto = (ReservaVirtualDTO) context.getSource();
                    Integer idPersona = dto.getId_persona();
                    if (idPersona != null) {
                        Persona persona = new Persona();
                        persona.setId_persona(idPersona);
                        return persona;
                    } else {
                        return null;
                    }
                }).map(source, destination.getPersona());
            }
        });
    }

    public ReservaVirtualDTO convertirToDto(ReservaVirtual reservaVirtual){
        return modelMapper.map(reservaVirtual, ReservaVirtualDTO.class);
    }

    public ReservaVirtual convertirToEntity(ReservaVirtualDTO reservaVirtualDTO){
        return modelMapper.map(reservaVirtualDTO, ReservaVirtual.class);
    }
}
