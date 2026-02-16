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
    private Long ISBM;

    @JoinColumn(name = "titulo")
    private String titulo;

    @JoinColumn(name = "autor")
    private String autor;

    @JoinColumn(name = "fechaLectura")
    private Date fechaLectura;

    @JoinColumn(name = "fechaRegistro")
    private Date fechaRegistro;


    // GETTERS Y SETTERS

    public Long getISBM() {
        return ISBM;
    }

    public void setISBM(Long ISBM) {
        this.ISBM = ISBM;
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

    public Date getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(Date fechaLectura) {
        this.fechaLectura = fechaLectura;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
