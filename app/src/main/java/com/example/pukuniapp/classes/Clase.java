package com.example.pukuniapp.classes;

public class Clase {
    private int clase_id;
    private String clase_name;
    private int tipo_form_id;

    public int getClase_id() {
        return clase_id;
    }

    public void setClase_id(int clase_id) {
        this.clase_id = clase_id;
    }

    public void setClase_name(String clase_name) {
        this.clase_name = clase_name;
    }

    public String getClase_name() {
        return clase_name;
    }

    public int getTipo_form_id() {
        return tipo_form_id;
    }

    public void setTipo_form_id(int tipo_form_id) {
        this.tipo_form_id = tipo_form_id;
    }

    @Override
    public String toString() {
        return clase_name;
    }
}
