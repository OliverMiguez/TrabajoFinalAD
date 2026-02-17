package org.example.repository;

import org.example.model.Libros;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrosRepository extends MongoRepository<Libros,String> {
}
