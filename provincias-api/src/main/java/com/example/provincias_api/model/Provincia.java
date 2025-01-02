package com.example.provincias_api.model;

import java.util.Map;

public class Provincia {
    private String provincia;
    private Map<String, Canton> cantones;

    // Getters y setters
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Map<String, Canton> getCantones() {
        return cantones;
    }

    public void setCantones(Map<String, Canton> cantones) {
        this.cantones = cantones;
    }
}