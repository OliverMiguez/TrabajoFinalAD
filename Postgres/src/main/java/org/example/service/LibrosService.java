package org.example.service;

import org.example.model.Libros;
import org.example.repository.LibrosRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<Libros> buscarPorTitulo(String titulo) {
        return librosRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libros> buscarPorAutor(String autor) {
        return librosRepository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Libros> buscarPorFechaLectura(Date inicio, Date fin) {
        return librosRepository.findByFechalecturaBetween(inicio, fin);
    }

    public List<Libros> buscarPorFechaRegistro(Date inicio, Date fin) {
        return librosRepository.findByFecharegistroBetween(inicio, fin);
    }

}
