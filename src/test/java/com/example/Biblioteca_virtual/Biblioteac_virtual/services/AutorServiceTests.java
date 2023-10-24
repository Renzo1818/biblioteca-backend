package com.example.Biblioteca_virtual.Biblioteac_virtual.services;


import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Autor;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.AutorRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Implements.AutorImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
public class AutorServiceTests {
    @Mock
    private AutorRepository autorRepository;
    @InjectMocks
    private AutorImp autorService;
    private Autor autor = new Autor();

    @BeforeEach
    public void setUp(){
        autor.setId_autor(1);
        autor.setNombre("Lucas");
        autor.setApellido("Fernandez");
        autor.setFecha_nacimiento(new Date(1999,10,17));
        autor.setNacionalidad("Argentino");
        autor.setEstado(true);
    }

    @DisplayName("Test para guardar autor")
    @Test
    public void testGuardarAutor(){
        given(autorRepository.findByApellido(autor.getApellido()))
                .willReturn(Optional.empty());

        given(autorRepository.save(autor)).willReturn(autor);

        autorService.guardarAutor(autor);
        Optional<Autor> autorGuardado = autorRepository.findByApellido(autor.getApellido());

        assertNotNull(autorGuardado);
    }

    @DisplayName("Test para listar autores")
    @Test
    public void testListarAutor(){
        Autor autor1 = new Autor();
        autor1.setId_autor(1);
        autor1.setNombre("Mateo");
        autor1.setApellido("Chavez");
        autor1.setFecha_nacimiento(new Date(1988,8,10));
        autor1.setNacionalidad("Peruano");
        autor1.setEstado(true);

        given(autorRepository.findAll()).willReturn(List.of(autor,autor1));

        List<Autor> autores = autorService.getAllAutores();

        assertNotNull(autores);
        assertTrue(autores.size() > 1);
    }

    @DisplayName("Test para listar autor por ID")
    @Test
    public void testListarAutorById(){
        given(autorRepository.findById(1)).willReturn(Optional.of(autor));

        Autor autorGuardado = autorService.getAutor(autor.getId_autor());

        assertNotNull(autorGuardado);
    }

    @DisplayName("Test para actualizar autor")
    @Test
    public void testActualizarAutor(){
        given(autorRepository.save(autor)).willReturn(autor);
        given(autorRepository.findById(1)).willReturn(Optional.of(autor));
        autorRepository.save(autor);

        Autor autorM = new Autor();
        autorM.setNombre("Mateo");
        autorM.setApellido("Chavez");

        autorService.modifcarAutor(autorM, 1);
        Autor autorActualizado = autorRepository.findById(1).get();

        assertEquals(autorActualizado.getNombre(),"Mateo");
        assertEquals(autorActualizado.getApellido(),"Chavez");

    }

    @DisplayName("Test para eliminar autor")
    @Test
    public void testEliminarAutor(){
        given(autorRepository.save(autor)).willReturn(autor);
        given(autorRepository.findById(1)).willReturn(Optional.of(autor));
        willDoNothing().given(autorRepository).delete(autor);

        autorRepository.save(autor);
        autorService.eliminar(1);

        verify(autorRepository,times(1)).delete(autor);
    }
}
