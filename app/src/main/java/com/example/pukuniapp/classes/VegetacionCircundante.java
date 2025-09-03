package com.example.pukuniapp.classes;

public class VegetacionCircundante {
    private int vegetacion_circundante_id;
    private String vegetacion_circundante_name;

    public int getVegetacion_circundante_id() {
        return vegetacion_circundante_id;
    }

    public void setVegetacion_circundante_id(int vegetacion_circundante_id) {
        this.vegetacion_circundante_id = vegetacion_circundante_id;
    }

    public String getVegetacion_circundante_name() {
        return vegetacion_circundante_name;
    }

    public void setVegetacion_circundante_name(String vegetacion_circundante_name) {
        this.vegetacion_circundante_name = vegetacion_circundante_name;
    }

    @Override
    public String toString() {
        return vegetacion_circundante_name;
    }
}
