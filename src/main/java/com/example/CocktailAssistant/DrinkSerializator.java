package com.example.CocktailAssistant;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DrinkSerializator {

    public static ArrayList<Drink> serializeDrinks(String jsonResponse) {
        ArrayList<Drink> drinksList = new ArrayList<>();
        try {
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

            if (jsonObject.has("drinks") && !jsonObject.get("drinks").isJsonNull()) {
                JsonArray drinksArray = jsonObject.getAsJsonArray("drinks");

                for (JsonElement drinkElement : drinksArray) {
                    Drink drink = new Gson().fromJson(drinkElement, Drink.class);
                    drinksList.add(drink);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }


        return drinksList;
    }


}
