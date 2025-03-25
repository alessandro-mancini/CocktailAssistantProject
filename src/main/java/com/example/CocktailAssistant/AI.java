package com.example.CocktailAssistant;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.*;


public class AI {

    private static final String API_KEY = "AIzaSyBhzALSp-iTMWQUgtM1moiNvRfAmLs_70I";
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";

    public static String sendCommand(String command) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        // Build the JSON request body
        String jsonBody = "{\n" +
                "  \"contents\": [{\n" +
                "    \"parts\": [\n" +
                "      {\"text\": \"" + command + "\"}\n" +
                "    ]\n" +
                "  }]\n" +
                "}";

        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()
                .url(API_URL + API_KEY)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        return handleResponse(response);
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("cosa vuoi chiedere al cockatil assistant");
        String domanda = scanner.nextLine();
        try {
            String risposta = sendCommand("L'utente ha scritto" + domanda + "rispondi col nome di un singolo cocktail attingengo dal database di TheCocktailDB,rispondi solo se la domanda è pertinente se la domanda non è pertinente rispondimi domanda non pertinente");
            System.out.println(risposta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}


