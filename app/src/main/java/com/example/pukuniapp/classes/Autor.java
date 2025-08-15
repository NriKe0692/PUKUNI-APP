package com.example.pukuniapp.classes;

public class Autor {
    private int autor_id;
    private String autor_name;

    public int getAutor_id() {
        return autor_id;
    }

    public String getAutor_name() {
        return autor_name;
    }

    public void setAutor_id(int autor_id) {
        this.autor_id = autor_id;
    }

    public void setAutor_name(String autor_name) {
        this.autor_name = autor_name;
    }

    @Override
    public String toString() {
        return autor_name;
    }
}
