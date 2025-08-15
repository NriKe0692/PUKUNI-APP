package com.example.pukuniapp.classes;

public class SubParcela {
    int subparcela_id;
    String subparcela_name;
    int parcela_id;

    public int getSubparcela_id() {
        return subparcela_id;
    }

    public String getSubparcela_name(){
        return subparcela_name;
    }

    public int getParcela_id(){
        return parcela_id;
    }

    public void setSubparcela_id(int subparcela_id) {
        this.subparcela_id = subparcela_id;
    }

    public void setSubparcela_name(String subparcela_name) {
        this.subparcela_name = subparcela_name;
    }

    public void setParcela_id(int parcela_id) {
        this.parcela_id = parcela_id;
    }

    @Override
    public String toString() {
        return subparcela_name;
    }
}
