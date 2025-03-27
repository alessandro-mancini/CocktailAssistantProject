package com.example.CocktailAssistant;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JSonReader {

    public static String sendRequest(String action, String param, String value){

        OkHttpClient client = new OkHttpClient();

        HttpUrl url;
        if(param == null){
            url = new HttpUrl.Builder()
                    .scheme("https")
                    .host("www.thecocktaildb.com")
                    .addPathSegment("api")
                    .addPathSegment("json")
                    .addPathSegment("v1")
                    .addPathSegment("1")
                    .addPathSegment(action + ".php")
                    .build();
        }else{
            url = new HttpUrl.Builder()
                    .scheme("https")
                    .host("www.thecocktaildb.com")
                    .addPathSegment("api")
                    .addPathSegment("json")
                    .addPathSegment("v1")
                    .addPathSegment("1")
                    .addPathSegment(action + ".php")
                    .addQueryParameter(param, value)
                    .build();
        }


        // Costruisce la richiesta HTTP GET
        Request request = new Request.Builder()
                .url(url)
                .build();


        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            return response.body().string();
        } catch (Exception e) {
            return null;
        }

    }


}
