package com.example.pukuniapp.classes;

public class Clima {
    private int clima_id;
    private String clima_name;

    public int getClima_id() {
        return clima_id;
    }

    public void setClima_id(int clima_id) {
        this.clima_id = clima_id;
    }

    public String getClima_name() {
        return clima_name;
    }

    public void setClima_name(String clima_name) {
        this.clima_name = clima_name;
    }

    @Override
    public String toString() {
        return clima_name;
    }
}
