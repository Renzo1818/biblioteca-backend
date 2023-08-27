package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Categoria;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.CategoriaRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.ICategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaImp implements ICategoria {
    @Autowired
    private CategoriaRepository repository;
    @Override
    public List<Categoria> getAllCategorias() {
        return repository.findAll();
    }
}
