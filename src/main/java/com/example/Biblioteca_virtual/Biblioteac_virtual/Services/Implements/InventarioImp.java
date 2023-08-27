package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers.InventarioMapper;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Inventario;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Libro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Persona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.InventarioDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.InventarioRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InventarioImp implements IInventario {
    @Autowired
    private InventarioRepository repository;
    @Autowired
    private InventarioMapper mapper;

    @Override
    public List<InventarioDTO> getAllInventarios() {
        List<Inventario> lstInventarios = repository.findAll();
        List<InventarioDTO> lstInvetariosDTO  = new ArrayList<>();
        for(Inventario inventario: lstInventarios){
            lstInvetariosDTO.add(mapper.convertirToDto(inventario));
        }
        return lstInvetariosDTO;
    }

    @Override
    public InventarioDTO getInventario(int id) {
        Optional<Inventario> optionalInventario = repository.findById(id);
        if(optionalInventario.isPresent()){
            return mapper.convertirToDto(optionalInventario.get());
        }
        else{
            throw new NoSuchElementException("No se encontró el inventario con el id: " + id);
        }
    }

    @Override
    public void agregarInventario(InventarioDTO inventarioDTO) {
        Inventario inventario = mapper.convertirToEntity(inventarioDTO);
        repository.save(inventario);
    }

    @Override
    public void modificarInventario(InventarioDTO inventarioDTO, int id) {
        Optional<Inventario> optionalInventario = repository.findById(id);
        if(optionalInventario.isPresent()){
            Inventario inventario = new Inventario();
            inventario.setId_inventario(inventarioDTO.getId_inventario());
            inventario.setCantidad_libros(inventarioDTO.getCantidad_libros());
            inventario.setFecha_registro(inventarioDTO.getFecha_registro());

            Persona persona = new Persona();
            persona.setId_persona(inventarioDTO.getId_persona());
            inventario.setPersona(persona);

            Libro libro = new Libro();
            libro.setId_libro(inventarioDTO.getId_libro());
            inventario.setLibro(libro);

            repository.save(inventario);
        }
        else{
            throw new NoSuchElementException("No se encontró el inventario con el id: " + id);
        }
    }

    @Override
    public void eliminarInventario(int id) {
        Optional<Inventario> optionalInventario = repository.findById(id);
        if(optionalInventario.isPresent()){
            repository.delete(optionalInventario.get());
        }
        else{
            throw new NoSuchElementException("No se encontró el inventario con el id: " + id);
        }
    }
}
