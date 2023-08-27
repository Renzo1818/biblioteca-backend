package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Distrito;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.DistritoRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IDistrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistritoImp implements IDistrito {
    @Autowired
    private DistritoRepository repository;

    @Override
    public List<Distrito> getAllDistritos() {
        return repository.findAll();
    }
}
