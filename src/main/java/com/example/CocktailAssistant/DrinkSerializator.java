package com.example.CocktailAssistant;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DrinkSerializator {

    public static ArrayList<Drink> serializeDrinks(String jsonResponse) {
        try {
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

            if (jsonObject.has("drinks")) {
                JsonArray drinksArray = jsonObject.getAsJsonArray("drinks");
                ArrayList<Drink> drinksList = new ArrayList<>();
                for (JsonElement drinkElement : drinksArray) {
                    Drink drink = new Gson().fromJson(drinkElement, Drink.class);
                    drinksList.add(drink);
                }
                return drinksList;
            }
        } catch (JsonSyntaxException e) {
            System.out.println("Errore nel parsing del JSON: " + e.getMessage());
        }
        return null;
    }


}
