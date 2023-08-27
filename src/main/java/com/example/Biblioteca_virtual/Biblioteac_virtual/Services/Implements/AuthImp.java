package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.JWT.JwtService;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers.UsuarioMapper;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Usuario;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.AuthResponse;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.CredentialsDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.UsuarioDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.UsuarioRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthImp implements IAuth {
    private final UsuarioRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse login(CredentialsDTO credentialsDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentialsDTO.getCorreo_electronico(), credentialsDTO.getContrasena()));
        Usuario usuario = repository.findByCorreo_Electronico(credentialsDTO.getCorreo_electronico()).orElseThrow();
        UserDetails user = repository.findByCorreo_Electronico(credentialsDTO.getCorreo_electronico()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .id_usuario(usuario.getId_usuario())
                .token(token)
                .build();
    }

}