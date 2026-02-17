package org.example.service;

import org.example.model.Libros;
import org.example.repository.LibrosRepository;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.Optional;

/**
 * Acciones que podrá ejecutar
 */
@Service
public class LibrosService {
    private final LibrosRepository librosRepository;

    // Constructor
    public LibrosService(LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }

    public Libros save(Libros Libros) {
        return librosRepository.save(Libros);
    }

    public boolean existe(Long id) {
        return librosRepository.existsById(id);
    }

    public void delete(Long id) {
        librosRepository.deleteById(id);
    }

    public Optional<Libros>findById(Long id){
        return librosRepository.findById(id);
    }

    public List<Libros>obtenerTodosLibros(){
        return librosRepository.findAll();
    }

}
