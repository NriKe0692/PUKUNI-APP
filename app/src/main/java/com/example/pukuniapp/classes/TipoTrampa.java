package com.example.pukuniapp.classes;

public class TipoTrampa {
    private int tipo_trampa_id;
    private String tipo_trampa_name;

    public int getTipo_trampa_id() {
        return tipo_trampa_id;
    }

    public void setTipo_trampa_id(int tipo_trampa_id) {
        this.tipo_trampa_id = tipo_trampa_id;
    }

    public String getTipo_trampa_name() {
        return tipo_trampa_name;
    }

    public void setTipo_trampa_name(String tipo_trampa_name) {
        this.tipo_trampa_name = tipo_trampa_name;
    }

    @Override
    public String toString() {
        return tipo_trampa_name;
    }
}