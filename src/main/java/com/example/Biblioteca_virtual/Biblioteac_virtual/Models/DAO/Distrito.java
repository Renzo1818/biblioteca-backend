package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="DISTRITOS")
public class Distrito {
    @Id
    @Column(name = "ID_DISTRITO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id_distrito;

    @Column(name="DESCRIPCION", length = 30)
    @Getter @Setter
    private String descripcion;

    @JsonIgnore
    @OneToMany(targetEntity = Persona.class, mappedBy = "distrito", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<Persona> personas;

    public Distrito() {
    }
}
