package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers.PersonaMapper;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Distrito;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Persona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.TipoPersona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.PersonaDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.PersonaRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonaImp implements IPersona {
    @Autowired
    private PersonaRepository repository;
    @Autowired
    private PersonaMapper mapper;

    @Override
    public List<PersonaDTO> getAllPersonas() {
        List<Persona> personas = repository.findAll();
        List<PersonaDTO> personasDTO = new ArrayList<>();

        for(Persona persona: personas){
            PersonaDTO personaDTO = mapper.convertirToDto(persona);
            personasDTO.add(personaDTO);
        }
        return personasDTO;
    }

    @Override
    public PersonaDTO getPersona(int id) {
        Optional<Persona> optionalPersona = repository.findById(id);
        if (optionalPersona.isPresent()) {
            Persona persona = optionalPersona.get();
            return mapper.convertirToDto(persona);
        }
        return null;
    }

    @Override
    public void guardarPersona(PersonaDTO personaDTO) {
        Persona persona = mapper.convertirToEntity(personaDTO);
        /*Persona persona = new Persona();

        TipoPersona tipoPersona = new TipoPersona();
        tipoPersona.setId_tipo_persona(personaDTO.getId_tipo_persona());
        persona.setTipo(tipoPersona);

        Distrito distrito = new Distrito();
        distrito.setId_distrito(personaDTO.getId_distrito());
        persona.setDistrito(distrito);

        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setDni(personaDTO.getDni());
        persona.setTelefono(personaDTO.getTelefono());*/

        repository.save(persona);
    }

    @Override
    public void modificarPersona(PersonaDTO personaDTO, int id) {
        Optional<Persona> optionalPersona = repository.findById(id);
        if(optionalPersona.isPresent()){
            Persona persona = optionalPersona.get();

            TipoPersona tipoPersona = new TipoPersona();
            tipoPersona.setId_tipo_persona(personaDTO.getId_tipo_persona());
            persona.setTipo(tipoPersona);

            Distrito distrito = new Distrito();
            distrito.setId_distrito(personaDTO.getId_distrito());
            persona.setDistrito(distrito);

            persona.setNombre(personaDTO.getNombre());
            persona.setApellido(personaDTO.getApellido());
            persona.setDni(personaDTO.getDni());
            persona.setTelefono(personaDTO.getTelefono());
            repository.save(persona);
        }
        else{
            throw new NoSuchElementException("No se encontró la persona con ID: " + id);
        }
    }

    @Override
    public void eliminarPersona(int id) {
        Optional<Persona> optionalPersona = repository.findById(id);
        if (optionalPersona.isPresent()){
            repository.delete(optionalPersona.get());
        }
        else{
            throw new NoSuchElementException("No se encontró la persona con ID: " + id);
        }


    }
}
