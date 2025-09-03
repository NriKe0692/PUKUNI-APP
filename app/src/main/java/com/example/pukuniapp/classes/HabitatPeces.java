package com.example.pukuniapp.classes;

public class HabitatPeces {
    private int habitat_peces_id;
    private String habitat_peces_name;

    public int getHabitat_peces_id() {
        return habitat_peces_id;
    }

    public void setHabitat_peces_id(int habitat_peces_id) {
        this.habitat_peces_id = habitat_peces_id;
    }

    public String getHabitat_peces_name() {
        return habitat_peces_name;
    }

    public void setHabitat_peces_name(String habitat_peces_name) {
        this.habitat_peces_name = habitat_peces_name;
    }

    @Override
    public String toString() {
        return habitat_peces_name;
    }
}
