package com.example.pukuniapp.classes;

public class TipoAmbienteAcuatico {
    private int tipo_ambiente_acuatico_id;
    private String tipo_ambiente_acuatico_name;

    public int getTipo_ambiente_acuatico_id() {
        return tipo_ambiente_acuatico_id;
    }

    public void setTipo_ambiente_acuatico_id(int tipo_ambiente_acuatico_id) {
        this.tipo_ambiente_acuatico_id = tipo_ambiente_acuatico_id;
    }

    public String getTipo_ambiente_acuatico_name() {
        return tipo_ambiente_acuatico_name;
    }

    public void setTipo_ambiente_acuatico_name(String tipo_ambiente_acuatico_name) {
        this.tipo_ambiente_acuatico_name = tipo_ambiente_acuatico_name;
    }

    @Override
    public String toString() {
        return tipo_ambiente_acuatico_name;
    }
}
