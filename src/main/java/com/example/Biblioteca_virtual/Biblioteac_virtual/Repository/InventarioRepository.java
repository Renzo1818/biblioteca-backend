package com.example.Biblioteca_virtual.Biblioteac_virtual.Repository;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
}
