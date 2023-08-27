package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TIPO_PERSONA")
public class TipoPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_TIPO_PERSONA")
    @Getter @Setter
    private int id_tipo_persona;

    @Column(name = "DESCRIPCION", length = 20)
    @Getter @Setter
    private String descripcion;

    @JsonIgnore
    @OneToMany(targetEntity = Persona.class, mappedBy = "tipo", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<Persona> personas;

    public TipoPersona() {
    }
}
