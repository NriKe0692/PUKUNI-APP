package com.example.pukuniapp.classes;

public class Sustrato {
    private int sustrato_id;
    private String sustrato_name;

    public int getSustrato_id() {
        return sustrato_id;
    }

    public void setSustrato_id(int sustrato_id) {
        this.sustrato_id = sustrato_id;
    }

    public String getSustrato_name() {
        return sustrato_name;
    }

    public void setSustrato_name(String sustrato_name) {
        this.sustrato_name = sustrato_name;
    }

    @Override
    public String toString() {
        return sustrato_name;
    }
}
