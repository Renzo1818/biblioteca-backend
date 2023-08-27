package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "SINOPSIS_LIBRO", uniqueConstraints = @UniqueConstraint(name = "UK_DESCRIPCION_SINOPSIS", columnNames = "DESCRIPCION"))
public class SinopsisLibro {
    @Id
    @Column(name = "ID_LIBRO")
    private int id_libro;

    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;

    @OneToOne(targetEntity = Libro.class, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "ID_LIBRO", foreignKey = @ForeignKey(name = "FK_ID_LIBRO_SINOPSIS"))
    private Libro libro;

    public SinopsisLibro() {
    }
}
