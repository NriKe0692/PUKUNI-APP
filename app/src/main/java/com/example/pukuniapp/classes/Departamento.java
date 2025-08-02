package com.example.pukuniapp.classes;

public class Departamento {
    private int departamento_id;
    private String departamento_name;

    public int getDepartamento_id() {
        return departamento_id;
    }

    public String getDepartamento_name() {
        return departamento_name;
    }

    @Override
    public String toString() {
        return departamento_name;
    }
}
