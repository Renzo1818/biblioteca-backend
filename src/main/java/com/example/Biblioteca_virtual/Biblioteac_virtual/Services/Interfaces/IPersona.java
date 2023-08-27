package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.PersonaDTO;

import java.util.List;

public interface IPersona {
    List<PersonaDTO> getAllPersonas();
    PersonaDTO getPersona(int id);
    void guardarPersona(PersonaDTO persona);
    void modificarPersona(PersonaDTO persona, int id);
    void eliminarPersona(int id);
}
