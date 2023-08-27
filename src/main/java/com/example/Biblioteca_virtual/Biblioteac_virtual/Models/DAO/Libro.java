package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.embedded_id.ReservaLibro;
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
@Table(name = "LIBRO")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIBRO")
    private int id_libro;

    @Column(name = "TITULO", length = 20)
    private String titulo;

    @Column(name = "STOCK")
    private int stock;

    @Column(name = "FECHA_PUBLICACION")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fecha_publicacion;

    @Column(name = "RUTA_IMG", length = 250)
    private String ruta_img;

    @Column(name = "ESTADO")
    private boolean estado;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "ID_CATEGORIA", foreignKey = @ForeignKey(name = "FK_ID_CATEGORIA"))
    private Categoria categoria;

    @ManyToOne(targetEntity = Autor.class)
    @JoinColumn(name = "ID_AUTOR", foreignKey = @ForeignKey(name = "FK_ID_AUTOR"))
    private Autor autor;

    @ManyToOne(targetEntity = Editorial.class)
    @JoinColumn(name = "ID_EDITORIAL", foreignKey = @ForeignKey(name = "FK_ID_EDITORIAL"))
    private Editorial editorial;

    @OneToOne(targetEntity = SinopsisLibro.class, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private SinopsisLibro sinopsisLibro;

    @JsonIgnore
    @OneToMany(targetEntity = Inventario.class, mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Inventario> inventarios;

    @JsonIgnore
    @OneToMany(targetEntity = ReservaLibro.class, mappedBy = "libro", cascade = CascadeType.ALL)
    private List<ReservaLibro> reservaLibros;

    public Libro() {
    }
}
