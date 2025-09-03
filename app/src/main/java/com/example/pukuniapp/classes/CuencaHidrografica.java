package com.example.pukuniapp.classes;

public class CuencaHidrografica {
    private int cuenca_hidrografica_id;
    private String cuenca_hidrografica_name;

    public int getCuenca_hidrografica_id() {
        return cuenca_hidrografica_id;
    }

    public void setCuenca_hidrografica_id(int cuenca_hidrografica_id) {
        this.cuenca_hidrografica_id = cuenca_hidrografica_id;
    }

    public String getCuenca_hidrografica_name() {
        return cuenca_hidrografica_name;
    }

    public void setCuenca_hidrografica_name(String cuenca_hidrografica_name) {
        this.cuenca_hidrografica_name = cuenca_hidrografica_name;
    }

    @Override
    public String toString() {
        return cuenca_hidrografica_name;
    }
}
