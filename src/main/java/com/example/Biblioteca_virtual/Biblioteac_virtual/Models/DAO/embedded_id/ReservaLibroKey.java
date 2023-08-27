package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.embedded_id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter
public class ReservaLibroKey implements Serializable {
    @Column(name = "ID_RESERVA")
    private int id_reserva;

    @Column(name = "ID_LIBRO")
    private int id_libro;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservaLibroKey that = (ReservaLibroKey) o;

        if (id_reserva != that.id_reserva) return false;
        return id_libro == that.id_libro;
    }

    @Override
    public int hashCode() {
        int result = id_reserva;
        result = 31 * result + id_libro;
        return result;
    }
}
