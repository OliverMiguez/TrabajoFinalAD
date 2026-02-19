package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.model.Libros;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class XmlService {

    private final String RUTA_ARCHIVO = "src/main/java/org/example/xml/registros.xml";
    private final XmlMapper xmlMapper;

    public XmlService() {
        this.xmlMapper = new XmlMapper();
        this.xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public synchronized void guardarLibro(Libros libro) {
        List<Libros> libros = leerTodos();
        libros.add(libro);
        escribirArchivo(libros);
    }

    public List<Libros> buscarLibro(String isbn, String titulo) {
        List<Libros> todos = leerTodos();
        return todos.stream()
                .filter(l -> (isbn != null && l.getisbm() != null && l.getisbm().toString().equals(isbn)) ||
                             (titulo != null && l.getTitulo() != null && l.getTitulo().equalsIgnoreCase(titulo)))
                .collect(Collectors.toList());
    }

    private List<Libros> leerTodos() {
        File file = new File(RUTA_ARCHIVO);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return xmlMapper.readValue(file, new TypeReference<List<Libros>>() {});
        } catch (IOException e) {
            System.err.println("Error leyendo XML: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void escribirArchivo(List<Libros> libros) {
        try {
            xmlMapper.writeValue(new File(RUTA_ARCHIVO), libros);
        } catch (IOException e) {
            System.err.println("Error escribiendo XML: " + e.getMessage());
        }
    }
}
