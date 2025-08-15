package com.example.pukuniapp.classes;

public class Habito {
    private int habito_id;
    private String habito_name;

    public int getHabito_id() {
        return habito_id;
    }

    public String getHabito_name() {
        return habito_name;
    }

    public void setHabito_id(int habito_id) {
        this.habito_id = habito_id;
    }

    public void setHabito_name(String habito_name) {
        this.habito_name = habito_name;
    }

    @Override
    public String toString() {
        return habito_name;
    }
}
