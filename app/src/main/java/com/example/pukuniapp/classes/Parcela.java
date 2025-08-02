package com.example.pukuniapp.classes;

public class Parcela {
    int parcela_id;
    String parcela_name;
    int franja_id;
    private String franjaName;

    public int getParcela_id() {
        return parcela_id;
    }
    public int getFranja_id() {
        return franja_id;
    }

    public String getParcela_name(){
        return parcela_name;
    }

    public void setFranjaName(String franjaName) {
        this.franjaName = franjaName;
    }

    @Override
    public String toString() {
        return parcela_name;
    }
}