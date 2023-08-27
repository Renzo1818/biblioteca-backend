package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "AUTOR")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AUTOR")
    private int id_autor;

    @Column(name="NOMBRE", length = 40)
    private String nombre;

    @Column(name = "APELLIDO", length = 60)
    private String apellido;

    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fecha_nacimiento;

    @Column(name = "NACIONALIDAD", length = 20)
    private String nacionalidad;

    @Column(name = "ESTADO")
    private boolean estado;

    @JsonIgnore
    @OneToMany(targetEntity = Libro.class, mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> libros;

    public Autor() {
    }
}
