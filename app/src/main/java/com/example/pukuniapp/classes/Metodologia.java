package com.example.pukuniapp.classes;

public class Metodologia {
    private int metodologia_id;
    private String metodologia_name;

    public int getMetodologia_id() {
        return metodologia_id;
    }

    public void setMetodologia_id(int metodologia_id) {
        this.metodologia_id = metodologia_id;
    }

    public String getMetodologia_name() {
        return metodologia_name;
    }

    public void setMetodologia_name(String metodologia_name) {
        this.metodologia_name = metodologia_name;
    }

    @Override
    public String toString() {
        return metodologia_name;
    }
}
