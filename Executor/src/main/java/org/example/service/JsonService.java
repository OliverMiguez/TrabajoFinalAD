package org.example.service;

import com.google.gson.Gson;
import org.example.model.Libros;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.util.List;

@Service
public class JsonService {
    
    public void exportarLibrosJson(List<Libros> libros){
        Gson gson = new Gson();
        try (FileWriter escritor = new FileWriter("src/main/java/org/example/json/JsonLibros.json")){
            String json = gson.toJson(libros);
            escritor.write(json);
        } catch (Exception e) {
            System.out.println("Error al exportar. "+e.getMessage());
        }
    }
}