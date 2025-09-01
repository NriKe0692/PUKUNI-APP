package com.example.pukuniapp.classes;

public class Orden {
    private int orden_id;
    private String orden_name;
    private int clase_id;
    private int tipo_form_id;

    public int getOrden_id() {
        return orden_id;
    }

    public String getOrden_name() {
        return orden_name;
    }

    public int getClase_id() {
        return clase_id;
    }

    public void setOrden_id(int orden_id) {
        this.orden_id = orden_id;
    }

    public void setOrden_name(String orden_name) {
        this.orden_name = orden_name;
    }

    public void setClase_id(int clase_id) {
        this.clase_id = clase_id;
    }

    public int getTipo_form_id() {
        return tipo_form_id;
    }

    public void setTipo_form_id(int tipo_form_id) {
        this.tipo_form_id = tipo_form_id;
    }

    @Override
    public String toString() {
        return orden_name;
    }
}
