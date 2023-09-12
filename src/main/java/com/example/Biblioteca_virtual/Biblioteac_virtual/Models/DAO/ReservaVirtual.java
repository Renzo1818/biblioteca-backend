package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.embedded_id.ReservaLibro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RESERVA_VIRTUAL")
@Getter @Setter
public class ReservaVirtual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA")
    private int id_reserva;

    @Column(name = "FECHA_INICIO")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fecha_inicio;

    @Column(name = "FECHA_FINAL")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fecha_final;

    @Column(name = "ESTADO")
    private boolean estado;

    @ManyToOne(targetEntity = Persona.class)
    @JoinColumn(name = "ID_PERSONA", foreignKey = @ForeignKey(name = "FK_ID_PERSONA_RESERVA"))
    private Persona persona;

    @JsonIgnore
    @OneToMany(targetEntity = ReservaLibro.class, mappedBy = "reservaVirtual", cascade = CascadeType.ALL)
    private List<ReservaLibro> reservaLibros;

    public ReservaVirtual() {
    }
}
