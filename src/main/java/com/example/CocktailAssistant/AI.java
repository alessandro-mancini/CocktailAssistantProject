package com.example.CocktailAssistant;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.*;


public class AI {

    private static final String API_KEY = "AIzaSyBhzALSp-iTMWQUgtM1moiNvRfAmLs_70I";
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";

    public static String sendCommand(String input) throws IOException { // Richiesta HTTP POST
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        String jsonBody = composeJsonBody(input); // Compone il Body

        RequestBody body = RequestBody.create(jsonBody, mediaType);

        Request request = new Request.Builder()
                .url(API_URL + API_KEY)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        return handleResponse(response);
    }

    private static String composeJsonBody(String input) {

        // Regole per 'filtraggio' risposta dell' IA
        String regole = """
              \n.Regole per la Risposta:
              Sii cortese con l'utente, ma cerca di essere sintetico
              Se la richiesta non riguarda un cocktail/bevanda (alcolica o analcolica che sia), rispondi che non è di tua competenza.
              Se richiesto un consiglio su un cocktail, rispondi solo con il nome cocktail, non aggiungere descrizioni se non richiesto.
              Se richiesti 'n' cocktail, rispondi con 'n' nomi di cocktail.
              Per gli ingredienti, rispondi con il formato: Cocktail: 'ingrediente1, ingrediente2, ...'.
              Se un cocktail non è presente su TheCocktailDB, rispondi che non è nel database.
              Esaudisci la richiesta solo con cocktail, bevande e/o ingredienti presi da TheCocktailDB."""; // Gemini non riusciva a capire che deve usare solo dati da TheCocktailDB

        String command = input + regole;


        // Costruisce il Body della richiesta HTTP POST e la ritorna

        return "{\n" +
                "  \"contents\": [{\n" +
                "    \"parts\": [\n" +
                "      {\"text\": \"" + command + "\"}\n" +
                "    ]\n" +
                "  }]\n" +
                "}";

    }


    private static String handleResponse(Response response) throws IOException { // si assicura della validità della risposta e in caso la restituisce
        if (response.isSuccessful()) {
            assert response.body() != null;
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


