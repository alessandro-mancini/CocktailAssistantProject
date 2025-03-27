package com.example.CocktailAssistant;

import com.google.gson.annotations.SerializedName;

public class Drink { // classe per la deserializzazione dei cocktail dal JSON, i warning sono normali
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

    @SerializedName("strDrinkThumb")
    private String imageURL;

    @SerializedName("strIngredient1")
    private String ingredient1;
    @SerializedName("strMeasure1")
    private String measure1;

    @SerializedName("strIngredient2")
    private String ingredient2;
    @SerializedName("strMeasure2")
    private String measure2;

    @SerializedName("strIngredient3")
    private String ingredient3;
    @SerializedName("strMeasure3")
    private String measure3;

    @SerializedName("strIngredient4")
    private String ingredient4;
    @SerializedName("strMeasure4")
    private String measure4;

    @SerializedName("strIngredient5")
    private String ingredient5;
    @SerializedName("strMeasure5")
    private String measure5;

    @SerializedName("strIngredient6")
    private String ingredient6;
    @SerializedName("strMeasure6")
    private String measure6;

    @SerializedName("strIngredient7")
    private String ingredient7;
    @SerializedName("strMeasure7")
    private String measure7;

    @SerializedName("strIngredient8")
    private String ingredient8;
    @SerializedName("strMeasure8")
    private String measure8;

    @SerializedName("strIngredient9")
    private String ingredient9;
    @SerializedName("strMeasure9")
    private String measure9;

    @SerializedName("strIngredient10")
    private String ingredient10;
    @SerializedName("strMeasure10")
    private String measure10;

    @SerializedName("strIngredient11")
    private String ingredient11;
    @SerializedName("strMeasure11")
    private String measure11;

    @SerializedName("strIngredient12")
    private String ingredient12;
    @SerializedName("strMeasure12")
    private String measure12;

    @SerializedName("strIngredient13")
    private String ingredient13;
    @SerializedName("strMeasure13")
    private String measure13;

    @SerializedName("strIngredient14")
    private String ingredient14;
    @SerializedName("strMeasure14")
    private String measure14;

    @SerializedName("strIngredient15")
    private String ingredient15;
    @SerializedName("strMeasure15")
    private String measure15;


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

    public String getImageURL() {
        return imageURL;
    }

    @Override
    public String toString() {
        return strDrink;
    }

    public String getIngredients() { // stampa degli ingredienti e le quantità di ognuno, in quando estrazione avvenuta singolarmente
        StringBuilder ingredients = new StringBuilder();

        // Append ingredient and measure pairs to the string, with "- " prefix
        if (ingredient1 != null && !ingredient1.isEmpty()) {
            ingredients.append("- ").append(ingredient1);
            if (measure1 != null && !measure1.isEmpty()) {
                ingredients.append(" (").append(measure1).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient2 != null && !ingredient2.isEmpty()) {
            ingredients.append("- ").append(ingredient2);
            if (measure2 != null && !measure2.isEmpty()) {
                ingredients.append(" (").append(measure2).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient3 != null && !ingredient3.isEmpty()) {
            ingredients.append("- ").append(ingredient3);
            if (measure3 != null && !measure3.isEmpty()) {
                ingredients.append(" (").append(measure3).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient4 != null && !ingredient4.isEmpty()) {
            ingredients.append("- ").append(ingredient4);
            if (measure4 != null && !measure4.isEmpty()) {
                ingredients.append(" (").append(measure4).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient5 != null && !ingredient5.isEmpty()) {
            ingredients.append("- ").append(ingredient5);
            if (measure5 != null && !measure5.isEmpty()) {
                ingredients.append(" (").append(measure5).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient6 != null && !ingredient6.isEmpty()) {
            ingredients.append("- ").append(ingredient6);
            if (measure6 != null && !measure6.isEmpty()) {
                ingredients.append(" (").append(measure6).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient7 != null && !ingredient7.isEmpty()) {
            ingredients.append("- ").append(ingredient7);
            if (measure7 != null && !measure7.isEmpty()) {
                ingredients.append(" (").append(measure7).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient8 != null && !ingredient8.isEmpty()) {
            ingredients.append("- ").append(ingredient8);
            if (measure8 != null && !measure8.isEmpty()) {
                ingredients.append(" (").append(measure8).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient9 != null && !ingredient9.isEmpty()) {
            ingredients.append("- ").append(ingredient9);
            if (measure9 != null && !measure9.isEmpty()) {
                ingredients.append(" (").append(measure9).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient10 != null && !ingredient10.isEmpty()) {
            ingredients.append("- ").append(ingredient10);
            if (measure10 != null && !measure10.isEmpty()) {
                ingredients.append(" (").append(measure10).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient11 != null && !ingredient11.isEmpty()) {
            ingredients.append("- ").append(ingredient11);
            if (measure11 != null && !measure11.isEmpty()) {
                ingredients.append(" (").append(measure11).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient12 != null && !ingredient12.isEmpty()) {
            ingredients.append("- ").append(ingredient12);
            if (measure12 != null && !measure12.isEmpty()) {
                ingredients.append(" (").append(measure12).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient13 != null && !ingredient13.isEmpty()) {
            ingredients.append("- ").append(ingredient13);
            if (measure13 != null && !measure13.isEmpty()) {
                ingredients.append(" (").append(measure13).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient14 != null && !ingredient14.isEmpty()) {
            ingredients.append("- ").append(ingredient14);
            if (measure14 != null && !measure14.isEmpty()) {
                ingredients.append(" (").append(measure14).append(")");
            }
            ingredients.append("\n");
        }
        if (ingredient15 != null && !ingredient15.isEmpty()) {
            ingredients.append("- ").append(ingredient15);
            if (measure15 != null && !measure15.isEmpty()) {
                ingredients.append(" (").append(measure15).append(")");
            }
            ingredients.append("\n");
        }

        return ingredients.toString().trim();  // Remove trailing newline if any
    }

}
