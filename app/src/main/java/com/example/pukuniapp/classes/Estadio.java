package com.example.pukuniapp.classes;

public class Estadio {
    private int estadio_id;
    private String estadio_name;

    public int getEstadio_id() {
        return estadio_id;
    }

    public String getEstadio_name() {
        return estadio_name;
    }

    public void setEstadio_name(String estadio_name) {
        this.estadio_name = estadio_name;
    }

    public void setEstadio_id(int estadio_id) {
        this.estadio_id = estadio_id;
    }

    @Override
    public String toString() {
        return estadio_name;
    }
}
