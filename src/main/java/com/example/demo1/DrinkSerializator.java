package com.example.demo1;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DrinkSerializator {

    /*static class ResultsWrapper {
        ArrayList<Drink> results = new ArrayList<>();
    }*/

    public static ArrayList<Drink> serializeDrinks(String jsonBody){

        //ResultsWrapper wrapper = new ResultsWrapper();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonBody, JsonObject.class);
        JsonElement drinksElement = jsonObject.get("drinks");

        Type listType = new TypeToken<ArrayList<Drink>>() {}.getType();
        ArrayList<Drink> results = gson.fromJson(drinksElement, listType);

        /*if (results != null && !results.isEmpty()) {
            wrapper.results = new ArrayList<>(results);
        } else {
            System.out.println("Nessun drink trovato!");
        }*/

        return results;
    }


}
