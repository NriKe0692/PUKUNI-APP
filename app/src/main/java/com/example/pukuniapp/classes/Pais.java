package com.example.pukuniapp.classes;

public class Pais {
    public int pais_id;
    public String pais_name;

    public int getPais_id() {
        return pais_id;
    }

    public String getPais_name() {
        return pais_name;
    }

    @Override
    public String toString() {
        return pais_name;
    }
}
