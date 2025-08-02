package com.example.pukuniapp.classes;

public class Forofito {
    int forofito_id;
    String forofito_name;
    int parcela_id;
    String parcela_name;

    public int getForofito_id() {
        return forofito_id;
    }
    public int getParcela_id() {
        return parcela_id;
    }

    public String getForofito_name(){
        return forofito_name;
    }

    public void setParcela_name(String parcela_name) {
        this.parcela_name = parcela_name;
    }

    public String getParcela_name(){
        return parcela_name;
    }

    @Override
    public String toString() {
        return parcela_name;
    }
}
