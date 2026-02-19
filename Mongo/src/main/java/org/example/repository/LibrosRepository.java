package org.example.repository;

import org.example.model.Libros;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibrosRepository extends MongoRepository<Libros,Long> {
    List<Libros>findByTitulo(String titulo);
}
