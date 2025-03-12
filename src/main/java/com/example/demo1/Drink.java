package com.example.demo1;

import com.google.gson.annotations.SerializedName;

public class Drink {
    @SerializedName("idDrink")
    private String idDrink;

    @SerializedName("strDrink")
    private String strDrink;

    @SerializedName("strCategory")
    private String strCategory;

    @SerializedName("strInstructions")
    private String strInstructions;

    // Getters and toString method for debugging
    public String getIdDrink() {
        return idDrink;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    @Override
    public String toString() {
        return strDrink;
    }
}
