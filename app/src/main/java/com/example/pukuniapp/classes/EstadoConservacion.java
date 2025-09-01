package com.example.pukuniapp.classes;

public class EstadoConservacion {
    private int estado_conservacion_habitat_id;
    private String estado_conservacion_habitat_name;

    public int getEstado_conservacion_habitat_id() {
        return estado_conservacion_habitat_id;
    }

    public void setEstado_conservacion_habitat_id(int estado_conservacion_habitat_id) {
        this.estado_conservacion_habitat_id = estado_conservacion_habitat_id;
    }

    public String getEstado_conservacion_habitat_name() {
        return estado_conservacion_habitat_name;
    }

    public void setEstado_conservacion_habitat_name(String estado_conservacion_habitat_name) {
        this.estado_conservacion_habitat_name = estado_conservacion_habitat_name;
    }

    @Override
    public String toString() {
        return estado_conservacion_habitat_name;
    }
}
