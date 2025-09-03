package com.example.pukuniapp.classes;

public class Estacion {
    private int estacion_id;
    private String estacion_name;

    public int getEstacion_id() {
        return estacion_id;
    }

    public void setEstacion_id(int estacion_id) {
        this.estacion_id = estacion_id;
    }

    public String getEstacion_name() {
        return estacion_name;
    }

    public void setEstacion_name(String estacion_name) {
        this.estacion_name = estacion_name;
    }

    @Override
    public String toString() {
        return estacion_name;
    }
}
