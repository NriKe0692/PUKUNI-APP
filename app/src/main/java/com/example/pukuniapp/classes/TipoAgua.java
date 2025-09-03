package com.example.pukuniapp.classes;

public class TipoAgua {
    private int tipo_agua_id;
    private String tipo_agua_name;

    public int getTipo_agua_id() {
        return tipo_agua_id;
    }

    public void setTipo_agua_id(int tipo_agua_id) {
        this.tipo_agua_id = tipo_agua_id;
    }

    public String getTipo_agua_name() {
        return tipo_agua_name;
    }

    public void setTipo_agua_name(String tipo_agua_name) {
        this.tipo_agua_name = tipo_agua_name;
    }

    @Override
    public String toString() {
        return tipo_agua_name;
    }
}
