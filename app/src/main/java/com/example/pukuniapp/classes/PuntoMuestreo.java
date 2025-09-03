package com.example.pukuniapp.classes;

public class PuntoMuestreo {
    private int punto_muestreo_id;
    private String punto_muestreo_name;

    public int getPunto_muestreo_id() {
        return punto_muestreo_id;
    }

    public void setPunto_muestreo_id(int punto_muestreo_id) {
        this.punto_muestreo_id = punto_muestreo_id;
    }

    public String getPunto_muestreo_name() {
        return punto_muestreo_name;
    }

    public void setPunto_muestreo_name(String punto_muestreo_name) {
        this.punto_muestreo_name = punto_muestreo_name;
    }

    @Override
    public String toString() {
        return punto_muestreo_name;
    }
}
