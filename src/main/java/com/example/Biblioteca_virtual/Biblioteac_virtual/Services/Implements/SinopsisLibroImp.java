package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers.SinopsisLibroMapper;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Libro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.SinopsisLibro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.SinopsisLibroDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.SinopsisLibroRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.ISinopsisLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SinopsisLibroImp implements ISinopsisLibro {
    @Autowired
    private SinopsisLibroRepository repository;
    @Autowired
    private SinopsisLibroMapper mapper;

    @Override
    public List<SinopsisLibroDTO> getAllSinopsis() {
        List<SinopsisLibro> lstSinopsis = repository.findAll();
        List<SinopsisLibroDTO> lstSinopsisDTO = new ArrayList<>();
        for(SinopsisLibro sinopsisLibro: lstSinopsis){
            lstSinopsisDTO.add(mapper.convertirToDto(sinopsisLibro));
        }
        return lstSinopsisDTO;
    }

    @Override
    public SinopsisLibroDTO getSinopsis(int id) {
        Optional<SinopsisLibro> optionalSinopsisLibro = repository.findById(id);
        if(optionalSinopsisLibro.isPresent()){
            return mapper.convertirToDto(optionalSinopsisLibro.get());
        }
        else{
            throw new NoSuchElementException("No se encontró la sinopsis con ID: " + id);
        }
    }

    @Override
    public void agregarSinopsis(SinopsisLibroDTO sinopsisLibroDTO) {
        SinopsisLibro sinopsisLibro = mapper.convertirToEntity(sinopsisLibroDTO);
        repository.save(sinopsisLibro);
    }

    @Override
    public void modificarSinopsis(SinopsisLibroDTO sinopsisLibroDTO, int id) {
        Optional<SinopsisLibro> optionalSinopsisLibro = repository.findById(id);
        if(optionalSinopsisLibro.isPresent()){
            SinopsisLibro sinopsisLibro = new SinopsisLibro();
            sinopsisLibro.setDescripcion(sinopsisLibroDTO.getDescripcion());

            Libro libro = new Libro();
            libro.setId_libro(sinopsisLibroDTO.getId_libro());
            sinopsisLibro.setLibro(libro);

            repository.save(sinopsisLibro);

        }
        else{
            throw new NoSuchElementException("No se encontró la sinopsis con ID: " + id);
        }
    }

    @Override
    public void eliminarSinopsis(int id) {
        Optional<SinopsisLibro> optionalSinopsisLibro = repository.findById(id);
        if(optionalSinopsisLibro.isPresent()){
            repository.delete(optionalSinopsisLibro.get());
        }
        else{
            throw new NoSuchElementException("No se encontró la sinopsis con ID: " + id);
        }
    }
}
