package org.example.controller;


import org.example.model.Libros;
import org.example.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(RestLibros.MAPPING)
public class RestLibros {
    public static final String MAPPING = "/Mongo/libros";

    @Autowired
    private LibrosService librosService;


    @GetMapping
    public List<Libros> getAll() {
        return librosService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libros> getById(@PathVariable Long id) {
        return librosService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Libros>> getByTitulo(@PathVariable String titulo) {
        List<Libros> libross = librosService.librosByTitulo(titulo);
        if (libross == null || libross.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libross);
    }


    @PostMapping
    public ResponseEntity<Libros> create(@RequestBody Libros libros) {
        Libros gardado = librosService.save(libros);
        return ResponseEntity.ok(gardado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!librosService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        librosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}