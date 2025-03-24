package com.example.CocktailAssistant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

public class ListController {


    @FXML
    private ListView listView;
    @FXML
    private TextField field;
    private ArrayList<Drink> drinks = new ArrayList<Drink>();

    @FXML
    public void initialize(){
        for(int i = 0; i < 26; i++){
            String letter = String.valueOf((char) (i + 97));
            ArrayList<Drink> tempList = DrinkSerializator.serializeDrinks(sendRequest("search","f",letter));
            if(tempList != null){
                drinks.addAll(tempList);
            }
            viewDrinks(drinks);
        }

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

    private void chooseDrink() {
        // Ottieni l'oggetto Drink selezionato
        Drink drink = (Drink) listView.getSelectionModel().getSelectedItem();

        if (drink != null) {  // Assicurati che un drink sia selezionato
            try {
                // Carica la scena e il controller del nuovo FXML
                FXMLLoader loader = new FXMLLoader(CocktailAssistantApp.class.getResource("drink-view.fxml"));
                Scene scene = new Scene(loader.load());

                // Ottieni il controller del nuovo FXML
                DrinkController controller = loader.getController();

                // Passa il drink al metodo showInfo del controller
                controller.showInfo(drink);

                // Crea un nuovo Stage per la finestra extra
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Dettagli del Drink");

                // Mostra la nuova finestra
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Nessun drink selezionato!");
        }
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
            System.out.println(url.toString());
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            return response.body().string();
        } catch (Exception e) {
            return null;
        }

    }

}
