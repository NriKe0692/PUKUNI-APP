package com.example.pukuniapp.classes;

public class Franja {
    public int franja_id;
    public String franja_name;

    public int getFranja_id() {
        return franja_id;
    }

    public String getFranja_name() {
        return franja_name;
    }

    @Override
    public String toString() {
        return franja_name;
    }
}
