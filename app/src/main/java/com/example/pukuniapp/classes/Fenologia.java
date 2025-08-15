package com.example.pukuniapp.classes;

public class Fenologia {
    private int fenologia_id;
    private String fenologia_name;

    public int getFenologia_id() {
        return fenologia_id;
    }

    public String getFenologia_name() {
        return fenologia_name;
    }

    public void setFenologia_id(int fenologia_id) {
        this.fenologia_id = fenologia_id;
    }

    public void setFenologia_name(String fenologia_name) {
        this.fenologia_name = fenologia_name;
    }

    @Override
    public String toString() {
        return fenologia_name;
    }
}
