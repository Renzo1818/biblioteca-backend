package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="INVENTARIO")
@Getter @Setter
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INVENTARIO")
    private int id_inventario;

    @Column(name = "CANTIDAD_LIBROS")
    private int cantidad_libros;

    @Column(name = "FECHA_REGISTRO")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fecha_registro;

    @ManyToOne(targetEntity = Persona.class)
    @JoinColumn(name = "ID_PERSONA", foreignKey = @ForeignKey(name = "FK_ID_PERSONA_INVEN"))
    private Persona persona;

    @ManyToOne(targetEntity = Libro.class)
    @JoinColumn(name = "ID_LIBRO", foreignKey = @ForeignKey(name = "FK_ID_LIBRO_INVEN"))
    private Libro libro;

    public Inventario() {
    }
}
