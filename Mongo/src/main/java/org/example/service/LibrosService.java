package org.example.service;

import org.example.repository.LibrosRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LibrosService {

    private final LibrosRepository librosRepositor;

    public LibrosService(LibrosRepository librosRepositor) {
        this.librosRepositor = librosRepositor;
    }

    public List<Libros> findAll() {
        return librosRepositor.findAll();
    }

    public Optional<Libros> findById(Long id) {
        return librosRepositor.findById(id);
    }

    public Libros save(Libros libros) {
        return librosRepositor.save(libros);
    }

    public boolean existsById(Long id) {
        return librosRepositor.existsById(id);
    }

    public List<Libros> buscarPorTitulo(String titulo) {
        return librosRepositor.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libros> buscarPorAutor(String autor) {
        return librosRepositor.findByAutorContainingIgnoreCase(autor);
    }

    public List<Libros> buscarPorFechaLectura(Date inicio, Date fin) {
        return librosRepositor.findByFechalecturaBetween(inicio, fin);
    }

    public List<Libros> buscarPorFechaRegistro(Date inicio, Date fin) {
        return librosRepositor.findByFecharegistroBetween(inicio, fin);
    }

    public void deleteById(Long id) {
        librosRepositor.deleteById(id);
    }
}