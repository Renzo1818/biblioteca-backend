package com.example.Biblioteca_virtual.Biblioteac_virtual.Repository;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.SinopsisLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinopsisLibroRepository extends JpaRepository<SinopsisLibro, Integer> {
}
