package com.example.pukuniapp.classes;

public class EtapaReproductiva {
    private int etapa_reproductiva_id;
    private String etapa_reproductiva_name;

    public int getEtapa_reproductiva_id() {
        return etapa_reproductiva_id;
    }

    public void setEtapa_reproductiva_id(int etapa_reproductiva_id) {
        this.etapa_reproductiva_id = etapa_reproductiva_id;
    }

    public String getEtapa_reproductiva_name() {
        return etapa_reproductiva_name;
    }

    public void setEtapa_reproductiva_name(String etapa_reproductiva_name) {
        this.etapa_reproductiva_name = etapa_reproductiva_name;
    }

    @Override
    public String toString() {
        return etapa_reproductiva_name;
    }
}
