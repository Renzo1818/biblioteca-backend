package com.example.Biblioteca_virtual.Biblioteac_virtual.Services.Interfaces;

import com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO.Editorial;

import java.util.List;

public interface IEditorial {
    List<Editorial> getAllEditoriales();
    Editorial getEditorial(int id);
    void guardarEditorial(Editorial editorial);
    void modificarEditorial(Editorial editorial, int id);
    void eliminarEditorial(int id);
}
