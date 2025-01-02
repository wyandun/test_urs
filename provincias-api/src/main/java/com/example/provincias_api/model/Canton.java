package com.example.provincias_api.model;

import java.util.Map;

public class Canton {
    private String canton;
    private Map<String, String> parroquias;

    // Getters y setters
    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public Map<String, String> getParroquias() {
        return parroquias;
    }

    public void setParroquias(Map<String, String> parroquias) {
        this.parroquias = parroquias;
    }
}