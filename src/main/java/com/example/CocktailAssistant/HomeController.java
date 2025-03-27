package com.example.CocktailAssistant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private AnchorPane pane;

    @FXML
    private void initialize(){
        pane.setBackground(uploadBackground());
    }

    @FXML
    private void start(){
        try{
            Stage stage = (Stage) pane.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(CocktailAssistantApp.class.getResource("list.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setResizable(false);

            stage.show();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    private Background uploadBackground(){
        Image backgroundImage = new Image(getClass().getResource("/img/Sfondo.png").toExternalForm()); // Percorso locale

        // Crea un oggetto BackgroundImage
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );

        return new Background(background);
    }

}
