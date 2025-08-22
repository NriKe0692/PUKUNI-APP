package com.example.pukuniapp.classes;

public class Zona {
    private int zona_id;
    private String zona_name;

    public int getZona_id() {
        return zona_id;
    }

    public void setZona_id(int zona_id) {
        this.zona_id = zona_id;
    }

    public String getZona_name() {
        return zona_name;
    }

    public void setZona_name(String zona_name) {
        this.zona_name = zona_name;
    }

    @Override
    public String toString() {
        return zona_name;
    }
}
