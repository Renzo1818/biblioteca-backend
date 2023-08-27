package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Autor;

import java.util.List;

public interface IAutor {
    List<Autor> getAllAutores();
    Autor getAutor(int id);
    void guardarAutor(Autor autor);
    void modifcarAutor(Autor autor, int id);
    void eliminar(int id);
}
