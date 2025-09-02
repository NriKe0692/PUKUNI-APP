package com.example.pukuniapp.classes;

public class CondicionReproductiva {
    private int condicion_reproductiva_id;
    private String condicion_reproductiva_name;

    public int getCondicion_reproductiva_id() {
        return condicion_reproductiva_id;
    }

    public void setCondicion_reproductiva_id(int condicion_reproductiva_id) {
        this.condicion_reproductiva_id = condicion_reproductiva_id;
    }

    public String getCondicion_reproductiva_name() {
        return condicion_reproductiva_name;
    }

    public void setCondicion_reproductiva_name(String condicion_reproductiva_name) {
        this.condicion_reproductiva_name = condicion_reproductiva_name;
    }

    @Override
    public String toString() {
        return condicion_reproductiva_name;
    }
}
