package com.example.pukuniapp.classes;

public class Indicador {
    private int indicador_id;
    private String indicador_name;

    public int getIndicador_id() {
        return indicador_id;
    }

    public void setIndicador_id(int indicador_id) {
        this.indicador_id = indicador_id;
    }

    public String getIndicador_name() {
        return indicador_name;
    }

    public void setIndicador_name(String indicador_name) {
        this.indicador_name = indicador_name;
    }

    @Override
    public String toString() {
        return indicador_name;
    }
}
