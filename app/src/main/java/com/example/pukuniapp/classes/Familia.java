package com.example.pukuniapp.classes;

public class Familia {
    private int familia_id;
    private String familia_name;
    private int orden_id;

    private int tipo_form_id;

    public int getFamilia_id() {
        return familia_id;
    }

    public String getFamilia_name() {
        return familia_name;
    }

    public int getOrden_id() {
        return orden_id;
    }

    public void setFamilia_id(int familia_id) {
        this.familia_id = familia_id;
    }

    public void setFamilia_name(String familia_name) {
        this.familia_name = familia_name;
    }

    public void setOrden_id(int orden_id) {
        this.orden_id = orden_id;
    }

    public int getTipo_form_id() {
        return tipo_form_id;
    }

    public void setTipo_form_id(int tipo_form_id) {
        this.tipo_form_id = tipo_form_id;
    }

    @Override
    public String toString() {
        return familia_name;
    }
}
