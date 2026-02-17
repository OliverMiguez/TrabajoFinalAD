package org.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Modelo de libros para el MongoDB
 */
@Document(collection = "libros")
public class Libros {

    private Long isbm;
    private String titulo;
    private String autor;
    private Date fechalectura;
    private Date fecharegistro;

    public Long getisbm() {
        return isbm;
    }

    public void setisbm(Long isbm) {
        this.isbm = isbm;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getfechalectura() {
        return fechalectura;
    }

    public void setfechalectura(Date fechalectura) {
        this.fechalectura = fechalectura;
    }

    public Date getfecharegistro() {
        return fecharegistro;
    }

    public void setfecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }
}
