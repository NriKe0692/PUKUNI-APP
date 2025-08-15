package com.example.pukuniapp.classes;

public class Especie {
    private int especie_id;
    private String especie_name;
    private int genero_id;

    public int getEspecie_id() {
        return especie_id;
    }

    public String getEspecie_name() {
        return especie_name;
    }

    public int getGenero_id() {
        return genero_id;
    }

    public void setEspecie_id(int especie_id) {
        this.especie_id = especie_id;
    }

    public void setEspecie_name(String especie_name) {
        this.especie_name = especie_name;
    }

    public void setGenero_id(int genero_id) {
        this.genero_id = genero_id;
    }
}
