package org.example.controller;

import org.example.model.Libros;
import org.example.service.ConexionMongoService;
import org.example.service.ConexionPostgresService;
import org.example.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExecutorController {

    @Autowired
    private ConexionPostgresService conexionPostgresService;

    @Autowired
    private ConexionMongoService conexionMongoService;

    @Autowired
    private XmlService xmlService;

    @Autowired
    private org.example.service.LogService logService;

    @PostMapping("/registro")
    public ResponseEntity<Libros> registrarLibro(@RequestBody Libros libro) {
        logService.registrarEvento("Iniciando registro de libro: " + libro.getTitulo());

        // Guardar en Postgres
        Libros savedPostgres = conexionPostgresService.crearLibros(libro);
        if (savedPostgres == null) {
            logService.registrarEvento("ERROR: No se pudo guardar en Postgres: " + libro.getTitulo());
            return ResponseEntity.status(500).build();
        }

        // Guardar en Mongo
        conexionMongoService.crearLibros(savedPostgres);

        // Guardar en XML (prd-rex / Sistema de Ficheros)
        xmlService.guardarLibro(savedPostgres);

        logService.registrarEvento("ÉXITO: Libro registrado en todos los sistemas: " + savedPostgres.getTitulo());
        return ResponseEntity.ok(savedPostgres);
    }

    @GetMapping("/xml/buscar")
    public ResponseEntity<List<Libros>> buscarEnXml(@RequestParam(required = false) String isbn, @RequestParam(required = false) String titulo) {
        return ResponseEntity.ok(xmlService.buscarLibro(isbn, titulo));
    }
}
