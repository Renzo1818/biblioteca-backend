package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoria {
    List<Categoria> getAllCategorias();
}
