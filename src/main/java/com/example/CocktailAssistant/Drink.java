package com.example.CocktailAssistant;

import com.google.gson.annotations.SerializedName;

public class Drink {
    @SerializedName("idDrink")
    private String idDrink;

    @SerializedName("strDrink")
    private String strDrink;

    @SerializedName("strCategory")
    private String strCategory;
    @SerializedName("strAlcoholic")
    private String strAlcoholic;

    @SerializedName("strInstructionsIT")
    private String strInstructions;

    // Getters and toString method for debugging
    public String getId() {
        return idDrink;
    }

    public String getName() {
        return strDrink;
    }

    public String getCategory() {
        return strCategory;
    }

    public String getInstructions() {
        return strInstructions;
    }

    public boolean isAlcoholic(){
        return strAlcoholic.equals("Alcoholic");
    }

    @Override
    public String toString() {
        return strDrink;
    }
}
