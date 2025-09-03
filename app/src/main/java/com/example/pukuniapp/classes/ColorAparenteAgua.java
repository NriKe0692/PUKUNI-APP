package com.example.pukuniapp.classes;

public class ColorAparenteAgua {
    private int color_aparente_agua_id;
    private String color_aparente_agua_name;

    public int getColor_aparente_agua_id() {
        return color_aparente_agua_id;
    }

    public void setColor_aparente_agua_id(int color_aparente_agua_id) {
        this.color_aparente_agua_id = color_aparente_agua_id;
    }

    public String getColor_aparente_agua_name() {
        return color_aparente_agua_name;
    }

    public void setColor_aparente_agua_name(String color_aparente_agua_name) {
        this.color_aparente_agua_name = color_aparente_agua_name;
    }

    @Override
    public String toString() {
        return color_aparente_agua_name;
    }
}
