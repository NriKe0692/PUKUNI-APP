package com.example.pukuniapp.classes;

public class Distrito {
    private int distrito_id;
    private String distrito_name;

    public int getDistrito_id() {
        return distrito_id;
    }

    public String getDistrito_name() {
        return distrito_name;
    }

    @Override
    public String toString() {
        return distrito_name;
    }
}
