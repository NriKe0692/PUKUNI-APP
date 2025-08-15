package com.example.pukuniapp.classes;

public class Genero {
    private int genero_id;
    private String genero_name;
    private int familia_id;

    public int getGenero_id() {
        return genero_id;
    }

    public String getGenero_name() {
        return genero_name;
    }

    public int getFamilia_id() {
        return familia_id;
    }

    public void setGenero_id(int genero_id) {
        this.genero_id = genero_id;
    }

    public void setGenero_name(String genero_name) {
        this.genero_name = genero_name;
    }

    public void setFamilia_id(int familia_id) {
        this.familia_id = familia_id;
    }
}
