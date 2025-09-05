package com.example.pukuniapp.classes;

public class HabitoAlimenticio {
    private int habito_alimenticio_id;
    private String habito_alimenticio_name;
    private int tipo_form_id;

    public int getHabito_alimenticio_id() {
        return habito_alimenticio_id;
    }

    public void setHabito_alimenticio_id(int habito_alimenticio_id) {
        this.habito_alimenticio_id = habito_alimenticio_id;
    }

    public String getHabito_alimenticio_name() {
        return habito_alimenticio_name;
    }

    public void setHabito_alimenticio_name(String habito_alimenticio_name) {
        this.habito_alimenticio_name = habito_alimenticio_name;
    }

    public int getTipo_form_id() {
        return tipo_form_id;
    }

    public void setTipo_form_id(int tipo_form_id) {
        this.tipo_form_id = tipo_form_id;
    }

    @Override
    public String toString() {
        return habito_alimenticio_name;
    }
}
