package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.embedded_id;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Libro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.ReservaVirtual;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RESERVA_LIBRO")
@Getter @Setter
public class ReservaLibro {
    @EmbeddedId
    private ReservaLibroKey id;

    @ManyToOne(targetEntity = ReservaVirtual.class)
    @JoinColumn(name = "ID_RESERVA", foreignKey = @ForeignKey(name = "FK_ID_RESERVA_DETALLE"))
    @MapsId("id_reserva")
    private ReservaVirtual reservaVirtual;

    @ManyToOne(targetEntity = Libro.class)
    @JoinColumn(name = "ID_LIBRO", foreignKey = @ForeignKey(name = "FK_ID_LIBRO_DETALLE"))
    @MapsId("id_libro")
    private Libro libro;

    @Column(name="CANTIDAD")
    private int cantidad;

    @Column(name = "ESTADO")
    private boolean estado;

    public ReservaLibro() {
    }
}
