package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USUARIO", uniqueConstraints = @UniqueConstraint(name = "UK_CORREO_ELEC", columnNames = "CORREO_ELEC"))
@ToString
public class Usuario implements UserDetails {
    @Id
    @Column(name = "ID_USUARIO")
    @Getter @Setter
    private int id_usuario;

    @Column(name="CORREO_ELEC", length = 40)
    @Getter @Setter
    private String correo_electronico;

    @Column(name = "CONTRASENA", length = 255)
    @Getter @Setter
    private String contrasena;

    @Column(name = "ESTADO")
    @Getter @Setter
    private boolean estado;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId
    @JoinColumn(name="ID_USUARIO", foreignKey = @ForeignKey(name = "FK_ID_USUARIO"))
    @Getter @Setter
    private Persona persona;

    public Usuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(persona.getTipo().getDescripcion()));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correo_electronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
