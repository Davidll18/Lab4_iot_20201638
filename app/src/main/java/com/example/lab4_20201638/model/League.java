package com.example.lab4_20201638.model;

import com.google.gson.annotations.SerializedName;

public class League {

    @SerializedName("idLeague")
    private String idLeague; // ID de la liga

    @SerializedName("strLeague")
    private String name; // Nombre de la liga

    @SerializedName("strAlternate1")
    private String alternateName1; // Nombre alternativo 1 (si existe)

    @SerializedName("strAlternate2")
    private String alternateName2; // Nombre alternativo 2 (si existe)

    // Constructor
    public League(String idLeague, String name, String alternateName1, String alternateName2) {
        this.idLeague = idLeague;
        this.name = name;
        this.alternateName1 = alternateName1;
        this.alternateName2 = alternateName2;
    }

    // Getters y Setters
    public String getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(String idLeague) {
        this.idLeague = idLeague;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternateName1() {
        return alternateName1;
    }

    public void setAlternateName1(String alternateName1) {
        this.alternateName1 = alternateName1;
    }

    public String getAlternateName2() {
        return alternateName2;
    }

    public void setAlternateName2(String alternateName2) {
        this.alternateName2 = alternateName2;
    }
}

