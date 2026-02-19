package org.example;

import org.example.model.Libros;
import org.example.service.ConexionService;
import org.example.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Secuencia {
    @Autowired
    private final ConexionService conexionService;
    @Autowired
    private final LibrosService librosService;

    @Autowired
    public Secuencia(ConexionService conexionService, LibrosService librosService) {
        this.conexionService = conexionService;
        this.librosService = librosService;
    }

    public void executar() {

        List<Libros> libros = conexionService.getAllLibros();

        for(Libros lib : libros){
            librosService.save(lib);
        }


    }
}