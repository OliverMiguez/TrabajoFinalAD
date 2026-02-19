package org.example.service;

import com.google.gson.Gson;
import org.example.model.Libros;
import org.example.repository.LibrosRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

@Service
public class LibrosService {

    private LibrosRepository librosRepositor;

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

    public List<Libros> librosByTitulo(String titulo){
        return librosRepositor.findByTitulo(titulo);
    }

    public void deleteById(Long id) {
        librosRepositor.deleteById(id);
    }

    public void exportarJson(){
        Gson gson = new Gson();
        List<Libros> libross = findAll();
        try (FileWriter escritor = new FileWriter("src/main/java/org/example/Json/Libross.json")){
            String json = gson.toJson(libross);
            escritor.write(json);
        } catch (Exception e) {
            System.out.println("Error al exportar. "+e.getMessage());
        }
    }
}