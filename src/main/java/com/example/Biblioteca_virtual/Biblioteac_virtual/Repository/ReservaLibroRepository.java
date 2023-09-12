package com.example.Biblioteca_virtual.Biblioteac_virtual.Repository;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.embedded_id.ReservaLibro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.embedded_id.ReservaLibroKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaLibroRepository extends JpaRepository<ReservaLibro, ReservaLibroKey> {
    @Query("SELECT r FROM ReservaLibro r WHERE r.id.id_reserva = :id_reserva AND r.estado = false")
    List<ReservaLibro> getAllReservaLibroById(@Param("id_reserva") int id_reserva);
    @Query("SELECT r FROM ReservaLibro r WHERE r.id.id_reserva = :id_reserva AND r.id.id_libro = :id_libro AND r.estado = false")
    Optional<ReservaLibro> getReservaLibroByIdStatus(@Param("id_reserva") int id_reserva, @Param("id_libro") int id_libro);
}
