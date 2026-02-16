package org.example.controller;

import org.example.model.Libros;
import org.example.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Permite crear, actualizar, etc los datos de la BD
 */
@RestController
@RequestMapping(RestLibro.MAPPING)
public class RestLibro {

    public static final String MAPPING = "/postgres/libros";

    @Autowired
    private LibrosService librosService;

    @PostMapping
    public ResponseEntity<Libros> create(@RequestBody Libros libros) {
        Libros gardado = librosService.registrarLibro(libros);
        return ResponseEntity.ok(gardado);
    }

}