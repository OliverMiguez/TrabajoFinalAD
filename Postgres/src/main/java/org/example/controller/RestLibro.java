package org.example.controller;

import org.example.model.Libros;
import org.example.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(RestLibro.MAPPING)
public class RestLibro {

    public static final String MAPPING = "/postgres/libros";

    @Autowired
    private LibrosService librosService;
    

    @GetMapping
    public List<Libros> getAll() {
        return librosService.obtenerTodosLibros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libros> getById(@PathVariable Long id) {
        return librosService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/titulo")
    public List<Libros> getByTitulo(@RequestParam String titulo) {
        return librosService.buscarPorTitulo(titulo);
    }

    @GetMapping("/buscar/autor")
    public List<Libros> getByAutor(@RequestParam String autor) {
        return librosService.buscarPorAutor(autor);
    }

    @GetMapping("/buscar/fecha-lectura")
    public List<Libros> getByFechaLectura(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
        return librosService.buscarPorFechaLectura(inicio, fin);
    }

    @GetMapping("/buscar/fecha-registro")
    public List<Libros> getByFechaRegistro(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
        return librosService.buscarPorFechaRegistro(inicio, fin);
    }

    @PostMapping
    public ResponseEntity<Libros> create(@RequestBody Libros libros) { //acepta crear xogadores no libros porque se crea 1º libros
        Libros gardado = librosService.save(libros);
        return ResponseEntity.ok(gardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libros> update(@PathVariable Long id,
                                            @RequestBody Libros datos) {
      /*  return librosService.findById(id)
                .map(e -> {
                    e.setNome(datos.getNome());
                    e.setCidade(datos.getCidade());
                    return ResponseEntity.ok(librosService.save(e));
                })
                .orElse(ResponseEntity.notFound().build());
        */

        var librosOptional= librosService.findById(id);
        if(librosOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Libros librosToUpdate = librosOptional.get();
        librosToUpdate.setfechalectura(datos.getfechalectura());
        librosToUpdate.setTitulo(datos.getTitulo());
        librosToUpdate.setfecharegistro(datos.getfecharegistro());
        librosToUpdate.setAutor(datos.getAutor());
        librosToUpdate = librosService.save(librosToUpdate);

        return ResponseEntity.ok(librosToUpdate);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!librosService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        librosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}