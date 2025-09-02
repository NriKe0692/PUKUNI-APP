package com.example.pukuniapp.classes;

public class Microhabitat {
    private int microhabitat_id;
    private String microhabitat_name;

    public int getMicrohabitat_id() {
        return microhabitat_id;
    }

    public void setMicrohabitat_id(int microhabitat_id) {
        this.microhabitat_id = microhabitat_id;
    }

    public String getMicrohabitat_name() {
        return microhabitat_name;
    }

    public void setMicrohabitat_name(String microhabitat_name) {
        this.microhabitat_name = microhabitat_name;
    }

    @Override
    public String toString() {
        return microhabitat_name;
    }
}
