package com.example.pukuniapp.classes;

public class Actividad {
    private int actividad_id;
    private String actividad_name;

    public int getActividad_id() {
        return actividad_id;
    }

    public void setActividad_id(int actividad_id) {
        this.actividad_id = actividad_id;
    }

    public String getActividad_name() {
        return actividad_name;
    }

    public void setActividad_name(String actividad_name) {
        this.actividad_name = actividad_name;
    }

    @Override
    public String toString() {
        return actividad_name;
    }
}
