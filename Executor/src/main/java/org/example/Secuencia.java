package org.example;
import org.example.model.Libros;
import org.example.service.ConexionMongoService;
import org.example.service.ConexionPostgresService;
import org.example.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class Secuencia {

    @Autowired
    private ConexionPostgresService conexionPostgresService;
    @Autowired
    private ConexionMongoService conexionMongoService;
    @Autowired
    private JsonService jsonService;

    public void executar() throws ParseException {
        Libros libros = new Libros();
        libros.setTitulo("Historias corrientes");
        libros.setAutor("Oliver");
        libros.setfecharegistro(new SimpleDateFormat("yyyy-MM-dd").parse("2026-02-19"));
        libros.setfechalectura(new SimpleDateFormat("yyyy-MM-dd").parse("2026-02-19"));

        libros = conexionPostgresService.crearLibros(libros);

        List<Libros> libross = conexionPostgresService.buscarLibross();
        for (Libros s:libross){
            conexionMongoService.crearLibros(s);
        }

        jsonService.exportarLibrosJson(libross);
        conexionPostgresService.borrarLibros(libros.getisbm());
        conexionMongoService.borrarLibros(libros.getisbm());

    }
}