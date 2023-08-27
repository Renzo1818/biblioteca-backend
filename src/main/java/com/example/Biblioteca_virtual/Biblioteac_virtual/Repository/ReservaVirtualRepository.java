package com.example.Biblioteca_virtual.Biblioteac_virtual.Repository;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.ReservaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaVirtualRepository extends JpaRepository<ReservaVirtual, Integer> {
    @Query("SELECT r FROM ReservaVirtual r ORDER BY r.id_reserva DESC LIMIT 1")
    ReservaVirtual correlativoReservaVirtual();
}
