package com.example.CocktailAssistant;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.*;


public class AI {

    private static final String API_KEY = "AIzaSyBhzALSp-iTMWQUgtM1moiNvRfAmLs_70I";
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";

    public static String sendCommand(String input) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        String jsonBody = composeJsonBody(input);

        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()
                .url(API_URL + API_KEY)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        return handleResponse(response);
    }

    private static String composeJsonBody(String input) {
        String regole = ".\nSegui le seguenti regole per rispondere:"
                + "\n- Recupera i dati da 'TheCocktailDB' per qualsiasi richiesta ti venga fatta"
                + "\n- Se la domanda/richiesta non è di tua competenza (ossia inerente a TheCocktailDB), rispondi dicendo che la richiesta non è di tua competenza"
                + "\n- Se L'utente ti chiede un consiglio su uno o più cocktail rispondi sinteticamente usando solo il/i singolo/i nome/i del/i cocktail (se il cocktail è presente su TheCocktailDB)"
                + "\n- Se l'utente ti richiede 'n' cocktail tu rispondi con 'n' singoli nomi di cocktail (se il cocktail è presente su TheCocktailDB)"
                + "\n Se l'utente ti chiede gli ingredienti di un cocktail (TheCocktailDB) elencali in formato '[nomeCocktail]: ingrediente1, ingrediente2, ..."
                + "\n- Se un Cocktail non è presente su thecocktaildb, rispondi che il cocktail non è presente nel database";
        String command = input + regole;


        // Build the JSON request body
        String jsonBody = "{\n" +
                "  \"contents\": [{\n" +
                "    \"parts\": [\n" +
                "      {\"text\": \"" + command + "\"}\n" +
                "    ]\n" +
                "  }]\n" +
                "}";

        return jsonBody;
    }


    private static String handleResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            JsonElement jsonElement = JsonParser.parseString(responseBody);
            JsonArray candidates = jsonElement.getAsJsonObject().get("candidates").getAsJsonArray();
            StringBuilder result = new StringBuilder();
            for (JsonElement candidate : candidates) {
                JsonArray parts = candidate.getAsJsonObject().get("content").getAsJsonObject().get("parts").getAsJsonArray();
                for (JsonElement part : parts) {
                    if (part.getAsJsonObject().has("text")) {
                        result.append(part.getAsJsonObject().get("text").getAsString());
                    }
                }
            }
            if (result.toString().equals("true")) {
                return "true";
            }
            return result.toString();
        } else {
            return "Error: " + response.code() + " " + response.message();
        }
    }

}


