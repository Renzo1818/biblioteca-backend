package com.example.Biblioteca_virtual.Biblioteac_virtual;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Mappers.PersonaMapper;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Persona;
import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DTO.PersonaDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BiblioteacVirtualApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioteacVirtualApplication.class, args);
	}
	@Bean
	public ModelMapper ModelMapper(){
		return new ModelMapper();
	}
}
