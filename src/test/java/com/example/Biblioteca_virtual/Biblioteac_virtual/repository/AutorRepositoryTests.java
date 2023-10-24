package com.example.Biblioteca_virtual.Biblioteac_virtual.repository;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Autor;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class AutorRepositoryTests {
    @Autowired
    private AutorRepository repository;

    private Autor autor = new Autor();

    @BeforeEach
    public void setUp(){
        autor.setNombre("Lucas");
        autor.setApellido("Fernandez");
        autor.setFecha_nacimiento(new Date(1999,10,17));
        autor.setNacionalidad("Argentino");
        autor.setEstado(true);
    }

    @DisplayName("Test para guardar autor")
    @Test
    public void testGuardarAutor(){
        Autor autor1 = new Autor();
        autor1.setNombre("Pedro");
        autor1.setApellido("Perez");
        autor1.setFecha_nacimiento(new Date(1984,05,14));
        autor1.setNacionalidad("Peruana");
        autor1.setEstado(true);

        Autor autorGuardado = repository.save(autor1);

        //assertAll(autorGuardado, () -> assertNotNull(autorGuardado), () -> assertTrue(autorGuardado.getId_autor() > 0));
        assertNotNull(autorGuardado);
        assertTrue(autorGuardado.getId_autor() > 0);

    }

    @DisplayName("Test para listar autores")
    @Test
    public void testListarAutor(){
        Autor autor1 = new Autor();
        autor1.setNombre("Pedro");
        autor1.setApellido("Perez");
        autor1.setFecha_nacimiento(new Date(1984,05,14));
        autor1.setNacionalidad("Peruana");
        autor1.setEstado(true);

        repository.save(autor1);
        repository.save(autor);

        List<Autor> lstAutor = repository.findAll();

        assertNotNull(lstAutor);
        assertTrue(lstAutor.size() > 1);

    }

    @DisplayName("Test para listar autor por Id")
    @Test
    public void testListarAutorById(){
        repository.save(autor);

        Optional<Autor> autor1 = repository.findById(autor.getId_autor());

        assertNotNull(autor1.get());
    }

    @DisplayName("Test para modificar un autor")
    @Test
    public void testModificarAutor(){
        repository.save(autor);

        Optional<Autor> autor1 = repository.findById(autor.getId_autor());
        autor1.get().setNombre("Julio");
        autor1.get().setApellido("Lopez");
        autor1.get().setFecha_nacimiento(new Date(1975,10,10));
        autor1.get().setNacionalidad("Peruano");

        Autor autorModificado = repository.save(autor1.get());

        assertEquals(autorModificado.getNombre(),"Julio");
        assertEquals(autorModificado.getApellido(), "Lopez");
        assertEquals(autorModificado.getFecha_nacimiento(), new Date(1975,10,10));
        assertEquals(autorModificado.getNacionalidad(), "Peruano");
    }
    @DisplayName("Test para eliminar un autor")
    @Test
    public void tesEliminarAutor(){
        repository.save(autor);

        repository.deleteById(autor.getId_autor());

        Optional<Autor> optionalAutor = repository.findById(autor.getId_autor());

        assertTrue(optionalAutor.isEmpty());
    }

}
