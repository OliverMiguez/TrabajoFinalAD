package org.example.service;

import com.google.gson.Gson;
import org.example.model.Libros;
import org.example.repository.LibrosRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class LibrosServices {

    private final LibrosRepository librosRepository;

    public LibrosServices(LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }

    public void crearLibros(Libros libros){
        librosRepository.save(libros);
    }

    public List<Libros> findAllLibros(){
        return librosRepository.findAll();
    }

    public void exportarJSON(){
        List<Libros> libros = findAllLibros();
        Gson gson = new Gson();

        try(  FileWriter fileWriter = new FileWriter("C:\\Users\\olimi\\Desktop\\2ºDam\\AD\\TrabajoFinalAD\\Mongo\\src\\main\\java\\org\\example\\Json\\Json.json")) {

            String json = gson.toJson(libros);
            fileWriter.write(json);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
