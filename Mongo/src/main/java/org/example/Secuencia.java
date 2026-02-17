package org.example;

import org.example.model.Libros;
import org.example.service.ConexionService;
import org.example.service.LibrosService;
import org.example.service.LibrosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Secuencia {
    @Autowired
    private final ConexionService conexionService;
    @Autowired
    private final LibrosServices librosService;

    @Autowired
    public Secuencia(ConexionService conexionService, LibrosServices librosService) {
        this.conexionService = conexionService;
        this.librosService = librosService;
    }

    public void executar() {

        // Trae los libros de Postgres
        List<Libros> libros = conexionService.getAllLibros();

        // Implementa en MongoDB los libros que hemos traído de Postgres

        for(Libros lib : libros){
            librosService.crearLibros(lib);

        }

        librosService.exportarJSON();

    }
}