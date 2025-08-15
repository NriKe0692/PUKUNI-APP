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

    public void setForofito_id(int forofito_id) {
        this.forofito_id = forofito_id;
    }

    public void setForofito_name(String forofito_name) {
        this.forofito_name = forofito_name;
    }

    public void setParcela_id(int parcela_id) {
        this.parcela_id = parcela_id;
    }

    @Override
    public String toString() {
        return parcela_name;
    }
}
