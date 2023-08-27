package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
@Table(name = "CATEGORIA")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_CATEGORIA")
    private int id_categoria;

    @Column(name="DESCRIPCION", length = 20)
    private String descripcion;

    @JsonIgnore
    @OneToMany(targetEntity = Libro.class, mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Libro> libros;

    public Categoria() {
    }
}
