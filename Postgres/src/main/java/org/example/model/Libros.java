package org.example.model;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Modelo que recoge todos los datos de cada libro
 */
@Entity
@Table(name = "libros")
public class Libros {

    // VARIABLES

    @Id
    @JoinColumn(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbm;

    @JoinColumn(name = "titulo")
    private String titulo;

    @JoinColumn(name = "autor")
    private String autor;

    @JoinColumn(name = "fechalectura")
    private Date fechalectura;

    @JoinColumn(name = "fecharegistro")
    private Date fecharegistro;


    // GETTERS Y SETTERS

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
