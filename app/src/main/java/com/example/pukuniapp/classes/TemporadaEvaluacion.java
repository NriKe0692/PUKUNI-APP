package com.example.pukuniapp.classes;

public class TemporadaEvaluacion {
    private int temporada_evaluacion_id;
    private String temporada_evaluacion_name;

    public int getTemporada_evaluacion_id() {
        return temporada_evaluacion_id;
    }

    public void setTemporada_evaluacion_id(int temporada_evaluacion_id) {
        this.temporada_evaluacion_id = temporada_evaluacion_id;
    }

    public String getTemporada_evaluacion_name() {
        return temporada_evaluacion_name;
    }

    public void setTemporada_evaluacion_name(String temporada_evaluacion_name) {
        this.temporada_evaluacion_name = temporada_evaluacion_name;
    }

    @Override
    public String toString() {
        return temporada_evaluacion_name;
    }
}
