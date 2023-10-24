package com.example.Biblioteca_virtual.Biblioteac_virtual.controller;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Autor;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.AuthResponse;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.CredentialsDTO;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces.IAuth;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutorControllerRestTemplateTests {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private IAuth servicesAuth;

    @Test
    @Order(1)
    public void testGuardarAutor(){
        Autor autor = new Autor();
        autor.setId_autor(1);
        autor.setNombre("Lucas");
        autor.setApellido("Fernandez");
        autor.setFecha_nacimiento(new Date(1999,10,17));
        autor.setNacionalidad("Argentino");
        autor.setEstado(true);

        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setCorreo_electronico("FENANDO_PUCUTAI@GMAIL.COM");
        credentialsDTO.setContrasena("141516");
        AuthResponse authResponse = servicesAuth.login(credentialsDTO);


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authResponse.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Autor> requestAutor = new HttpEntity<>(autor, headers);

        //ResponseEntity<Autor> response = testRestTemplate.postForEntity("http://localhost:8080/api/autores",requestAutor, Autor.class);
        ResponseEntity<Void> response = testRestTemplate.exchange("/api/autores", HttpMethod.POST, requestAutor, Void.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertTrue(requestAutor.hasBody());
        //assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }

    @Test
    @Order(2)
    public void testListarAutor(){
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setCorreo_electronico("FENANDO_PUCUTAI@GMAIL.COM");
        credentialsDTO.setContrasena("141516");
        AuthResponse authResponse = servicesAuth.login(credentialsDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authResponse.getToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        //ResponseEntity<Autor> response = testRestTemplate.getForEntity("/api/autores/1", Autor.class);
        ResponseEntity<Autor> response = testRestTemplate.exchange("/api/autores/1", HttpMethod.GET, entity, Autor.class);

        Autor autor = response.getBody();

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());

        assertNotNull(autor);
        assertEquals(1,autor.getId_autor());
        assertEquals("Lucas",autor.getNombre());
        assertEquals("Fernandez",autor.getApellido());
        //assertEquals(new Date(1999,10,17),autor.getFecha_nacimiento());
        assertEquals("Argentino",autor.getNacionalidad());
        assertEquals(true,autor.isEstado());
    }
    @Test
    @Order(3)
    public void testListarAutores(){
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setCorreo_electronico("FENANDO_PUCUTAI@GMAIL.COM");
        credentialsDTO.setContrasena("141516");
        AuthResponse authResponse = servicesAuth.login(credentialsDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authResponse.getToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Autor[]> respuesta = testRestTemplate.exchange("/api/autores", HttpMethod.GET, entity, Autor[].class);
        List<Autor> autores = Arrays.asList(respuesta.getBody());

        assertEquals(HttpStatus.OK,respuesta.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,respuesta.getHeaders().getContentType());

        assertEquals(2,autores.size());
        assertEquals(1,autores.get(0).getId_autor());
        assertEquals("Lucas",autores.get(0).getNombre());
        assertEquals("Fernandez",autores.get(0).getApellido());
        //assertEquals(new Date(1999,10,17),autores.get(0).getFecha_nacimiento());
        assertEquals("Argentino",autores.get(0).getNacionalidad());
        assertEquals(true,autores.get(0).isEstado());

    }

    /*@Test
    @Order(4)
    public void testEliminarAutor(){
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setCorreo_electronico("FENANDO_PUCUTAI@GMAIL.COM");
        credentialsDTO.setContrasena("141516");
        AuthResponse authResponse = servicesAuth.login(credentialsDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authResponse.getToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Autor[]> respuesta = testRestTemplate.exchange("/api/autores", HttpMethod.GET, entity, Autor[].class);
        List<Autor> autores = Arrays.asList(respuesta.getBody());
        assertEquals(2,autores.size());

        Map<String,Integer> pathVariables = new HashMap<>();
        pathVariables.put("id",1);
        ResponseEntity<Void> exchange = testRestTemplate.exchange("/api/autores/{id}",HttpMethod.DELETE, entity, Void.class, pathVariables);

        assertEquals(HttpStatus.OK,exchange.getStatusCode());
        assertFalse(exchange.hasBody());

        respuesta = testRestTemplate.exchange("/api/autores", HttpMethod.GET, entity, Autor[].class);
        autores = Arrays.asList(respuesta.getBody());
        assertEquals(1,autores.size());

        ResponseEntity<Autor> responseDetalle = testRestTemplate.exchange("/api/autores/{2}",HttpMethod.GET,entity,Autor.class);
        assertEquals(HttpStatus.NOT_FOUND,responseDetalle.getStatusCode());
        assertFalse(responseDetalle.hasBody());
    }*/
}
