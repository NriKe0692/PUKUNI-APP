package com.example.pukuniapp.classes;

public class TipoUsos {
    private int usos_id;
    private String usos_name;
    private int tipo_form_id;

    public int getUsos_id() {
        return usos_id;
    }

    public void setUsos_id(int usos_id) {
        this.usos_id = usos_id;
    }

    public String getUsos_name() {
        return usos_name;
    }

    public void setUsos_name(String usos_name) {
        this.usos_name = usos_name;
    }

    public int getTipo_form_id() {
        return tipo_form_id;
    }

    public void setTipo_form_id(int tipo_form_id) {
        this.tipo_form_id = tipo_form_id;
    }

    @Override
    public String toString() {
        return usos_name;
    }
}
