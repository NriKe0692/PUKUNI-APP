package com.example.pukuniapp.classes;

public class SubParcela {
    int subparcela_id;
    String subparcela_name;
    int parcela_id;

    public int getParcela_id() {
        return subparcela_id;
    }

    public String getSubparcela_name(){
        return subparcela_name;
    }

    @Override
    public String toString() {
        return subparcela_name;
    }
}
