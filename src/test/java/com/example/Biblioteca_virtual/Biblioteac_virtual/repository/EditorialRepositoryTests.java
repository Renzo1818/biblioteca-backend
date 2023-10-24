package com.example.Biblioteca_virtual.Biblioteac_virtual.repository;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Editorial;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Repository.EditorialRepository;
import org.junit.jupiter.api.Assertions;
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
public class EditorialRepositoryTests {
    @Autowired
    private EditorialRepository repository;

    private Editorial editorial = new Editorial();

    @BeforeEach
    public void setUp(){
        editorial.setRazon_social("Rubiales SAC");
        editorial.setRuc("01247015241");
        editorial.setCorreo_electronico("contactRubiales@rubiales.com");
        editorial.setEstado(true);
    }

    @DisplayName("Test para guardar editorial")
    @Test
    public void testGuardarEditorial(){
        Editorial editorialGuardar = repository.save(editorial);
        assertNotNull(editorialGuardar);
        assertTrue(editorialGuardar.getId_editorial() > 0);

    }

    @DisplayName("Test para listar editoriales")
    @Test
    public void testListarEditoriales(){
        Editorial editorial1 = new Editorial();
        editorial1.setRazon_social("Corefo SAC");
        editorial1.setRuc("10247012012");
        editorial1.setCorreo_electronico("contactCorefo@corefo.com");
        editorial1.setEstado(true);

        repository.save(editorial1);
        repository.save(editorial);

        List<Editorial> lstEditorial = repository.findAll();

        assertNotNull(lstEditorial);
        assertTrue(lstEditorial.size() > 0);
    }

    @DisplayName("Test para listar una editorial")
    @Test
    public void testListarByIdEditorial(){
        repository.save(editorial);
        Optional<Editorial> optionalEditorial = repository.findById(editorial.getId_editorial());

        assertNotNull(optionalEditorial.get());
    }

    @DisplayName("Test para modificar una editorial")
    @Test
    public void testModificarEditorial(){
        repository.save(editorial);

        Editorial editorial1 = repository.findById(editorial.getId_editorial()).get();
        editorial1.setRazon_social("Linares SAC");
        editorial1.setRuc("10401785012");
        editorial1.setCorreo_electronico("contactLinares@linares.com");
        editorial1.setEstado(true);

        Editorial editorialModificado =  repository.save(editorial1);
        assertEquals(editorialModificado.getRazon_social(), "Linares SAC");
        assertEquals(editorialModificado.getRuc(), "10401785012");
        assertEquals(editorialModificado.getCorreo_electronico(), "contactLinares@linares.com");
        assertEquals(editorialModificado.isEstado(), true);
    }

    @DisplayName("Test para eliminar una editorial")
    @Test
    public void testEliminarEditorial(){
        repository.save(editorial);

        repository.deleteById(editorial.getId_editorial());

        Optional<Editorial> optionalEditorial = repository.findById(editorial.getId_editorial());

        assertTrue(optionalEditorial.isEmpty());
    }

}
