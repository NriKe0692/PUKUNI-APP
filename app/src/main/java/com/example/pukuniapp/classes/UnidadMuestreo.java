package com.example.pukuniapp.classes;

public class UnidadMuestreo {
    public int unidad_muestreo_id;
    public String unidad_muestreo_name;

    public int getUnidad_muestreo_id() {
        return unidad_muestreo_id;
    }

    public String getUnidad_muestreo_name() {
        return unidad_muestreo_name;
    }

    public void setUnidad_muestreo_id(int unidad_muestreo_id) {
        this.unidad_muestreo_id = unidad_muestreo_id;
    }

    public void setUnidad_muestreo_name(String unidad_muestreo_name) {
        this.unidad_muestreo_name = unidad_muestreo_name;
    }

    @Override
    public String toString() {
        return unidad_muestreo_name;
    }
}
