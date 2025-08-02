package com.example.pukuniapp.classes;

public class UnidadVegetacion {
    public int unidad_vegetacion_id;
    public String unidad_vegetacion_name;

    public int getUnidad_vegetacion_id() {
        return unidad_vegetacion_id;
    }

    public String getUnidad_vegetacion_name() {
        return unidad_vegetacion_name;
    }

    @Override
    public String toString() {
        return unidad_vegetacion_name;
    }
}
