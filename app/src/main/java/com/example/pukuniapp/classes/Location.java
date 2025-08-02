package com.example.pukuniapp.classes;

public class Location {
    public int lugar_id;
    public String lugar_name;

    public int getLugar_id() {
        return lugar_id;
    }

    public String getLugar_name() {
        return lugar_name;
    }

    @Override
    public String toString() {
        return lugar_name;
    }
}
