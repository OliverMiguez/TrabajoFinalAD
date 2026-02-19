package org.example.service;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LogService {

    private final String RUTA_LOG = "sistema.log";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void registrarEvento(String mensaje) {
        try (FileWriter fw = new FileWriter(RUTA_LOG, true);
             PrintWriter pw = new PrintWriter(fw)) {
            String timestamp = LocalDateTime.now().format(formatter);
            pw.println("[" + timestamp + "] " + mensaje);
        } catch (IOException e) {
            System.err.println("Error escribiendo en log: " + e.getMessage());
        }
    }
}
