package com.example.Biblioteca_virtual.Biblioteac_virtual.repository;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Distrito;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Persona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.TipoPersona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.DistritoRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.PersonaRepository;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.TipoPersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class PersonaRepositoryTest {
    @Autowired
    private PersonaRepository repository;
    @Autowired
    private DistritoRepository repositoryDistrito;
    @Autowired
    private TipoPersonaRepository repositoryTipo;

    private Persona persona = new Persona();
    private Distrito distrito = new Distrito();
    private TipoPersona tipo = new TipoPersona();

    @BeforeEach
    public void setUp(){
        persona.setNombre("Lucas");
        persona.setApellido("Fernandez");
        persona.setDni("01410248");
        persona.setTelefono("987012302");

        distrito.setDescripcion("San Borja");
        repositoryDistrito.save(distrito);

        persona.setDistrito(distrito);

        tipo.setDescripcion("administrador");
        repositoryTipo.save(tipo);

        persona.setTipo(tipo);

    }

    @DisplayName("Test para guardar una persona")
    @Test
    public void testGuardarPersona(){
        Persona persona1 =  repository.save(persona);

        assertNotNull(persona1);
        assertTrue(persona1.getId_persona() > 0);
    }

    @DisplayName("Test para listar personas")
    @Test
    public void testListarPersonas(){
        Persona persona1 = new Persona();
        persona1.setNombre("Pedro");
        persona1.setApellido("Hernandez");
        persona1.setDni("75015896");
        persona1.setTelefono("980103589");

        Distrito distrito1 = new Distrito();
        distrito1.setDescripcion("San Luis");
        repositoryDistrito.save(distrito1);

        persona1.setDistrito(distrito1);

        TipoPersona tipo1 = new TipoPersona();
        tipo1.setDescripcion("usuario");
        repositoryTipo.save(tipo1);

        persona1.setTipo(tipo1);

        repository.save(persona1);
        repository.save(persona);

        List<Persona> lstPersona = repository.findAll();

        assertNotNull(lstPersona);
        assertTrue(lstPersona.size() > 1);

    }

    @DisplayName("Test para listar persona por ID")
    @Test
    public void testListarPersonaById(){
        repository.save(persona);
        Optional<Persona> persona1 = repository.findById(persona.getId_persona());
        assertNotNull(persona1.get());
    }

    @DisplayName("Test para modificar persona")
    @Test
    public void testModificarPersona(){
        repository.save(persona);

        Optional<Persona> persona1 = repository.findById(persona.getId_persona());
        persona1.get().setNombre("Pancho");
        distrito.setDescripcion("San Luis");
        persona1.get().setDistrito(distrito);
        persona1.get().setApellido("Martinez");
        persona1.get().setTelefono("980123485");

        Persona personaModificado = repository.save(persona1.get());

        assertEquals(personaModificado.getNombre(), "Pancho");
        assertEquals(personaModificado.getDistrito().getDescripcion(), "San Luis");
        assertEquals(personaModificado.getApellido(), "Martinez");
        assertEquals(personaModificado.getTelefono(), "980123485");
    }

    @DisplayName("Test para eliminar persona")
    @Test
    public void testEliminarPersona() {
        repository.save(persona);
        repository.deleteById(persona.getId_persona());

        Optional<Persona> optionalPersona = repository.findById(persona.getId_persona());

        assertTrue(optionalPersona.isEmpty());
    }
}
