package com.example.CocktailAssistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class CocktailAssistantApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(CocktailAssistantApp.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/img/Icon.png")).toExternalForm(),500,500,true,true)); // il requireNonNull lha messo intelliJ
        stage.setTitle("Cocktail Assistant");
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}