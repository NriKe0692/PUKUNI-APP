package com.example.pukuniapp.classes;

public class Proyecto {
    private int proyecto_id;
    private String proyecto_name;
    private int pais_id;
    private int departamento_id;
    private int provincia_id;
    private int distrito_id;

    public int getProyecto_id() {
        return proyecto_id;
    }

    public void setProyecto_id(int proyecto_id) {
        this.proyecto_id = proyecto_id;
    }

    public String getProyecto_name() {
        return proyecto_name;
    }

    public void setProyecto_name(String proyecto_name) {
        this.proyecto_name = proyecto_name;
    }

    public int getPais_id() {
        return pais_id;
    }

    public void setPais_id(int pais_id) {
        this.pais_id = pais_id;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public int getProvincia_id() {
        return provincia_id;
    }

    public void setProvincia_id(int provincia_id) {
        this.provincia_id = provincia_id;
    }

    public int getDistrito_id() {
        return distrito_id;
    }

    public void setDistrito_id(int distrito_id) {
        this.distrito_id = distrito_id;
    }

    @Override
    public String toString() {
        return proyecto_name;
    }
}
