package com.example.pukuniapp.classes;

public class Metodologia {
    private int metodologia_id;
    private String metodologia_name;
    private int tipo_formulario_id;

    public int getMetodologia_id() {
        return metodologia_id;
    }

    public void setMetodologia_id(int metodologia_id) {
        this.metodologia_id = metodologia_id;
    }

    public String getMetodologia_name() {
        return metodologia_name;
    }

    public void setMetodologia_name(String metodologia_name) {
        this.metodologia_name = metodologia_name;
    }

    public int getTipo_formulario_id() {
        return tipo_formulario_id;
    }

    public void setTipo_formulario_id(int tipo_formulario_id) {
        this.tipo_formulario_id = tipo_formulario_id;
    }

    @Override
    public String toString() {
        return metodologia_name;
    }
}
