package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Permite el guardado de datos
 */
@Repository
public interface LibrosRepository extends JpaRepository<Libros,Long> {
    List<Libros> findByTituloContainingIgnoreCase(String titulo);
    List<Libros> findByAutorContainingIgnoreCase(String autor);
    List<Libros> findByFechalecturaBetween(Date start, Date end);
    List<Libros> findByFecharegistroBetween(Date start, Date end);
}
