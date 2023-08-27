package com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Distrito;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Persona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.TipoPersona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.PersonaDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PersonaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<Persona, PersonaDTO>() {
            @Override
            protected void configure() {
                map().setId_tipo_persona(source.getTipo().getId_tipo_persona());
                map().setId_distrito(source.getDistrito().getId_distrito());
            }
        });
        modelMapper.addMappings(new PropertyMap<PersonaDTO, Persona>() {
            @Override
            protected void configure() {
                using(context -> {
                    PersonaDTO dto = (PersonaDTO) context.getSource();
                    Integer idTipoPersona = dto.getId_tipo_persona();
                    if (idTipoPersona != null) {
                        TipoPersona tipoPersona = new TipoPersona();
                        tipoPersona.setId_tipo_persona(idTipoPersona);
                        return tipoPersona;
                    } else {
                        return null;
                    }
                }).map(source, destination.getTipo());

                using(context -> {
                    PersonaDTO dto = (PersonaDTO) context.getSource();
                    Integer idDistrito = dto.getId_distrito();
                    if (idDistrito != null) {
                        Distrito distrito = new Distrito();
                        distrito.setId_distrito(idDistrito);
                        return distrito;
                    } else {
                        return null;
                    }
                }).map(source, destination.getDistrito());
            }
        });
    }


    public PersonaDTO convertirToDto(Persona persona) {
        return modelMapper.map(persona, PersonaDTO.class);
    }

    public Persona convertirToEntity(PersonaDTO personaDTO) {
        return modelMapper.map(personaDTO, Persona.class);
    }
}
