package com.example.pukuniapp.classes;

public class GrupoTrofico {
    private int grupo_trofico_id;
    private String grupo_trofico_name;

    public int getGrupo_trofico_id() {
        return grupo_trofico_id;
    }

    public void setGrupo_trofico_id(int grupo_trofico_id) {
        this.grupo_trofico_id = grupo_trofico_id;
    }

    public String getGrupo_trofico_name() {
        return grupo_trofico_name;
    }

    public void setGrupo_trofico_name(String grupo_trofico_name) {
        this.grupo_trofico_name = grupo_trofico_name;
    }

    @Override
    public String toString() {
        return grupo_trofico_name;
    }
}
