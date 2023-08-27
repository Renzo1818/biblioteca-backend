package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers.UsuarioMapper;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Persona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Usuario;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.UsuarioDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.UsuarioRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioImp implements IUsuario, UserDetailsService {
    @Autowired
    private UsuarioRepository repository;
    /*@Autowired
    private PasswordEncoder passwordEncoder;*/
    @Autowired
    private UsuarioMapper mapper;

    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios =  repository.findAll();
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for(Usuario usuario: usuarios){
            UsuarioDTO usuarioDTO = mapper.convertirToDto(usuario);
            usuarioDTOS.add(usuarioDTO);
        }
        return usuarioDTOS;
    }

    @Override
    public UsuarioDTO getUsuario(int id) {
        Optional<Usuario> optionalUsuario =  repository.findById(id);

        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            return mapper.convertirToDto(usuario);
        }
        else{
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    @Override
    public void guardarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.convertirToEntity(usuarioDTO);
        repository.save(usuario);
    }

    @Override
    public void modificarUsuario(UsuarioDTO usuarioDTO, int id) {
        Optional<Usuario> optionalUsuario = repository.findById(id);
        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            usuario.setCorreo_electronico(usuarioDTO.getCorreo_electronico());
            usuario.setContrasena(usuarioDTO.getContrasena());

            Persona persona = new Persona();
            persona.setId_persona(usuarioDTO.getId_usuario());
            usuario.setPersona(persona);

            repository.save(usuario);
        }
        else{
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    @Override
    public void eliminarUsuario(int id) {
        Optional<Usuario> optionalUsuario = repository.findById(id);
        if(optionalUsuario.isPresent()){
            repository.delete(optionalUsuario.get());
        }
        else{
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario =  repository
                .findByCorreo_Electronico(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con correo electronico" + email + "no existe"));
        return usuario;
    }

    /*@Override
    public UsuarioDTO buscarCorreo_Electronico(String email) {
        Optional<Usuario> optionalUsuario = repository.findByCorreo_Electronico(email);
        if(optionalUsuario.isPresent()){
            return mapper.convertirToDto(optionalUsuario.get());
        }
        else{
            throw new NoSuchElementException("No se encontró el usuario con email: " + email);
        }
    }*/
}
