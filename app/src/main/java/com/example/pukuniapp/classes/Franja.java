package com.example.pukuniapp.classes;

public class Franja {
    public int franja_id;
    public String franja_name;
    public int estacion_muestreo_id;

    public int getFranja_id() {
        return franja_id;
    }

    public String getFranja_name() {
        return franja_name;
    }

    public int getEstacion_muestreo_id() {
        return estacion_muestreo_id;
    }

    public void setFranja_id(int franja_id) {
        this.franja_id = franja_id;
    }

    public void setFranja_name(String franja_name) {
        this.franja_name = franja_name;
    }

    public void setEstacion_muestreo_id(int estacion_muestreo_id) {
        this.estacion_muestreo_id = estacion_muestreo_id;
    }

    @Override
    public String toString() {
        return franja_name;
    }
}
