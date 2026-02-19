package org.example.service;

import org.example.model.Libros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ConexionMongoService {

    @Autowired
    private RestTemplate restTemplate;
    private static final String POSTGRES_BASE_URL_LIBROS = "http://localhost:8094/Mongo/libros";

    public List<Libros> buscarLibross() {
        try {
            String url = POSTGRES_BASE_URL_LIBROS;
            ResponseEntity<List<Libros>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Libros>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean borrarLibros(Long id) {
        try {
            String url = POSTGRES_BASE_URL_LIBROS+"/"+id;
            ResponseEntity<Void> response = restTemplate.exchange(
                    url, HttpMethod.DELETE, null, Void.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("NonNonNon non dixeche-la palabra maxica jajaja jajaja " + e.getMessage());
            return false;
        }
    }

    public Libros crearLibros(Libros libros) {
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
            System.out.println("Erro xenerico: " + e.getMessage());
            return null;
        }
    }

    public Libros librosPorID(Long id) {
        try {
            String url = POSTGRES_BASE_URL_LIBROS+"/"+id;
            HttpEntity<Libros> response = restTemplate.exchange(url, HttpMethod.GET, null, Libros.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Mensaxe xenerica " + e.getMessage());
            return null;
        }
    }

    public Libros librosPorTitulo(String titulo) {
        try {
            String url = POSTGRES_BASE_URL_LIBROS+"/titulo/"+titulo;
            HttpEntity<List<Libros>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Libros>>() {});
            List<Libros> s = response.getBody();
            return s.get(0);
        } catch (HttpClientErrorException e) {
            System.out.println("Mensaxe xenerica " + e.getMessage());
            return null;
        }
    }

}