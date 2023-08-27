package com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Autor;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Categoria;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Editorial;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Libro;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.LibroDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public LibroMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<Libro, LibroDTO>() {
            @Override
            protected void configure() {
                map().setId_autor(source.getAutor().getId_autor());
                map().setId_editorial(source.getEditorial().getId_editorial());
                map().setId_categoria(source.getCategoria().getId_categoria());
            }
        });
        modelMapper.addMappings(new PropertyMap<LibroDTO, Libro>() {
            @Override
            protected void configure() {
                using(context -> {
                    LibroDTO dto = (LibroDTO) context.getSource();
                    Integer idAutor = dto.getId_autor();
                    if (idAutor != null) {
                        Autor autor = new Autor();
                        autor.setId_autor(idAutor);
                        return autor;
                    } else {
                        return null;
                    }
                }).map(source, destination.getAutor());

                using(context -> {
                    LibroDTO dto = (LibroDTO) context.getSource();
                    Integer idEditorial = dto.getId_editorial();
                    if (idEditorial != null) {
                        Editorial editorial = new Editorial();
                        editorial.setId_editorial(idEditorial);
                        return editorial;
                    } else {
                        return null;
                    }
                }).map(source, destination.getEditorial());

                using(context ->{
                    LibroDTO dto = (LibroDTO) context.getSource();
                    Integer idCategoria = dto.getId_categoria();
                    if(idCategoria != null){
                        Categoria categoria = new Categoria();
                        categoria.setId_categoria(idCategoria);
                        return categoria;
                    }
                    else{
                        return null;
                    }
                }).map(source, destination.getCategoria());
            }
        });
    }

    public LibroDTO convertirToDto(Libro libro){
        return modelMapper.map(libro, LibroDTO.class);
    }

    public Libro convertirToEntity(LibroDTO libroDTO){
        return modelMapper.map(libroDTO, Libro.class);
    }
}
