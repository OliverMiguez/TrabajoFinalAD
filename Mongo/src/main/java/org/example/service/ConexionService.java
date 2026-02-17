package org.example.service;

import org.example.model.Libros;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ConexionService {

    @Autowired
    private RestTemplate restTemplate;


    private static final String POSTGRES_BASE_URL_LIBROS = "http://localhost:8081/postgres/libros";


    public List<Libros> getAllLibros() {
        try {
            String url = POSTGRES_BASE_URL_LIBROS;
            ResponseEntity<List<Libros>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Libros>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter libross: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Libros getLibrosById(Long id) {
        try {
            String url = POSTGRES_BASE_URL_LIBROS + "/" + id;
            ResponseEntity<Libros> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, Libros.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter libros " + id + ": " + e.getMessage());
            return null;
        }
    }

    public Libros createLibros(Libros libros) {
        try {
            String url = POSTGRES_BASE_URL_LIBROS;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Libros> request = new HttpEntity<>(libros, headers);
            ResponseEntity<Libros> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, Libros.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao crear libros: " + e.getMessage());
            return null;
        }
    }

    public Libros updateLibros(Long id, Libros datos) {
        try {
            String url = POSTGRES_BASE_URL_LIBROS + "/" + id;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Libros> request = new HttpEntity<>(datos, headers);
            ResponseEntity<Libros> response = restTemplate.exchange(
                    url, HttpMethod.PUT, request, Libros.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao actualizar libros " + id + ": " + e.getMessage());
            return null;
        }
    }

    public boolean deleteLibros(Long id) {
        try {
            String url = POSTGRES_BASE_URL_LIBROS + "/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao borrar libros " + id + ": " + e.getMessage());
            return false;
        }
    }
}

