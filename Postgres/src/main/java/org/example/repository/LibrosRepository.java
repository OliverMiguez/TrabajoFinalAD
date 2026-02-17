package org.example.repository;

import org.example.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Permite el guardado de datos
 */
@Repository
public interface LibrosRepository extends JpaRepository<Libros,Long> {
}
