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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.CocktailAssistant.JSonReader.sendRequest;

public class ListController {


    @FXML
    private ListView<Drink> listView;
    @FXML
    private TextArea chatTextArea;
    @FXML
    private TextField chatField;
    @FXML
    private TextField searchField;
    @FXML
    private ImageView searchImage;
    @FXML
    private ImageView randomImage;


    private ArrayList<Drink> drinks = new ArrayList<>();

    @FXML
    public void initialize(){

        randomImage.setImage(new Image(Objects.requireNonNull(getClass().getResource("/img/Random.png")).toExternalForm(),240,200,true,true));
        randomImage.setMouseTransparent(true);
        searchImage.setImage(new Image(Objects.requireNonNull(getClass().getResource("/img/Search.png")).toExternalForm(),360,360,true,true));

        for(int i = 0; i < 26; i++) {

            final int index = i;

            new Thread(() -> {
                String letter = String.valueOf((char) (index + 97));
                ArrayList<Drink> tempList = DrinkSerializator.serializeDrinks(sendRequest("search", "f", letter));

                Platform.runLater(() -> {
                    drinks.addAll(tempList);
                    viewDrinks(drinks);
                });
            }).start();
        }
    }

    @FXML
    public void updateList(){
        String name = searchField.getText();
        if(!name.isEmpty()){
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
        }

    }

    @FXML
    private void chooseDrink() {
        // Ottieni l'oggetto Drink selezionato
        Drink drink = listView.getSelectionModel().getSelectedItem();

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
                stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/img/Icon.png")).toExternalForm(),500,500,true,true));

                // Mostra la nuova finestra
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void randomDrink(){
        Drink drink = DrinkSerializator.serializeDrinks(sendRequest("random",null,null)).getFirst();

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
                stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/img/Icon.png")).toExternalForm(),500,500,true,true));

                // Mostra la nuova finestra
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void filterByInitial(ActionEvent event){
        Button pressedButton = (Button) event.getSource();
        String letter = pressedButton.getText();

        drinks = DrinkSerializator.serializeDrinks(sendRequest("search", "f", letter));
        viewDrinks(drinks);


    }

    public void chatWithAI(){
        try{
            
            String input = chatField.getText();

            if(!input.isEmpty()){
                chatTextArea.appendText("Utente:\n" + input + "\n\n");
                chatField.setText("");

                String response = AI.sendCommand(input);

                chatTextArea.appendText("Gemini:\n" + response + "\n");
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }
}
