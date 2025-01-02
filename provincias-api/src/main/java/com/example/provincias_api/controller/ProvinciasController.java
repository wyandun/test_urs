package com.example.provincias_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.provincias_api.model.Provincia;
import com.example.provincias_api.model.Canton;

@RestController
@CrossOrigin(origins = "http://localhost:5173")  // Ajusta esto a tu configuración
public class ProvinciasController {

    // URL del servicio que contiene el JSON de provincias
    private static final String API_URL = "https://gist.githubusercontent.com/emamut/6626d3dff58598b624a1/raw/166183f4520c4603987c55498df8d2983703c316/provincias.json";

    // Obtener todas las provincias
    @GetMapping("/provincias")
    public ResponseEntity<Object> getProvincias() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL, String.class);

        // Verifica si la respuesta está vacía
        if (response == null || response.isEmpty()) {
            return ResponseEntity.status(500).body("Respuesta vacía del servicio.");
        }

        // Mostrar la respuesta para depuración
        System.out.println("Respuesta del servicio: " + response);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Provincia> provincias = objectMapper.readValue(response, new TypeReference<Map<String, Provincia>>() {});

            // Filtramos solo el nombre de la provincia
            Map<String, String> provinciasSolo = provincias.entrySet().stream()
                    .filter(entry -> entry.getValue() != null && entry.getValue().getProvincia() != null)
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getProvincia()));

            return ResponseEntity.ok(provinciasSolo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al procesar el JSON: " + e.getMessage());
        }
    }

    // Obtener los cantones de una provincia específica
    @GetMapping("/cantones/{provinciaId}")
    public ResponseEntity<Object> getCantones(@PathVariable String provinciaId) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL, String.class);

        // Verifica si la respuesta está vacía
        if (response == null || response.isEmpty()) {
            return ResponseEntity.status(500).body("Respuesta vacía del servicio.");
        }

        // Mostrar la respuesta para depuración
        System.out.println("Respuesta del servicio: " + response);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Provincia> provincias = objectMapper.readValue(response, new TypeReference<Map<String, Provincia>>() {});

            // Filtrar por la provincia solicitada
            Provincia provincia = provincias.get(provinciaId);
            if (provincia == null) {
                return ResponseEntity.status(404).body("Provincia no encontrada.");
            }

            // Devolver solo los cantones de la provincia, ignorando valores null
            Map<String, String> cantonesSolo = provincia.getCantones().entrySet().stream()
                    .filter(entry -> entry.getValue() != null && entry.getValue().getCanton() != null)
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getCanton()));

            return ResponseEntity.ok(cantonesSolo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al procesar el JSON: " + e.getMessage());
        }
    }

    // Obtener las parroquias de un canton específico
    @GetMapping("/parroquias/{provinciaId}/{cantonId}")
    public ResponseEntity<Object> getParroquias(@PathVariable String provinciaId, @PathVariable String cantonId) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL, String.class);

        // Verifica si la respuesta está vacía
        if (response == null || response.isEmpty()) {
            return ResponseEntity.status(500).body("Respuesta vacía del servicio.");
        }

        // Mostrar la respuesta para depuración
        System.out.println("Respuesta del servicio: " + response);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Provincia> provincias = objectMapper.readValue(response, new TypeReference<Map<String, Provincia>>() {});

            // Filtrar por la provincia solicitada
            Provincia provincia = provincias.get(provinciaId);
            if (provincia == null) {
                return ResponseEntity.status(404).body("Provincia no encontrada.");
            }

            // Filtrar por el canton solicitado
            Canton canton = provincia.getCantones().get(cantonId);
            if (canton == null) {
                return ResponseEntity.status(404).body("Canton no encontrado.");
            }

            // Devolver solo las parroquias del canton, ignorando valores null
            Map<String, String> parroquiasSolo = canton.getParroquias().entrySet().stream()
                    .filter(entry -> entry.getValue() != null)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            return ResponseEntity.ok(parroquiasSolo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al procesar el JSON: " + e.getMessage());
        }
    }
}