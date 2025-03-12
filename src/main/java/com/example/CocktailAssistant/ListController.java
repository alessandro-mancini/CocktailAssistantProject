package com.example.CocktailAssistant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import okhttp3.*;

import java.util.ArrayList;

public class ListController {


    @FXML
    private ListView listView;
    @FXML
    private TextField field;
    ArrayList<Drink> drinks = new ArrayList<Drink>();

    @FXML
    public void initialize(){
        drinks = DrinkSerializator.serializeDrinks(sendRequest("list","c","list"));
        viewDrinks(drinks);
    }

    public void updateList(){
        String name = field.getText();
        System.out.println("name " + name);
        drinks = DrinkSerializator.serializeDrinks(sendRequest("search","s",name));
        viewDrinks(drinks);
    }

    private void viewDrinks(ArrayList<Drink> drinks){
        if(drinks != null){
            ObservableList<Drink> items = FXCollections.observableArrayList(drinks);
            listView.setItems(items);
        }else{
            ObservableList<String> items = FXCollections.observableArrayList("Nessun Risultato!");
            listView.setItems(items);
        }
    }



    private ArrayList<String> createTestList(){

        ArrayList<String> list = new ArrayList<>();

        for(int i=0; i<10; i++){
            list.add("rita");
            list.add("margarita");
            list.add("marga");
            list.add("vespasiano");
            list.add("oporcoddio");
        }

        return list;
    }


    private String sendRequest(String action, String param, String value){

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
