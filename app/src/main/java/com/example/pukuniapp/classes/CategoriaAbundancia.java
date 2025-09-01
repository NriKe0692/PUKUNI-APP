package com.example.pukuniapp.classes;

public class CategoriaAbundancia {
    private int categoria_abundancia_id;
    private String categoria_abundancia_name;

    public int getCategoria_abundancia_id() {
        return categoria_abundancia_id;
    }

    public void setCategoria_abundancia_id(int categoria_abundancia_id) {
        this.categoria_abundancia_id = categoria_abundancia_id;
    }

    public String getCategoria_abundancia_name() {
        return categoria_abundancia_name;
    }

    public void setCategoria_abundancia_name(String categoria_abundancia_name) {
        this.categoria_abundancia_name = categoria_abundancia_name;
    }

    @Override
    public String toString() {
        return categoria_abundancia_name;
    }
}
