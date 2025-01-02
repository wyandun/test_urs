package com.example.provincias_api.config;  // Usa tu paquete base aquí

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitir solicitudes desde localhost:5173 (el puerto donde corre tu frontend)
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")  // Dirección de tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos que deseas permitir
                .allowedHeaders("*")  // Permitir todos los encabezados
                .allowCredentials(true);  // Si necesitas enviar cookies o credenciales
    }
}