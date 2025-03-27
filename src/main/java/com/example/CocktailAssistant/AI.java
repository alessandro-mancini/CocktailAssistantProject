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
                .
                Segui le seguenti regole per rispondere:
                - Recupera i dati da 'TheCocktailDB' per qualsiasi richiesta ti venga fatta
                - Se la domanda/richiesta non è di tua competenza (ossia inerente a TheCocktailDB), rispondi dicendo che la richiesta non è di tua competenza
                - Se L'utente ti chiede un consiglio su uno o più cocktail rispondi sinteticamente usando solo il/i singolo/i nome/i del/i cocktail (se il cocktail è presente su TheCocktailDB)
                - Se l'utente ti richiede 'n' cocktail tu rispondi con 'n' singoli nomi di cocktail (se il cocktail è presente su TheCocktailDB)
                 Se l'utente ti chiede gli ingredienti di un cocktail (TheCocktailDB) elencali in formato '[nomeCocktail]: ingrediente1, ingrediente2, ...
                - Se un Cocktail non è presente su thecocktaildb, rispondi che il cocktail non è presente nel database"""; // L'ha fatto IntelliJ mi dava warning

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


    private static String handleResponse(Response response) throws IOException {
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


