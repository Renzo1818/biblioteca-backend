package com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Persona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Usuario;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.UsuarioDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UsuarioMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioMapper(ModelMapper modelMapper) {
        modelMapper.typeMap(UsuarioDTO.class, Usuario.class)
                .addMappings(mapper -> mapper.skip(Usuario::setId_usuario));
    }

    private void configureMappings() {
        modelMapper.createTypeMap(Usuario.class, UsuarioDTO.class)
                .addMapping(Usuario::getId_usuario, UsuarioDTO::setId_usuario)
                .addMapping(Usuario::getCorreo_electronico, UsuarioDTO::setCorreo_electronico)
                .addMapping(Usuario::getContrasena, UsuarioDTO::setContrasena)
                .addMapping(Usuario::isEstado, UsuarioDTO::setEstado);
    }
    public UsuarioDTO convertirToDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Usuario convertirToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);

        // Obtiene el último id_persona registrado
        Persona ultimaPersonaRegistrada = personaRepository.findTopByOrderByIdPersonaDesc();

        usuario.setPersona(ultimaPersonaRegistrada); // Asigna el objeto Persona al Usuario

        // Genera el id_usuario con el mismo valor que el id_persona
        usuario.setId_usuario(ultimaPersonaRegistrada.getId_persona());
        usuario.setContrasena(passwordEncoder.encode(usuarioDTO.getContrasena()));

        return usuario;
    }
}
