package org.example.service;

import org.example.model.Libros;
import org.example.repository.LibrosRepository;
import org.springframework.stereotype.Service;

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

    /**
     * Crea o guarda un libro entero en la BD
     * @param libros Clase libro que recoge todos su datos
     * @return Guarda en el repository los datos de libro
     */
    public Libros registrarLibro(Libros libros){
       return librosRepository.save(libros);
    }


}
