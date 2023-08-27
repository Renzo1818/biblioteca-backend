package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Autor;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.AutorRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AutorImp implements IAutor {
    @Autowired
    private AutorRepository repository;
    @Override
    public List<Autor> getAllAutores() {
        return repository.findAll();
    }

    @Override
    public Autor getAutor(int id) {
        Optional<Autor> optionalAutor = repository.findById(id);
        if(optionalAutor.isPresent()){
            return optionalAutor.get();
        }
        else{
            throw new NoSuchElementException("No se encontró el autor con ID: " + id);
        }
    }

    @Override
    public void guardarAutor(Autor autor) {
        repository.save(autor);
    }

    @Override
    public void modifcarAutor(Autor autor, int id) {
        Optional<Autor> optionalAutor = repository.findById(id);
        if(optionalAutor.isPresent()){
            Autor autorM = optionalAutor.get();
            autorM.setNombre(autor.getNombre());
            autorM.setApellido(autor.getApellido());
            autorM.setFecha_nacimiento(autor.getFecha_nacimiento());
            autorM.setNacionalidad(autor.getNacionalidad());
            autorM.setEstado(autor.isEstado());
            repository.save(autorM);
        }
        else{
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    @Override
    public void eliminar(int id) {
        Optional<Autor> optionalAutor = repository.findById(id);
        if(optionalAutor.isPresent()){
            repository.delete(optionalAutor.get());
        }
        else{
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }
}
