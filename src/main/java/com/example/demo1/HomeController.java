package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    TextField field;
    public void onListButtonClick() {

        try {
            FXMLLoader loader = new FXMLLoader(CocktailAssistantApp.class.getResource("list-view.fxml"));
            Scene scene = new Scene(loader.load());
            ListController controller = loader.getController();
            controller.searchByName(field.getText());
            Stage stage = (Stage) field.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}