package com.example.CocktailAssistant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private void switchTo(ActionEvent event){

        Button clickedButton = (Button) event.getSource();
        String name = clickedButton.getText().toLowerCase();

        try {
            FXMLLoader loader = new FXMLLoader(CocktailAssistantApp.class.getResource(name + "-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) clickedButton.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
