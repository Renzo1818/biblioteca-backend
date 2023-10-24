package com.example.Biblioteca_virtual.Biblioteac_virtual.Repository;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Optional<Autor> findByApellido(String apellido);
}
