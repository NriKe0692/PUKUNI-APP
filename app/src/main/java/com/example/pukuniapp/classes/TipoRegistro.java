package com.example.pukuniapp.classes;

public class TipoRegistro {
    private int tipo_registro_id;
    private String tipo_registro_name;

    public int getTipo_registro_id() {
        return tipo_registro_id;
    }

    public void setTipo_registro_id(int tipo_registro_id) {
        this.tipo_registro_id = tipo_registro_id;
    }

    public String getTipo_registro_name() {
        return tipo_registro_name;
    }

    public void setTipo_registro_name(String tipo_registro_name) {
        this.tipo_registro_name = tipo_registro_name;
    }

    @Override
    public String toString() {
        return tipo_registro_name;
    }
}
