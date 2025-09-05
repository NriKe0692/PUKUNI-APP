package com.example.pukuniapp.classes;

public class UnidadMuestreal {
    private int unidad_muestreal_id;
    private String unidad_muestreal_name;
    private int franja_id;
    private int metodologia_id;
    private int tipo_form_id;

    public int getTipo_form_id() {
        return tipo_form_id;
    }

    public void setTipo_form_id(int tipo_form_id) {
        this.tipo_form_id = tipo_form_id;
    }

    @Override
    public String toString() {
        return unidad_muestreal_name;
    }

    public int getUnidad_muestreal_id() {
        return unidad_muestreal_id;
    }

    public void setUnidad_muestreal_id(int unidad_muestreal_id) {
        this.unidad_muestreal_id = unidad_muestreal_id;
    }

    public String getUnidad_muestreal_name() {
        return unidad_muestreal_name;
    }

    public void setUnidad_muestreal_name(String unidad_muestreal_name) {
        this.unidad_muestreal_name = unidad_muestreal_name;
    }

    public int getFranja_id() {
        return franja_id;
    }

    public void setFranja_id(int franja_id) {
        this.franja_id = franja_id;
    }

    public int getMetodologia_id() {
        return metodologia_id;
    }

    public void setMetodologia_id(int metodologia_id) {
        this.metodologia_id = metodologia_id;
    }
}
