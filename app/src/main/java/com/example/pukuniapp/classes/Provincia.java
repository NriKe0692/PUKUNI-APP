package com.example.pukuniapp.classes;

public class Provincia {
    private int provincia_id;
    private String provincia_name;

    public int getPronvicia_id() {
        return provincia_id;
    }

    public String getPronvicia_name() {
        return provincia_name;
    }

    @Override
    public String toString() {
        return provincia_name;
    }
}
