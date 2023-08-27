package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
@Table(name = "EDITORIAL", uniqueConstraints = {
        @UniqueConstraint(name = "UK_RAZON_SOCIAL", columnNames = "RAZON_SOCIAL"),
        @UniqueConstraint(name = "UK_RUC", columnNames = "RUC"),
        @UniqueConstraint(name = "UK_CORREO_ELEC", columnNames = "CORREO_ELECTRONICO")
})
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EDITORIAL")
    private int id_editorial;

    @Column(name="RAZON_SOCIAL", length = 40)
    private String razon_social;

    @Column(name="RUC", length = 11)
    private String ruc;

    @Column(name = "CORREO_ELECTRONICO", length = 40)
    private String correo_electronico;

    @Column(name = "ESTADO")
    private boolean estado;

    @JsonIgnore
    @OneToMany(targetEntity = Libro.class, mappedBy = "editorial", cascade = CascadeType.ALL)
    private List<Libro> libros;

    public Editorial() {
    }
}
