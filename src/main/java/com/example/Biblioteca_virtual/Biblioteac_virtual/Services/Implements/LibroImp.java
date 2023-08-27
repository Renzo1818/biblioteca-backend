package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers.LibroMapper;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Autor;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Categoria;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Editorial;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Libro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.LibroDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.LibroRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.ILibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LibroImp implements ILibro {
    @Autowired
    private LibroRepository repository;
    @Autowired
    private LibroMapper mapper;
    @Override
    public List<LibroDTO> getAllLibros() {
        List<Libro> libros = repository.findAll();
        List<LibroDTO> libroDTOS = new ArrayList<>();
        for(Libro libro: libros){
            LibroDTO libroDTO = mapper.convertirToDto(libro);
            libroDTOS.add(libroDTO);
        }
        return libroDTOS;
    }

    @Override
    public LibroDTO getLibro(int id) {
        Optional<Libro> optionalLibro = repository.findById(id);
        if(optionalLibro.isPresent()){
            return mapper.convertirToDto(optionalLibro.get());
        }
        else{
            throw new NoSuchElementException("No se encontró el libro con ID: " + id);
        }
    }

    @Override
    public void agregarLibro(LibroDTO libroDTO) {
        Libro libro = mapper.convertirToEntity(libroDTO);
        repository.save(libro);
    }

    @Override
    public void modificarLibro(LibroDTO libroDTO, int id) {
        Optional<Libro> optionalLibro = repository.findById(id);
        if(optionalLibro.isPresent()) {
            Libro libro = optionalLibro.get();

            Autor autor = new Autor();
            autor.setId_autor(libroDTO.getId_autor());
            libro.setAutor(autor);

            Editorial editorial = new Editorial();
            editorial.setId_editorial(libroDTO.getId_editorial());
            libro.setEditorial(editorial);

            Categoria categoria = new Categoria();
            categoria.setId_categoria(libroDTO.getId_categoria());
            libro.setCategoria(categoria);

            libro.setTitulo(libroDTO.getTitulo());
            libro.setStock(libroDTO.getStock());
            libro.setFecha_publicacion(libroDTO.getFecha_publicacion());
            libro.setRuta_img(libroDTO.getRuta_img());
            libro.setEstado(libroDTO.isEstado());
            repository.save(libro);
        }
        else{
            throw new NoSuchElementException("No se encontró el libro con ID: " + id);
        }
    }

    @Override
    public void eliminarLibro(int id) {
        Optional<Libro> optionalLibro = repository.findById(id);
        if(optionalLibro.isPresent()){
            repository.delete(optionalLibro.get());
        }
        else{
            throw new NoSuchElementException("No se encontró el libro con ID: " + id);
        }
    }
}
