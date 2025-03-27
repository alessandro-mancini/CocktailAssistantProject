package com.example.CocktailAssistant;

import com.google.gson.*;
import java.util.ArrayList;

public class DrinkSerializator { // un serializator che in realtà deserializza il JSON in Arraylist di Drink

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
            throw new RuntimeException(e); // con e.printStackTrace mi dava warning
        }


        return drinksList;
    }


}
