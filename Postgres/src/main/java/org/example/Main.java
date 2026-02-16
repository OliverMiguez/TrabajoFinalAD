package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Inicia la aplicación que se conecta con Postgres
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
@ComponentScan({"org.example"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}