package com.example.CocktailAssistant;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.CocktailAssistant.JSonReader.sendRequest;

public class ListController {


    @FXML
    private ListView listView;
    @FXML
    private TextField field;
    private ArrayList<Drink> drinks = new ArrayList<Drink>();

    @FXML
    public void initialize(){
        for(int i = 0; i < 26; i++) {

            final int index = i;

            new Thread(() -> {
                String letter = String.valueOf((char) (index + 97));
                ArrayList<Drink> tempList = DrinkSerializator.serializeDrinks(sendRequest("search", "f", letter));

                if (tempList != null) {
                    Platform.runLater(() -> {
                        drinks.addAll(tempList);
                        viewDrinks(drinks);
                    });
                }
            }).start();
        }
    }

    @FXML
    public void updateList(){
        String name = field.getText();
        if(!name.equals("")){
            drinks = DrinkSerializator.serializeDrinks(sendRequest("search","s",name));
            viewDrinks(drinks);
        }else{
            initialize();
        }
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

    @FXML
    private void chooseDrink() {
        // Ottieni l'oggetto Drink selezionato
        Drink drink = (Drink) listView.getSelectionModel().getSelectedItem();

        if (drink != null) {
            try {
                // Carica la scena e il controller del nuovo FXML
                FXMLLoader loader = new FXMLLoader(CocktailAssistantApp.class.getResource("drink.fxml"));
                Scene scene = new Scene(loader.load());

                // Ottieni il controller del nuovo FXML
                DrinkController controller = loader.getController();

                // Passa il drink al metodo showInfo del controller
                controller.showInfo(drink);

                // Crea un nuovo Stage per la finestra extra
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Dettagli " + drink.getName());
                stage.getIcons().add(new Image(getClass().getResource("/img/Icon.png").toExternalForm(),500,500,true,true));

                // Mostra la nuova finestra
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public void filterByInitial(ActionEvent event){
        Button pressedButton = (Button) event.getSource();
        String letter = pressedButton.getText();

        drinks = DrinkSerializator.serializeDrinks(sendRequest("search", "f", letter));
        Platform.runLater(() -> viewDrinks(drinks));


    }
}
